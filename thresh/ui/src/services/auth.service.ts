import { createApi } from '@reduxjs/toolkit/query/react';
import { baseQueryWithReAuth } from './base.service';
import { IBaseResponse } from '../interface/IBase';
import { ILoginRequest, ILoginResponse } from '../interface/IAuth';

export const authApi = createApi({
    reducerPath: 'authServiceAPI',
    baseQuery: baseQueryWithReAuth,
    endpoints: (builder) => ({
        login: builder.mutation<{ data: IBaseResponse<ILoginResponse> }, ILoginRequest>({
            query: (body: ILoginRequest) => ({
                url: 'api/v1/auth/login',
                method: 'POST',
                body: body
            })
        })
    })
});

export const { useLoginMutation } = authApi;
