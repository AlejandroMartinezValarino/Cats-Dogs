package com.pets.dogs.application.port.in;

import java.util.List;
import com.pets.dogs.domain.entity.DogBreed;

public interface GetDogBreedsUseCase {
    /**
     * Obtiene una lista de razas de perro
     * @return Lista de razas de perro
     */
    List<DogBreed> getAllBreeds();

}
