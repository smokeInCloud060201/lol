import React, { useMemo } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useSelector } from "react-redux";
import { selectToken } from "../store/slices/auth.slice";

const AuthLayout = () => {
  const token = useSelector(selectToken);
  const isAuthenticated = useMemo(() => {
    return token !== undefined && token !== "";
  }, [token]);

  console.log("token", token);

  if (isAuthenticated) {
    return <Outlet />;
  }
  return <Navigate to={"/auth"} replace />;
};
export default AuthLayout;
