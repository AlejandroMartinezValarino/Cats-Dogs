package com.pets.dogs.application.service;

import org.springframework.stereotype.Service;
import com.pets.dogs.application.port.in.GetDogImagesUseCase;
import com.pets.dogs.domain.entity.DogImage;
import java.util.List;
import com.pets.dogs.application.port.out.DogApiPort;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetDogImagesService implements GetDogImagesUseCase {
    private final DogApiPort dogApiPort;
    
    @Autowired
    public GetDogImagesService(DogApiPort dogApiPort) {
        this.dogApiPort = dogApiPort;
    }
    @Override
    public DogImage getRandomImage() {
        return dogApiPort.getRandomImage();
    }
    @Override
    public List<DogImage> getRandomImages(int limit) {
        return dogApiPort.getRandomImages(limit);
    }

}
