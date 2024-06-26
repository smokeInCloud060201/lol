import { createApi } from '@reduxjs/toolkit/query/react';
import { baseQuery } from './base.service';
import { IBaseResponse } from '../interface/IBase';
import { ILoginRequest, ILoginResponse } from '../interface/IAuth';

export const authApi = createApi({
    reducerPath: 'authServiceAPI',
    baseQuery: baseQuery,
    endpoints: (builder) => ({
        login: builder.mutation<IBaseResponse<ILoginResponse>, ILoginRequest>({
            query: (body: ILoginRequest) => ({
                url: 'api/v1/auth/login',
                method: 'POST',
                body: body
            })
        })
    })
});

export const { useLoginMutation } = authApi;
