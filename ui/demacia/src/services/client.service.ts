import nautilusApi from "./nautilus.base";

const getClients = async () => {
  return nautilusApi.get("/api/v1/clients");
};

export const clientService = {};
