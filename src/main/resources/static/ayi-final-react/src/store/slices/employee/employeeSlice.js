import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  initialState: {
    isLoading: false,
    page: 0,
    employees: []
  }
}

// The slice is a function that receives an object that has a name, an initial state and the reducers

export const employeeSlice = createSlice({
  name: 'employee',
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

// Action creators are generated for each case reducer function
export const { startLoadingEmployees, setEmployees } = employeeSlice.actions