import React, { useState } from 'react';
import { Layout, Radio, Typography } from 'antd';
import { FaDog, FaCat } from 'react-icons/fa';
import { PET_TYPES } from '../utils/constants';
import ImageGallery from '../components/ImageGallery';

const { Header, Sider, Content } = Layout;
const { Title } = Typography;

// Paleta de colores
const colors = {
  primary: '#FF6B6B',
  primaryHover: '#FF5252',
  background: '#FAFAFA',
  surface: '#FFFFFF',
  sidebar: '#F8F9FA',
  textPrimary: '#2C3E50',
  textSecondary: '#7F8C8D',
  shadow: 'rgba(0, 0, 0, 0.08)',
  border: '#E1E8ED',
};

const HomePage = () => {
  const [petType, setPetType] = useState(PET_TYPES.DOGS);
  const [collapsed, setCollapsed] = useState(false);
  const [selectedBreed, setSelectedBreed] = useState(null);

  return (
    <Layout style={{ minHeight: '100vh', background: colors.background }}>
      {/* HEADER */}
      <Header style={{ 
        background: colors.surface, 
        padding: '0 24px',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
        boxShadow: `0 2px 8px ${colors.shadow}`,
        borderBottom: `1px solid ${colors.border}`
      }}>
        <Title level={3} style={{ 
          margin: 0, 
          color: colors.primary,
          fontWeight: 600
        }}>
          🐾 Galería de Perros y Gatos
        </Title>
        <Radio.Group
          value={petType}
          onChange={(e) => setPetType(e.target.value)}
          buttonStyle="solid"
          size="large"
        >
          <Radio.Button 
            value={PET_TYPES.DOGS}
            style={{
              background: petType === PET_TYPES.DOGS ? colors.primary : 'transparent',
              borderColor: colors.primary,
              color: petType === PET_TYPES.DOGS ? '#fff' : colors.textPrimary
            }}
          >
            <FaDog style={{ marginRight: 6 }} />
            Perros
          </Radio.Button>
          <Radio.Button 
            value={PET_TYPES.CATS}
            style={{
              background: petType === PET_TYPES.CATS ? colors.primary : 'transparent',
              borderColor: colors.primary,
              color: petType === PET_TYPES.CATS ? '#fff' : colors.textPrimary
            }}
          >
            <FaCat style={{ marginRight: 6 }} />
            Gatos
          </Radio.Button>
        </Radio.Group>
      </Header>

      <Layout>
        {/* SIDEBAR */}
        <Sider
          width={300}
          collapsible
          collapsed={collapsed}
          onCollapse={setCollapsed}
          style={{
            background: colors.sidebar,
            overflow: 'auto',
            height: 'calc(100vh - 64px)',
            position: 'fixed',
            left: 0,
            top: 64,
            boxShadow: `2px 0 8px ${colors.shadow}`,
            borderRight: `1px solid ${colors.border}`
          }}
        >
          <div style={{ padding: '16px' }}>
            <Title level={5} style={{ color: colors.textPrimary, marginBottom: 16 }}>
              Razas disponibles
            </Title>
            {/* Aquí irá el componente de búsqueda y lista de razas */}
            <p style={{ color: colors.textSecondary }}>Lista de razas aquí...</p>
          </div>
        </Sider>

        {/* CONTENIDO */}
        <Layout style={{ 
          marginLeft: collapsed ? 80 : 300, 
          transition: 'margin-left 0.2s',
          background: colors.background
        }}>
          <Content style={{
            margin: '24px',
            padding: '24px',
            background: colors.surface,
            borderRadius: 8,
            minHeight: 'calc(100vh - 112px)',
            boxShadow: `0 1px 3px ${colors.shadow}`
          }}>
            <Title level={4} style={{ color: colors.textPrimary, marginBottom: 16 }}>
              Galería de {petType === PET_TYPES.DOGS ? 'Perros' : 'Gatos'}
            </Title>
            <ImageGallery petType={petType} selectedBreed={selectedBreed} />
          </Content>
        </Layout>
      </Layout>
    </Layout>
  );
};

export default HomePage;