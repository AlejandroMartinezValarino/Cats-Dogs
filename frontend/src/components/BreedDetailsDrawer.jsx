import React, { useState, useEffect } from 'react';
import { Drawer, Spin, Empty, Descriptions, Typography, Tag, Space } from 'antd';
import { getBreedDetails } from '../services/petService';
import { PET_TYPES } from '../utils/constants';

const { Title, Text, Paragraph } = Typography;

const BreedDetailsDrawer = ({ visible, onClose, breedId, petType }) => {
  const [breedDetails, setBreedDetails] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (visible && breedId && petType === PET_TYPES.CATS) {
      loadBreedDetails();
    } else {
      setBreedDetails(null);
      setError(null);
    }
  }, [visible, breedId, petType]);

  const loadBreedDetails = async () => {
    setLoading(true);
    setError(null);
    try {
      const details = await getBreedDetails(petType, breedId);
      setBreedDetails(details);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const renderRating = (value) => {
    if (value === null || value === undefined) return 'N/A';
    return `${value}/5`;
  };

  const renderBoolean = (value) => {
    if (value === null || value === undefined) return 'N/A';
    return value === 1 ? 'Sí' : 'No';
  };

  const renderCatDetails = () => {
    if (!breedDetails) return null;

    return (
      <div>
        <Title level={4} style={{ marginBottom: 24 }}>
          {breedDetails.name}
        </Title>

        {breedDetails.description && (
          <Paragraph style={{ marginBottom: 24, fontSize: 16, lineHeight: 1.6 }}>
            {breedDetails.description}
          </Paragraph>
        )}

        <Descriptions column={1} bordered>
          {breedDetails.origin && (
            <Descriptions.Item label="Origen">
              {breedDetails.origin}
            </Descriptions.Item>
          )}

          {breedDetails.lifeSpan && (
            <Descriptions.Item label="Esperanza de vida">
              {breedDetails.lifeSpan}
            </Descriptions.Item>
          )}

          {breedDetails.weight && (
            <Descriptions.Item label="Peso">
              {breedDetails.weight.imperial && breedDetails.weight.metric
                ? `${breedDetails.weight.imperial} lbs / ${breedDetails.weight.metric} kg`
                : breedDetails.weight.imperial || breedDetails.weight.metric || 'N/A'}
            </Descriptions.Item>
          )}

          {breedDetails.temperament && (
            <Descriptions.Item label="Temperamento">
              <Space wrap>
                {breedDetails.temperament.split(',').map((trait, index) => (
                  <Tag key={index} color="blue">
                    {trait.trim()}
                  </Tag>
                ))}
              </Space>
            </Descriptions.Item>
          )}

          {breedDetails.altNames && (
            <Descriptions.Item label="Nombres alternativos">
              {breedDetails.altNames}
            </Descriptions.Item>
          )}

          {breedDetails.countryCodes && (
            <Descriptions.Item label="Códigos de país">
              {breedDetails.countryCodes}
            </Descriptions.Item>
          )}
        </Descriptions>

        <Title level={5} style={{ marginTop: 32, marginBottom: 16 }}>
          Características
        </Title>

        <Descriptions column={2} bordered>
          <Descriptions.Item label="Nivel de adaptabilidad">
            {renderRating(breedDetails.adaptability)}
          </Descriptions.Item>

          <Descriptions.Item label="Nivel de afecto">
            {renderRating(breedDetails.affectionLevel)}
          </Descriptions.Item>

          <Descriptions.Item label="Amigable con niños">
            {renderRating(breedDetails.childFriendly)}
          </Descriptions.Item>

          <Descriptions.Item label="Amigable con perros">
            {renderRating(breedDetails.dogFriendly)}
          </Descriptions.Item>

          <Descriptions.Item label="Nivel de energía">
            {renderRating(breedDetails.energyLevel)}
          </Descriptions.Item>

          <Descriptions.Item label="Aseo">
            {renderRating(breedDetails.grooming)}
          </Descriptions.Item>

          <Descriptions.Item label="Problemas de salud">
            {renderRating(breedDetails.healthIssues)}
          </Descriptions.Item>

          <Descriptions.Item label="Inteligencia">
            {renderRating(breedDetails.intelligence)}
          </Descriptions.Item>

          <Descriptions.Item label="Nivel de muda">
            {renderRating(breedDetails.sheddingLevel)}
          </Descriptions.Item>

          <Descriptions.Item label="Necesidades sociales">
            {renderRating(breedDetails.socialNeeds)}
          </Descriptions.Item>

          <Descriptions.Item label="Amigable con extraños">
            {renderRating(breedDetails.strangerFriendly)}
          </Descriptions.Item>

          <Descriptions.Item label="Vocalización">
            {renderRating(breedDetails.vocalisation)}
          </Descriptions.Item>
        </Descriptions>

        <Title level={5} style={{ marginTop: 32, marginBottom: 16 }}>
          Características físicas
        </Title>

        <Descriptions column={2} bordered>
          <Descriptions.Item label="Interior">
            {renderBoolean(breedDetails.indoor)}
          </Descriptions.Item>

          <Descriptions.Item label="Faldero">
            {renderBoolean(breedDetails.lap)}
          </Descriptions.Item>

          <Descriptions.Item label="Hipoalergénico">
            {renderBoolean(breedDetails.hypoallergenic)}
          </Descriptions.Item>

          <Descriptions.Item label="Sin pelo">
            {renderBoolean(breedDetails.hairless)}
          </Descriptions.Item>

          <Descriptions.Item label="Natural">
            {renderBoolean(breedDetails.natural)}
          </Descriptions.Item>

          <Descriptions.Item label="Raro">
            {renderBoolean(breedDetails.rare)}
          </Descriptions.Item>

          <Descriptions.Item label="Rex">
            {renderBoolean(breedDetails.rex)}
          </Descriptions.Item>

          <Descriptions.Item label="Cola suprimida">
            {renderBoolean(breedDetails.suppressedTail)}
          </Descriptions.Item>

          <Descriptions.Item label="Patas cortas">
            {renderBoolean(breedDetails.shortLegs)}
          </Descriptions.Item>

          <Descriptions.Item label="Experimental">
            {renderBoolean(breedDetails.experimental)}
          </Descriptions.Item>
        </Descriptions>

        {(breedDetails.wikipediaUrl || breedDetails.cfaUrl || breedDetails.vetstreetUrl || breedDetails.vcahospitalsUrl) && (
          <>
            <Title level={5} style={{ marginTop: 32, marginBottom: 16 }}>
              Enlaces de referencia
            </Title>
            <Space direction="vertical">
              {breedDetails.wikipediaUrl && (
                <a href={breedDetails.wikipediaUrl} target="_blank" rel="noopener noreferrer">
                  Wikipedia
                </a>
              )}
              {breedDetails.cfaUrl && (
                <a href={breedDetails.cfaUrl} target="_blank" rel="noopener noreferrer">
                  CFA (Cat Fanciers' Association)
                </a>
              )}
              {breedDetails.vetstreetUrl && (
                <a href={breedDetails.vetstreetUrl} target="_blank" rel="noopener noreferrer">
                  Vetstreet
                </a>
              )}
              {breedDetails.vcahospitalsUrl && (
                <a href={breedDetails.vcahospitalsUrl} target="_blank" rel="noopener noreferrer">
                  VCA Hospitals
                </a>
              )}
            </Space>
          </>
        )}
      </div>
    );
  };

  return (
    <Drawer
      title="Detalles de la raza"
      placement="right"
      onClose={onClose}
      open={visible}
      size="large"
      destroyOnClose
    >
      {loading && (
        <div style={{ textAlign: 'center', padding: '40px 0' }}>
          <Spin size="large" />
        </div>
      )}

      {error && (
        <Empty
          description={
            <span>
              Error al cargar los detalles: {error}
              <br />
              <a onClick={loadBreedDetails} style={{ cursor: 'pointer', color: '#1890ff' }}>
                Reintentar
              </a>
            </span>
          }
        />
      )}

      {!loading && !error && petType === PET_TYPES.CATS && renderCatDetails()}

      {!loading && !error && petType === PET_TYPES.DOGS && (
        <Empty description="Los detalles de razas de perros no están disponibles" />
      )}
    </Drawer>
  );
};

export default BreedDetailsDrawer;

