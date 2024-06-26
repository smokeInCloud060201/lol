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
      headers.set("Authorization", "Basic " + btoa("demo-client:demo-secret"));
      return headers;
    },
  }),
  endpoints: (builder) => ({
    login: builder.mutation<IBaseResponse<ILoginResponse>, ILoginRequest>({
      query: (body: ILoginRequest) => ({
        url: "/oauth2/token",
        method: "POST",
        body: new URLSearchParams({
          username: body.email,
          password: body.password,
          grant_type: "grant_password",
        }),
      }),
    }),
  }),
});

export const { useLoginMutation } = authApi;
