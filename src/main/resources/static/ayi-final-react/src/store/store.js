import { configureStore } from '@reduxjs/toolkit'
import { employeePagesSlice, employeeDetailsSlice } from './slices/employee';

export const store = configureStore({
  reducer: {
    employeePages: employeePagesSlice.reducer,
    employeeDetails: employeeDetailsSlice.reducer
  },
});