import React from "react";
import { Route, Routes } from "react-router-dom";
import Authentication from "../pages/auth/Authentication";

const AppRouter: React.FC = () => {
  return (
    <Routes>
      <Route path="/auth" element={<Authentication />} errorElement={<Authentication />} />
    </Routes>
  );
};

export default AppRouter;
