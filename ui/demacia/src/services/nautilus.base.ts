import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";
import { getItems, setItems } from "../utils/storage.util";
import { PARAMETERS } from "../constant";
import Toast, { IToastProps } from "../components/toast/Toast";
import { ERROR_CODE } from "../constant/api";

interface ErrorResponse {
  code: number;
  error_code: string;
  error_message: string;
}

const accessToken = getItems(PARAMETERS.DEMACIA_ACCESS_TOKEN);

const nautilusApi = axios.create({
  baseURL: "http://localhost:8086/",
  headers: {
    Accept: "*/*",
    Authorization: accessToken ? `Bearer ${accessToken}` : "",
  },
});

let isRefreshing = false;
let refreshSubscribers: ((token: string) => void)[] = [];

const subscribeTokenRefresh = (cb: (token: string) => void) => {
  refreshSubscribers.push(cb);
};

const onRefreshed = (newToken: string) => {
  refreshSubscribers.forEach((cb) => cb(newToken));
  refreshSubscribers = [];
};

const refreshToken = async (): Promise<string> => {
  const refreshToken = getItems(PARAMETERS.DEMACIA_ACCESS_TOKEN); // Assuming you store a refresh token
  const response = await axios.post<{ access_token: string }>("http://localhost:8086/api/login", {
    token: refreshToken,
  });
  const newToken = response.data.access_token;

  setItems(PARAMETERS.DEMACIA_ACCESS_TOKEN, newToken);
  return newToken;
};

nautilusApi.interceptors.response.use(
  (response: AxiosResponse) => {
    return response;
  },
  async (error: AxiosError<ErrorResponse>) => {
    const { config, response } = error;
    if (response?.data?.error_code === ERROR_CODE.TOKEN_EXPIRED) {
      if (!isRefreshing) {
        isRefreshing = true;
        try {
          const newToken = await refreshToken();
          isRefreshing = false;
          onRefreshed(newToken);
        } catch (err) {
          isRefreshing = false;
          return Promise.reject(err);
        }
      }

      const retryOriginalRequest = new Promise<AxiosResponse>((resolve) => {
        subscribeTokenRefresh((newToken: string) => {
          if (config && config.headers) {
            config.headers.Authorization = `Bearer ${newToken}`;
          }
          resolve(nautilusApi(config as AxiosRequestConfig));
        });
      });

      return retryOriginalRequest;
    } else {
      const toast: IToastProps = {
        errorCode: response?.data?.error_code,
        errorMessage: response?.data?.error_message,
      };

      Toast(toast);
    }

    return Promise.reject(response);
  },
);

export default nautilusApi;
