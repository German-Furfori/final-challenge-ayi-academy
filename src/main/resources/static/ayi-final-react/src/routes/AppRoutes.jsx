import React from 'react';
import EmployeeList from '../components/EmployeeList/EmployeeList';
import EmployeeDetails from '../components/EmployeeDetails/EmployeeDetails';
import NavBar from '../components/NavBar/NavBar';
import Home from '../components/Home/Home';
import Login from '../components/Account/Login';
import Logout from '../components/Account/Logout';
import Register from '../components/Account/Register';
import { PrivateRoute } from './PrivateRoute';
import { PublicRoute } from './PublicRoute';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useSelector } from "react-redux";
import { isUserLogged } from '../store/slices/user/userFunctions'

export default function AppRoutes() {
  const { userLogged } = useSelector((state) => state.user);
  const isLogged = isUserLogged(userLogged);

  return (
    <>
      <BrowserRouter>
        <NavBar/>
        <Routes>
          <Route path='/' element={
            <PublicRoute authentication = {isLogged} >
              <Login/>
            </PublicRoute>
          } />
          <Route path='/register' element={
            <PublicRoute authentication = {isLogged} >
              <Register/>
            </PublicRoute>
          } />
          <Route path='/home' element={
            <PrivateRoute authentication = {isLogged} >
              <Home/>
            </PrivateRoute>
          } />
          <Route path="/employees" element={
            <PrivateRoute authentication = {isLogged} >
              <EmployeeList/>
            </PrivateRoute>
          } />
          <Route path="/employee/:idEmployee" element={
            <PrivateRoute authentication = {isLogged} >
              <EmployeeDetails/>
            </PrivateRoute>
          } />
          <Route path="/logout" element={
            <PrivateRoute authentication = {isLogged} >
              <Logout/>
            </PrivateRoute>
          } />
        </Routes>
      </BrowserRouter>
    </>
  )
}