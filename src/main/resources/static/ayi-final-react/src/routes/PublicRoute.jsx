import { Navigate } from "react-router-dom";

export const PublicRoute = ({authentication, children}) => {

  return authentication ? <Navigate to="/home" /> : children;
  
};