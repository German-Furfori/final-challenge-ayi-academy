import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  initialState: {
    isLoading: false,
    page: 0,
    employeePages: {},
  }
}

// The slice is a function that receives an object that has a name, an initial state and the reducers

export const employeePagesSlice = createSlice({
  name: 'employeePages',
  initialState,
  reducers: {
    startLoadingEmployees: (state) => {
      state.isLoading = true;
    },
    setEmployees: (state, action) => {
      state.isLoading = false;
      state.page = action.payload.page;
      state.employees = action.payload.employees;
    }
  },
})

export const { startLoadingEmployees, setEmployees } = employeePagesSlice.actions