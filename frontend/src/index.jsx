import React from 'react';
import ReactDOM from 'react-dom/client';
import { ConfigProvider } from 'antd';
import esES from 'antd/locale/es_ES';
import App from './App';
import './index.css';

const theme = {
  token: {
    colorPrimary: '#FF6B6B',
  },
};

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ConfigProvider locale={esES} theme={theme}>
      <App />
    </ConfigProvider>
  </React.StrictMode>
);

