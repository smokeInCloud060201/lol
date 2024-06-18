import { configureStore } from '@reduxjs/toolkit';
import authSlice from './slices/auth.slice';
import { authApi } from '../services/auth.service';
import { setupListeners } from '@reduxjs/toolkit/query/react';

export const store = configureStore({
    reducer: {
        [authApi.reducerPath]: authApi.reducer,
        auth: authSlice
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(authApi.middleware)
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;

setupListeners(store.dispatch);
