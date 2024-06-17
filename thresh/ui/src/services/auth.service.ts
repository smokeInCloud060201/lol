import {createApi} from "@reduxjs/toolkit/query";
import {baseQueryWithReAuth} from "./base.service";


const authServiceAPI = createApi({
    reducerPath: 'authServiceAPI',

    baseQuery: baseQueryWithReAuth,

    endpoints: builder => ({
        login : builder.mutation<string, Record<string, string>>({
            query: (param: Record<string, string>) => ({
                url: '/api/v1/auth/login',
                method: 'POST',
                body: param
            })
        })
    }),
});

export default authServiceAPI;