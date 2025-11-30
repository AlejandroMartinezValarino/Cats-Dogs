import React, { useState, useEffect } from 'react';
import { Spin, Empty, Card, Image } from 'antd';
import { getRandomImages, getImagesByBreed } from '../services/petService';
import { PET_TYPES } from '../utils/constants';
import './ImageGallery.css';

const ImageGallery = ({ petType, selectedBreed }) => {
  const [images, setImages] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadImages();
  }, [petType, selectedBreed]);

  const loadImages = async () => {
    setLoading(true);
    setError(null);
    try {
      let data;
      if (selectedBreed && selectedBreed.trim() !== '') {
        data = await getImagesByBreed(petType, selectedBreed, 25);
      } else {
        data = await getRandomImages(petType, 25);
      }
      setImages(data);
    } catch (err) {
      setError(err.message);
      console.error('Error completo:', err);
    } finally {
      setLoading(false);
    }
  };

  const getImageUrl = (image) => {
    if(petType === PET_TYPES.DOGS){
        return image.url;
    } else {
        return image.url || image?.urls?.regular || image?.urls?.small;
    }
  };

  const getBreedInfo = (image) => {
    if(petType === PET_TYPES.DOGS){
        return image.breed || null;
    } else {
        return image.breeds?.[0]?.name || null;
    }
};
if(loading){
    return <Spin />;
}
if (error) {
    return (
      <Empty
        description={
          <span>
            Error al cargar las imágenes: {error}
            <br />
            <a onClick={loadImages} style={{ cursor: 'pointer', color: '#1890ff' }}>
              Reintentar
            </a>
          </span>
        }
      />
    );
  }
if(images.length === 0){
    return <Empty description="No se encontraron imágenes" />;
}
return (
    <div className="masonry-gallery">
      {images.map((image, index) => {
        const imageUrl = getImageUrl(image);
        const breedInfo = getBreedInfo(image);
        
        return (
          <Card
            key={index}
            hoverable
            className="gallery-item"
            cover={
              <Image
                src={imageUrl}
                alt={breedInfo}
                loading="lazy"
                style={{
                  width: '100%',
                  height: 'auto',
                  objectFit: 'cover'
                }}
                preview={{
                  mask: 'Ver más'
                }}
              />
            }
          >
            <Card.Meta
              title={breedInfo || 'Imagen aleatoria'}
              description={
                breedInfo ? (
                  <span style={{ fontSize: '12px', color: '#8c8c8c' }}>
                    {petType === PET_TYPES.DOGS ? 'Perro' : 'Gato'}
                  </span>
                ) : null
              }
            />
          </Card>
        );
      })}
    </div>
  );
};

export default ImageGallery;