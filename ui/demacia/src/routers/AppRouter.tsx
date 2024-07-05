import React from "react";
import { Route, Routes } from "react-router-dom";
import AuthLayout from "./AuthLayout";
import HomePage from "../pages/home/HomePage";
import Authentication from "../pages/auth/Authentication";

const AppRouter: React.FC = () => {
  return (
    <Routes>
      <Route path={"/auth"} element={<Authentication />} />
      <Route element={<AuthLayout />}>
        <Route path={"/dashboard"} element={<HomePage />} />
        <Route path={"*"} element={<HomePage />} />
      </Route>
    </Routes>
  );
};

export default AppRouter;
