import React from 'react';
import { createRoot } from 'react-dom/client';
import App from './pages/app/App';
import { Provider } from 'react-redux';
import { store } from './store/store';
import './styles/index.scss';
import { BrowserRouter } from 'react-router-dom';

const rootElement = document.getElementById('root');
if (!rootElement) throw new Error('Failed to find the root element');
const root = createRoot(rootElement);
root.render(
    <React.StrictMode>
        <Provider store={store}>
            <BrowserRouter>
                <App />
            </BrowserRouter>
        </Provider>
    </React.StrictMode>
);
