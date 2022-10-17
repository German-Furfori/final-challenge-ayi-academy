import { Navigate } from "react-router-dom";

export const PrivateRoute = ({authentication, children}) => {
  
  return authentication ? children : <Navigate to="/" />;

};