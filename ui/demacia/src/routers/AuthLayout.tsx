import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { getParams } from "../utils/storage.util";
import { PARAMETERS } from "../constant";

const AuthLayout = () => {
  const token = getParams(PARAMETERS.DEMACIA_ACCESS_TOKEN);
  if (token !== undefined && token !== "") {
    return <Outlet />;
  }
  return <Navigate to={"/auth"} />;
};
export default AuthLayout;
