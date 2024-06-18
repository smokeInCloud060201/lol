import { createSlice } from '@reduxjs/toolkit'
import type { RootState } from '../store'


interface AuthState {
    token: string
}

const initialState: AuthState = {
    token: '',
}


export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        setToken: (state, action ) => {
            state.token += action.payload
        },
    },
})

export const { setToken } = authSlice.actions

export const selectToken = (state: RootState) => state.auth.token;

export default authSlice.reducer;
