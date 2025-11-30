import React, { useState, useEffect } from 'react';
import { Spin, Empty, Card, Image } from 'antd';
import { getRandomImages, getImagesByBreed, getBreeds } from '../services/petService';
import { PET_TYPES } from '../utils/constants';
import BreedDetailsDrawer from './BreedDetailsDrawer';
import './ImageGallery.css';

const ImageGallery = ({ petType, selectedBreed }) => {
  const [images, setImages] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [breedName, setBreedName] = useState(null);
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [selectedBreedId, setSelectedBreedId] = useState(null);

  useEffect(() => {
    setBreedName(null);
    setDrawerVisible(false);
    setSelectedBreedId(null);
    loadImages();
    loadBreedName();
  }, [petType, selectedBreed]);

  const loadBreedName = async () => {
    if (!selectedBreed || selectedBreed.trim() === '') {
      setBreedName(null);
      return;
    }

    try {
      const breeds = await getBreeds(petType);
      if (!breeds || breeds.length === 0) {
        setBreedName(null);
        return;
      }

      if (petType === PET_TYPES.DOGS) {
        if (selectedBreed.includes('/')) {
          const [breedId, subBreedId] = selectedBreed.split('/');
          const breed = breeds.find(b => b.breed === breedId);
          if (breed) {
            setBreedName(`${formatBreedName(breedId)} - ${formatBreedName(subBreedId)}`);
          } else {
            setBreedName(null);
          }
        } else {
          const breed = breeds.find(b => b.breed === selectedBreed);
          if (breed) {
            setBreedName(formatBreedName(selectedBreed));
          } else {
            setBreedName(null);
          }
        }
      } else {
        const breed = breeds.find(b => b.id === selectedBreed);
        if (breed) {
          setBreedName(breed.name);
        } else {
          setBreedName(null);
        }
      }
    } catch (err) {
      setBreedName(null);
    }
  };

  const formatBreedName = (name) => {
    return name
      .split('-')
      .map(word => word.charAt(0).toUpperCase() + word.slice(1))
      .join(' ');
  };

  const loadImages = async () => {
    setLoading(true);
    setError(null);
    try {
      let data;
      if (selectedBreed && selectedBreed.trim() !== '') {
        try {
          data = await getImagesByBreed(petType, selectedBreed, 25);
        } catch (breedError) {
          // Si falla al cargar por raza, intentar cargar imágenes aleatorias
          console.warn('Error al cargar imágenes por raza, cargando aleatorias:', breedError);
          data = await getRandomImages(petType, 25);
          setBreedName(null);
        }
      } else {
        data = await getRandomImages(petType, 25);
      }
      setImages(data || []);
    } catch (err) {
      setError(err.message);
      setImages([]);
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
    if (selectedBreed && breedName) {
      return breedName;
    }
    if(petType === PET_TYPES.DOGS){
        return image.breed || null;
    } else {
        return image.breeds?.[0]?.name || null;
    }
  };

  const handleCardClick = (image) => {
    if (petType === PET_TYPES.CATS) {
      const breedId = image.breeds?.[0]?.id || selectedBreed;
      if (breedId) {
        setSelectedBreedId(breedId);
        setDrawerVisible(true);
      }
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
    <>
      <div className="masonry-gallery">
        {images.map((image, index) => {
          const imageUrl = getImageUrl(image);
          const breedInfo = getBreedInfo(image);
          
          return (
            <Card
              key={index}
              hoverable
              className="gallery-item"
              onClick={() => handleCardClick(image)}
              style={{ cursor: petType === PET_TYPES.CATS ? 'pointer' : 'default' }}
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
                      {petType === PET_TYPES.CATS && ' - Click para ver detalles'}
                    </span>
                  ) : null
                }
              />
            </Card>
          );
        })}
      </div>

      <BreedDetailsDrawer
        visible={drawerVisible}
        onClose={() => setDrawerVisible(false)}
        breedId={selectedBreedId}
        petType={petType}
      />
    </>
  );
};

export default ImageGallery;