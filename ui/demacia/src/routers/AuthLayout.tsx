import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useSelector } from "react-redux";
import { selectToken } from "../store/slices/auth.slice";

const AuthLayout = () => {
  const token = useSelector(selectToken);

  console.log("token", token);

  if (token !== undefined && token !== "") {
    return <Outlet />;
  }
  return <Navigate to={"/auth"} />;
};
export default AuthLayout;
