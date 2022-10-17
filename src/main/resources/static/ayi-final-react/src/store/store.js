import { configureStore } from '@reduxjs/toolkit'
import { employeePagesSlice, employeeDetailsSlice } from './slices/employee';
import { projectListSlice } from './slices/project/projectListSlice';
import { userSlice } from './slices/user/userSlice';

export const store = configureStore({
  reducer: {
    employeePages: employeePagesSlice.reducer,
    employeeDetails: employeeDetailsSlice.reducer,
    projectList: projectListSlice.reducer,
    user: userSlice.reducer
  },
});