import { useDispatch } from "react-redux";
import { Navigate } from "react-router-dom";
import { logoutUser } from "../../store/slices/user/userSlice";

const Logout = () => {
  const dispatch = useDispatch();
  window.localStorage.clear();
  dispatch(logoutUser());
  <Navigate to="/" />
};

export default Logout;
