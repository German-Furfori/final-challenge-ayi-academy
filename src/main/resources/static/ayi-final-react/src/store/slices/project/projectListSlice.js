import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    projectList: [],
    isLoading: false
}

export const projectListSlice = createSlice({
    name: 'projectList',
    initialState,
    reducers: {
        startLoadingProjects: (state) => {
            state.isLoading = true;
        },
        setProjects: (state, action) => {
            state.isLoading = false;
            state.projectList = action.payload.projectList;
        }
    }
});

export const { startLoadingProjects, setProjects } = projectListSlice.actions