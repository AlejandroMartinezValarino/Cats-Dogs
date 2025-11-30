import { PET_TYPES } from '../utils/constants';
import * as dogService from './dogService';
import * as catService from './catService';

/**
 * Servicio unificado para interactuar con los endpoints de perros y gatos
 * Facilita el cambio entre tipos de mascota
 */

/**
 * Obtiene imágenes aleatorias según el tipo de mascota
 * @param {string} petType - 'dogs' o 'cats'
 * @param {number} limit - Número de imágenes
 * @returns {Promise<Array>}
 */
export const getRandomImages = async (petType, limit = 10) => {
  if (petType === PET_TYPES.DOGS) {
    return await dogService.getRandomDogImages(limit);
  } else if (petType === PET_TYPES.CATS) {
    return await catService.getRandomCatImages(limit);
  }
  throw new Error(`Tipo de mascota no válido: ${petType}`);
};

/**
 * Obtiene todas las razas disponibles según el tipo de mascota
 * @param {string} petType - 'dogs' o 'cats'
 * @returns {Promise<Array>}
 */
export const getBreeds = async (petType) => {
  if (petType === PET_TYPES.DOGS) {
    return await dogService.getDogBreeds();
  } else if (petType === PET_TYPES.CATS) {
    return await catService.getCatBreeds();
  }
  throw new Error(`Tipo de mascota no válido: ${petType}`);
};

/**
 * Obtiene imágenes filtradas por raza según el tipo de mascota
 * @param {string} petType - 'dogs' o 'cats'
 * @param {string} breed - Nombre o ID de la raza
 * @param {number} limit - Número de imágenes (solo para gatos)
 * @returns {Promise<Array>}
 */
export const getImagesByBreed = async (petType, breed, limit = 10) => {
  if (petType === PET_TYPES.DOGS) {
    return await dogService.getDogImagesByBreed(breed);
  } else if (petType === PET_TYPES.CATS) {
    return await catService.getCatImagesByBreed(breed, limit);
  }
  throw new Error(`Tipo de mascota no válido: ${petType}`);
};

/**
 * Obtiene detalles de una raza específica
 * @param {string} petType - 'dogs' o 'cats'
 * @param {string} breedId - ID o nombre de la raza
 * @returns {Promise<Object>}
 */
export const getBreedDetails = async (petType, breedId) => {
  if (petType === PET_TYPES.DOGS) {
    // Para perros, no hay endpoint de detalles individual, devolvemos null
    // o podrías buscar en la lista de razas
    const breeds = await dogService.getDogBreeds();
    return breeds.find(b => b.breed === breedId) || null;
  } else if (petType === PET_TYPES.CATS) {
    return await catService.getCatBreedById(breedId);
  }
  throw new Error(`Tipo de mascota no válido: ${petType}`);
};

// Exportar también los servicios individuales por si se necesitan
export { dogService, catService };

