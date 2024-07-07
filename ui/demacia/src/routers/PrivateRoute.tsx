import React from "react";
import { Navigate } from "react-router-dom";
import { getItems } from "../utils/storage.util";
import { PARAMETERS } from "../constant";

const PrivateRoute = ({ children }: any) => {
  const isAuthenticated = getItems(PARAMETERS.DEMACIA_ACCESS_TOKEN) != null;
  return isAuthenticated ? children : <Navigate to="/login" />;
};
export default PrivateRoute;
