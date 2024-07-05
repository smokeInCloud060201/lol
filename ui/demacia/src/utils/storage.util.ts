export const storeParams = (key: string, value: any) => {
  localStorage.setItem(key, value);
};

export const getParams = (key: string) => {
  return JSON.parse(localStorage.getItem(key) ?? "");
};
