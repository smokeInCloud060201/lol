import React from "react";
import { Route, Routes } from "react-router-dom";
import PrivateRoute from "./PrivateRoute";
import HomePage from "../pages/home/HomePage";
import Authentication from "../pages/auth/Authentication";
import HomeLayout from "../layouts/home/HomeLayout";

const AppRouter: React.FC = () => {
  const protectedRoutes = [{ path: "/dashboard", component: HomePage, layout: HomeLayout }];
  return (
    <Routes>
      <Route path="/login" element={<Authentication />} />
      {protectedRoutes.map(({ path, component: Component, layout: Layout }) => (
        <Route
          key={path}
          path={path}
          element={
            <PrivateRoute>
              <Layout>
                <Component />
              </Layout>
            </PrivateRoute>
          }
        />
      ))}
      <Route path="/" element={<HomePage />} />
    </Routes>
  );
};

export default AppRouter;
