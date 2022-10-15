import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  initialState: {
    firstName: '',
    lastName: '',
    employeeDetails: {}
  }
}

export const employeeDetailsSlice = createSlice({
  name: 'employeeDetails',
  initialState,
  reducers: {
    setEmployeeDetails: (state, action) => {
        state.employeeDetails = action.payload.employeeDetails;
      }
  },
})

export const { setEmployeeDetails } = employeeDetailsSlice.actions