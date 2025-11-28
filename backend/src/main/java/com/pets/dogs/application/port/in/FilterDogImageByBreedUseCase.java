package com.pets.dogs.application.port.in;

import java.util.List;
import com.pets.dogs.domain.entity.DogImage;

public interface FilterDogImageByBreedUseCase {
    /**
     * Filtra las imágenes de perro por raza
     * @param breed Raza de perro
     * @return Lista de imágenes de perro por raza
     */
    List<DogImage> filterImagesByBreed(String breed);
    /**
     * Filtra las imágenes de perro por subraza
     * @param breed Raza de perro
     * @param subBreed Subraza de perro
     * @return Lista de imágenes de perro por subraza
     */
    List<DogImage> filterImagesBySubBreed(String breed, String subBreed);
}
