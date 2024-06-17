import React from 'react';
import {createRoot} from 'react-dom/client';
import App from './pages/app/App';
import {Provider} from "react-redux";
import {store} from "./store/store";
import './styles/index.scss'

const rootElement = document.querySelector('#root');
if (!rootElement) throw new Error('Failed to find the root element');
const root = createRoot(rootElement);
root.render(
    <React.StrictMode>
        <Provider store={store}>
            <App/>
        </Provider>
    </React.StrictMode>
);