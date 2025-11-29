package com.pets.cats.application.service;

import com.pets.cats.application.port.in.GetCatImagesUseCase;
import com.pets.cats.domain.entity.CatImage;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pets.cats.application.port.out.CatApiPort;


@Service
public class GetCatImagesService implements GetCatImagesUseCase {

    private final CatApiPort catApiPort;

    @Autowired
    public GetCatImagesService(CatApiPort catApiPort) {
        this.catApiPort = catApiPort;
    }

    @Override
    public CatImage getRandomCatImage() {
        return catApiPort.getRandomCatImage();
    }

    @Override
    public List<CatImage> getRandomCatImages(int limit) {
        return catApiPort.getRandomCatImages(limit);
    }

    @Override
    public List<CatImage> getImagesByBreed(String breed, int limit) {
        return catApiPort.getImagesByBreed(breed, limit);
    }
}
