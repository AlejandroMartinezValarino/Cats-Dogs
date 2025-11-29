package com.pets.cats.application.service;

import com.pets.cats.application.port.in.FilterCatImagesByBreedUseCase;
import com.pets.cats.domain.entity.CatImage;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pets.cats.application.port.out.CatApiPort;

@Service
public class FilterCatImagesByBreedService implements FilterCatImagesByBreedUseCase {

    private final CatApiPort catApiPort;

    @Autowired
    public FilterCatImagesByBreedService(CatApiPort catApiPort) {
        this.catApiPort = catApiPort;
    }

    @Override
    public List<CatImage> filterImagesByBreed(String breed) {
        return catApiPort.getImagesByBreed(breed, 50);
    }
}
