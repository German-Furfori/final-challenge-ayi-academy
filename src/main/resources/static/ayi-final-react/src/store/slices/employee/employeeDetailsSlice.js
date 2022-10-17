import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  initialState: {
    firstName: '',
    lastName: '',
    employeeDetails: {},
    project: {},
    isLoading: false
  }
}

export const employeeDetailsSlice = createSlice({
  name: 'employeeDetails',
  initialState,
  reducers: {
    startLoadingEmployee: (state) => {
      state.isLoading = true;
    },
    setEmployeeDetails: (state, action) => {
      state.isLoading = false;
      state.firstName = action.payload.firstName;
      state.lastName = action.payload.lastName;
      state.employeeDetails = action.payload.employeeDetails;
      state.project = action.payload.project;
    }
  },
});

export const { startLoadingEmployee, setEmployeeDetails } = employeeDetailsSlice.actions