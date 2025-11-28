package com.pets.dogs.application.port.in;

import java.util.List;
import com.pets.dogs.domain.entity.DogImage;

public interface GetDogImagesUseCase {  
    /**
     * Obtiene una imagen aleatoria de perro
     * @return Imagen aleatoria de perro
     */
    DogImage getRandomImage();
    /**
     * Obtiene una lista de imágenes aleatorias de perro
     * @param limit Número de imágenes a obtener
     * @return Lista de imágenes aleatorias de perro
     */
    List<DogImage> getRandomImages(int limit);
}
