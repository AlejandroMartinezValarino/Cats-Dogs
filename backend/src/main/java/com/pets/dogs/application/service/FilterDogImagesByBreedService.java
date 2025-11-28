package com.pets.dogs.application.service;

import org.springframework.stereotype.Service;
import com.pets.dogs.application.port.in.FilterDogImageByBreedUseCase;
import com.pets.dogs.domain.entity.DogImage;
import java.util.List;
import com.pets.dogs.application.port.out.DogApiPort;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FilterDogImagesByBreedService implements FilterDogImageByBreedUseCase {
    private final DogApiPort dogApiPort;

    @Autowired
    public FilterDogImagesByBreedService(DogApiPort dogApiPort) {
        this.dogApiPort = dogApiPort;
    }
    @Override
    public List<DogImage> filterImagesByBreed(String breed) {
        return dogApiPort.getImagesByBreed(breed);
    }    
    
    @Override
    public List<DogImage> filterImagesBySubBreed(String breed, String subBreed) {
        return dogApiPort.getImagesBySubBreed(breed, subBreed);
    }
}
