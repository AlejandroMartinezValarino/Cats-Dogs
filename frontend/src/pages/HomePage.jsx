import React from 'react';
import { Typography } from 'antd';

const { Title } = Typography;

const HomePage = () => {
  return (
    <div style={{ padding: '24px' }}>
      <Title level={1}>Pet Gallery</Title>
      <Title level={3}>Galería de Perros y Gatos</Title>
      <p>Esta es la página principal. Aquí se implementará la funcionalidad de visualización de imágenes.</p>
    </div>
  );
};

export default HomePage;

