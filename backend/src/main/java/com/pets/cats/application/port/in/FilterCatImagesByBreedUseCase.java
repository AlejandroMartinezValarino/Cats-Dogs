package com.pets.cats.application.port.in;

import java.util.List;
import com.pets.cats.domain.entity.CatImage;

public interface FilterCatImagesByBreedUseCase {
    /**
     * Filtra las imágenes de gato por raza
     * @param breed Raza de gato
     * @return Lista de imágenes de gato por raza
     */
    List<CatImage> filterImagesByBreed(String breed);
}
