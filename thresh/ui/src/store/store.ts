import {configureStore} from '@reduxjs/toolkit'
import authSlice from "./slices/auth.slice";
import authServiceAPI from "../services/auth.service";


export const store = configureStore({
    reducer: {
        [authServiceAPI.reducerPath]: authServiceAPI.reducer,
        auth: authSlice
    },
    middleware: getDefaultMiddleware => getDefaultMiddleware()
        .concat(authServiceAPI.middleware)
})

export type RootState = ReturnType<typeof store.getState>

export type AppDispatch = typeof store.dispatch

