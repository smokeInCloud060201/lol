import React from "react";
import ReactDOM from "react-dom";
import App from "./pages/App";
import {createRoot} from "react-dom/client";

const container = document.getElementById('root');
if (container) {
    const root = createRoot(container);
    root.render(
        <React.StrictMode>
            <App />
        </React.StrictMode>
    );
} else {
    console.error('Root container not found');
}