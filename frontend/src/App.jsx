import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Layout } from 'antd';
import HomePage from './pages/HomePage';
import './App.css';

const { Content } = Layout;

function App() {
  return (
    <Router>
      <Layout style={{ minHeight: '100vh' }}>
        <Content>
          <Routes>
            <Route path="/" element={<HomePage />} />
          </Routes>
        </Content>
      </Layout>
    </Router>
  );
}

export default App;

