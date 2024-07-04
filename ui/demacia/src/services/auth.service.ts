import { createApi } from "@reduxjs/toolkit/query/react";
import { IBaseResponse } from "../interface/IBase";
import { ILoginRequest, ILoginResponse } from "../interface/IAuth";
import { fetchBaseQuery } from "@reduxjs/toolkit/query";

export const authApi = createApi({
  reducerPath: "authServiceAPI",
  baseQuery: fetchBaseQuery({
    baseUrl: "http://localhost:8086",

    prepareHeaders: (headers: Headers) => {
      headers.set("X-Requested-With", `XMLHttpRequest`);
      headers.set("Content-Type", "application/x-www-form-urlencoded");
      headers.set("Content-Type", "application/json");
      return headers;
    },
  }),
  endpoints: (builder) => ({
    login: builder.mutation<IBaseResponse<ILoginResponse>, ILoginRequest>({
      query: (request: ILoginRequest) => ({
        url: "/api/v1/auth/login",
        method: "POST",
        body: request,
      }),
    }),
  }),
});

export const { useLoginMutation } = authApi;
