package com.pets.cats.application.port.out;

import com.pets.cats.domain.entity.CatImage;
import com.pets.cats.domain.entity.CatBreed;
import java.util.List;

public interface CatApiPort {

    /**
     * Obtiene una imagen aleatoria de un gato
     * @return Imagen aleatoria de un gato
     */
    CatImage getRandomCatImage();

    /**
     * Obtiene una lista de imágenes aleatorias de gatos
     * @param limit Número de imágenes a obtener
     * @return Lista de imágenes aleatorias de gatos
     */
    List<CatImage> getRandomCatImages(int limit);

    /**
     * Obtiene todas las razas de gatos
     * @return Lista de razas de gatos
     */
    List<CatBreed> getAllBreeds();

    /**
     * Obtiene una lista de imágenes de un gato por raza
     * @param breed Raza de gato
     * @param limit Número de imágenes a obtener
     * @return Lista de imágenes de un gato por raza
     */
    List<CatImage> getImagesByBreed(String breed, int limit);

    /**
     * Obtiene una lista de imágenes de un gato por id de raza
     * @param breedId Id de la raza de gato
     * @return Raza de gato por id
     */
    CatBreed getBreedById(String breedId);

    /**
     * Obtiene una lista de imágenes de un gato por raza
     * @param breedId Id de la raza de gato por id
     * @param limit Número de imágenes a obtener
     * @return Lista de imágenes de un gato por raza
     */
    List<CatImage> getImagesByBreedId(String breedId, int limit);

}
