import apiClient from './api';
import { API_BASE } from '../utils/constants';

/**
 * Servicio para interactuar con los endpoints de gatos del backend
 */

/**
 * Obtiene una imagen aleatoria de gato
 * @returns {Promise<CatImage>}
 */
export const getRandomCatImage = async () => {
  const response = await apiClient.get(`${API_BASE.CATS}/images/random`);
  return response.data;
};

/**
 * Obtiene múltiples imágenes aleatorias de gatos
 * @param {number} limit - Número de imágenes a obtener (default: 10)
 * @returns {Promise<Array<CatImage>>}
 */
export const getRandomCatImages = async (limit = 10) => {
  const response = await apiClient.get(`${API_BASE.CATS}/images/random/list`, {
    params: { limit }
  });
  return response.data;
};

/**
 * Obtiene todas las razas de gatos disponibles
 * @returns {Promise<Array<CatBreed>>}
 */
export const getCatBreeds = async () => {
  const response = await apiClient.get(`${API_BASE.CATS}/breeds`);
  return response.data;
};

/**
 * Obtiene una raza de gato por su ID
 * @param {string} breedId - ID de la raza
 * @returns {Promise<CatBreed>}
 */
export const getCatBreedById = async (breedId) => {
  const response = await apiClient.get(`${API_BASE.CATS}/breeds/${breedId}`);
  return response.data;
};

/**
 * Obtiene imágenes de gatos filtradas por raza
 * @param {string} breed - ID o nombre de la raza
 * @param {number} limit - Número de imágenes a obtener (default: 10)
 * @returns {Promise<Array<CatImage>>}
 */
export const getCatImagesByBreed = async (breed, limit = 10) => {
  const response = await apiClient.get(`${API_BASE.CATS}/images`, {
    params: { breed, limit }
  });
  return response.data;
};

