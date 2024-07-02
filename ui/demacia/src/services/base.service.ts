import {
  BaseQueryFn,
  FetchArgs,
  fetchBaseQuery,
  FetchBaseQueryError,
} from "@reduxjs/toolkit/query";
import { RootState } from "../store/store";

export const baseQuery = fetchBaseQuery({
  baseUrl: "http://localhost:8086/",

  prepareHeaders: (headers: Headers, { getState }) => {
    headers.set("X-Requested-With", `XMLHttpRequest`);
    headers.set("Content-Type", "application/json");
    const token = (getState() as RootState).auth.token;
    if (token) {
      headers.set("Authorization", `Bearer ${token}`);
    }

    return headers;
  },
});

export const baseQueryWithReAuth: BaseQueryFn<
  string | FetchArgs,
  unknown,
  FetchBaseQueryError
> = async (args, api, extraOptions) => {
  const result = await baseQuery(args, api, extraOptions);

  if (result?.error && result?.error.status === 401 && !!(api.getState() as RootState).auth.token) {
    //show modal token was expired
    console.log(result);
  }

  return result;
};
