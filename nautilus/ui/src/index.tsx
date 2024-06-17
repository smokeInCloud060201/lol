import React from "react";
import App from "./components/App";
import {createRoot} from "react-dom";

const container = document.getElementById("root");
const root = createRoot(container!);
// @ts-ignore
root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);