package com.pets.cats.infrastructure.adapter.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.pets.cats.application.port.in.GetCatImagesUseCase;
import com.pets.cats.application.port.in.GetCatBreedsUseCase;
import com.pets.cats.domain.entity.CatImage;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.pets.cats.domain.entity.CatBreed;

@RestController
@RequestMapping("/api/cats")
public class CatController {

    private final GetCatImagesUseCase getCatImagesUseCase;
    private final GetCatBreedsUseCase getCatBreedsUseCase;

    @Autowired
    public CatController(GetCatImagesUseCase getCatImagesUseCase, GetCatBreedsUseCase getCatBreedsUseCase) {
        this.getCatImagesUseCase = getCatImagesUseCase;
        this.getCatBreedsUseCase = getCatBreedsUseCase;
    }

    @GetMapping("/images/random")
    public ResponseEntity<CatImage> getRandomCatImage() {
        return ResponseEntity.ok(getCatImagesUseCase.getRandomCatImage());
    }

    @GetMapping("/images/random/list")
    public ResponseEntity<List<CatImage>> getRandomCatImages(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(getCatImagesUseCase.getRandomCatImages(limit));
    }

    @GetMapping("/breeds")
    public ResponseEntity<List<CatBreed>> getAllCatBreeds() {
        return ResponseEntity.ok(getCatBreedsUseCase.getAllCatBreeds());
    }

    @GetMapping("/breeds/{breedId}")
    public ResponseEntity<CatBreed> getBreedById(@PathVariable String breedId) {
        return ResponseEntity.ok(getCatBreedsUseCase.getBreedById(breedId));
    }

    @GetMapping("/images")
    public ResponseEntity<List<CatImage>> filterImagesByBreed(@RequestParam String breed, @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(getCatImagesUseCase.getImagesByBreed(breed, limit));
    }
}
