import { configureStore } from '@reduxjs/toolkit'
import { employeeSlice } from './slices/employee';

export const store = configureStore({
  reducer: {
    employee: employeeSlice.reducer
  },
});