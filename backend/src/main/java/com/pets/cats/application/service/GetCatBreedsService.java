package com.pets.cats.application.service;

import com.pets.cats.application.port.in.GetCatBreedsUseCase;
import com.pets.cats.domain.entity.CatBreed;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pets.cats.application.port.out.CatApiPort;

@Service
public class GetCatBreedsService implements GetCatBreedsUseCase {

    private final CatApiPort catApiPort;

    @Autowired
    public GetCatBreedsService(CatApiPort catApiPort) {
        this.catApiPort = catApiPort;
    }

    @Override
    public List<CatBreed> getAllCatBreeds() {
        return catApiPort.getAllBreeds();
    }

    @Override
    public CatBreed getBreedById(String breedId) {
        return catApiPort.getBreedById(breedId);
    }
}
