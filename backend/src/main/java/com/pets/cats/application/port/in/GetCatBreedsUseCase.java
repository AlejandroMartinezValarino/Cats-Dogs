package com.pets.cats.application.port.in;

import java.util.List;
import com.pets.cats.domain.entity.CatBreed;

public interface GetCatBreedsUseCase {
    /**
     * Obtiene todas las razas de gato
     * @return Lista de razas de gato
     */
    List<CatBreed> getAllCatBreeds();

    /**
     * Obtiene una raza de gato por su ID
     * @param id ID de la raza de gato
     * @return Raza de gato
     */
    CatBreed getBreedById(String breedId);

}
