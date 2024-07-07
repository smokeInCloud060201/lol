export const setItems = (key: string, value: any) => {
  localStorage.setItem(key, value);
};

export const getItems = (key: string) => {
  return localStorage.getItem(key);
};
