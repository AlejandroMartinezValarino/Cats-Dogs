package com.pets.dogs.application.port.out;

import java.util.List;

import com.pets.dogs.domain.entity.DogImage;
import com.pets.dogs.domain.entity.DogBreed;

public interface DogApiPort {

    /**
     * Obtiene una lista de imágenes aleatorias de perros
     * @param limit Número de imágenes a obtener
     * @return Lista de imágenes aleatorias de perros
     */
    List<DogImage> getRandomImages(int limit);
    /**
     * Obtiene una imagen aleatoria de perro
     * @return Imagen aleatoria de perro
     */
    DogImage getRandomImage();
    /**
     * Obtiene una lista de todas las razas de perros
     * @return Lista de todas las razas de perros
     */
    List<DogBreed> getAllBreeds();
    /**
     * Obtiene una lista de imágenes de perros por raza
     * @param breed Raza de perro
     * @return Lista de imágenes de perros por raza
     */
    List<DogImage> getImagesByBreed(String breed);

    /**
     * Obtiene una lista de imágenes de perros por raza
     * @param breed Raza de perro
     * @param subBreed Subraza de perro
     * @return Lista de imágenes de perros por raza
     */
    List<DogImage> getImagesBySubBreed(String breed, String subBreed);

}
