package com.pets.dogs.application.service;

import org.springframework.stereotype.Service;
import com.pets.dogs.application.port.in.GetDogBreedsUseCase;
import com.pets.dogs.domain.entity.DogBreed;
import java.util.List;
import com.pets.dogs.application.port.out.DogApiPort;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetDogBreedsService implements GetDogBreedsUseCase {
    private final DogApiPort dogApiPort;

    @Autowired
    public GetDogBreedsService(DogApiPort dogApiPort) {
        this.dogApiPort = dogApiPort;
    }
    
    @Override
    public List<DogBreed> getAllBreeds() {
        return dogApiPort.getAllBreeds();
    }

}
