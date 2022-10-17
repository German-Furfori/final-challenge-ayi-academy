import { createSlice } from '@reduxjs/toolkit';

const userLogged = window.localStorage.getItem('userLogged');

const initialState = {
    users: [{
        username: 'ayi',
        password: '123'
    }],
    userLogged: userLogged,
    message: '',
}

export const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        createUser: (state, action) => {
            state.users.push({
                username: action.payload.username,
                password: action.payload.password,
            });
        },
        loginUser: (state, action) => {
            state.userLogged = action.payload.userLogged;
        },
        logoutUser: (state) => {
            state.userLogged = null;
        },
        setMessage: (state, action) => {
            state.message = action.payload.message;
        },
    },
});

export const { createUser, loginUser, logoutUser, setMessage } = userSlice.actions;