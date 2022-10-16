import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  initialState: {
    firstName: '',
    lastName: '',
    employeeDetails: {},
    project: {}
  }
}

export const employeeDetailsSlice = createSlice({
  name: 'employeeDetails',
  initialState,
  reducers: {
    setEmployeeDetails: (state, action) => {
        state.firstName = action.payload.firstName;
        state.lastName = action.payload.lastName;
        state.employeeDetails = action.payload.employeeDetails;
        state.project = action.payload.project;
      }
  },
})

export const { setEmployeeDetails } = employeeDetailsSlice.actions