package com.pets.cats.application.port.in;

import java.util.List;
import com.pets.cats.domain.entity.CatImage;

public interface GetCatImagesUseCase {
    /**
     * Obtiene una imagen aleatoria de gato
     * @return Imagen de gato aleatoria
     */
    CatImage getRandomCatImage();

    /**
     * Obtiene múltiples imágenes aleatorias de gato
     * @param limit Número de imágenes a obtener
     * @return Lista de imágenes de gato aleatorias
     */
    List<CatImage> getRandomCatImages(int limit);

    /**
     * Obtiene imágenes de gato por raza
     * @param breed Raza de gato
     * @param limit Número de imágenes a obtener
     * @return Lista de imágenes de gato por raza
     */
    List<CatImage> getImagesByBreed(String breed, int limit);

}
