import React from 'react';
import EmployeeList from './EmployeeList/EmployeeList';
import EmployeeDetails from './EmployeeDetails/EmployeeDetails';
import NavBar from './NavBar/NavBar';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

export default function AppRoutes() {
  return (
    <>
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path="/employees" element={<EmployeeList />} />
          <Route path="/employee/:id" element={<EmployeeDetails/>} />
        </Routes>
      </BrowserRouter>
    </>
  )
}