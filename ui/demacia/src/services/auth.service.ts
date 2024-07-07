import nautilusApi from "./nautilus.base";
import { ILoginRequest } from "../interface/IAuth";

const login = async (request: ILoginRequest) => {
  return nautilusApi.post("/api/v1/auth/login", {
    email: request.email,
    password: request.password,
  });
};

export const authService = {
  login,
};
