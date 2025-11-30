import apiClient from './api';
import { API_BASE } from '../utils/constants';

/**
 * Servicio para interactuar con los endpoints de perros del backend
 */

/**
 * Obtiene una imagen aleatoria de perro
 * @returns {Promise<{url: string, breed: string|null}>}
 */
export const getRandomDogImage = async () => {
  const response = await apiClient.get(`${API_BASE.DOGS}/images/random`);
  return response.data;
};

/**
 * Obtiene múltiples imágenes aleatorias de perros
 * @param {number} limit - Número de imágenes a obtener
 * @returns {Promise<Array<{url: string, breed: string|null}>>}
 */
export const getRandomDogImages = async (limit = 10) => {
  const response = await apiClient.get(`${API_BASE.DOGS}/images/random/${limit}`);
  return response.data;
};

/**
 * Obtiene todas las razas de perros disponibles
 * @returns {Promise<Array<{breed: string, subBreed: string[]}>>}
 */
export const getDogBreeds = async () => {
  const response = await apiClient.get(`${API_BASE.DOGS}/breeds`);
  return response.data;
};

/**
 * Obtiene imágenes de perros filtradas por raza
 * @param {string} breed - Nombre de la raza
 * @returns {Promise<Array<{url: string, breed: string}>>}
 */
export const getDogImagesByBreed = async (breed) => {
  const response = await apiClient.get(`${API_BASE.DOGS}/breeds/${breed}`);
  return response.data;
};

/**
 * Obtiene imágenes de perros filtradas por subraza
 * @param {string} breed - Nombre de la raza
 * @param {string} subBreed - Nombre de la subraza
 * @returns {Promise<Array<{url: string, breed: string}>>}
 */
export const getDogImagesBySubBreed = async (breed, subBreed) => {
  const response = await apiClient.get(`${API_BASE.DOGS}/breeds/${breed}/${subBreed}`);
  return response.data;
};

