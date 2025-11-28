package com.pets.dogs.infrastructure.adapter.in;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pets.dogs.application.port.in.GetDogImagesUseCase;
import com.pets.dogs.application.port.in.GetDogBreedsUseCase;
import com.pets.dogs.application.port.in.FilterDogImageByBreedUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.pets.dogs.domain.entity.DogImage;
import com.pets.dogs.domain.entity.DogBreed;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    private final GetDogImagesUseCase getDogImagesUseCase;
    private final GetDogBreedsUseCase getDogBreedsUseCase;
    private final FilterDogImageByBreedUseCase filterDogImageByBreedUseCase;

    @Autowired
    public DogController(GetDogImagesUseCase getDogImagesUseCase, GetDogBreedsUseCase getDogBreedsUseCase, FilterDogImageByBreedUseCase filterDogImageByBreedUseCase) {
        this.getDogImagesUseCase = getDogImagesUseCase;
        this.getDogBreedsUseCase = getDogBreedsUseCase;
        this.filterDogImageByBreedUseCase = filterDogImageByBreedUseCase;
    }

    /**
     * Obtiene una imagen aleatoria de un perro
     * @return Imagen aleatoria de un perro
     */
    @GetMapping("images/random")
    public ResponseEntity<DogImage> getRandomImage() {
        return ResponseEntity.ok(getDogImagesUseCase.getRandomImage());
    }

    /**
     * Obtiene una lista de imágenes aleatorias de perros
     * @param limit Número de imágenes a obtener
     * @return Lista de imágenes aleatorias de perros
     */
    @GetMapping("images/random/{limit}")
    public ResponseEntity<List<DogImage>> getRandomImages(@PathVariable int limit) {
        return ResponseEntity.ok(getDogImagesUseCase.getRandomImages(limit));
    }

    /**
     * Obtiene una lista de razas de perros
     * @return Lista de razas de perros
     */
    @GetMapping("breeds")
    public ResponseEntity<List<DogBreed>> getDogBreeds() {
        return ResponseEntity.ok(getDogBreedsUseCase.getAllBreeds());
    }

    /**
     * Obtiene una lista de imágenes de un perro por raza
     * @param breed Raza de perro
     * @return Lista de imágenes de un perro por raza
     */
    @GetMapping("breeds/{breed}")
    public ResponseEntity<List<DogImage>> getDogImagesByBreed(@PathVariable String breed) {
        return ResponseEntity.ok(filterDogImageByBreedUseCase.filterImagesByBreed(breed));
    }

    /**
     * Obtiene una lista de imágenes de un perro por subraza
     * @param breed Raza de perro
     * @param subBreed Subraza de perro
     * @return Lista de imágenes de un perro por subraza
     */
    @GetMapping("breeds/{breed}/{subBreed}")
    public ResponseEntity<List<DogImage>> getDogImagesBySubBreed(@PathVariable String breed, @PathVariable String subBreed) {
        return ResponseEntity.ok(filterDogImageByBreedUseCase.filterImagesBySubBreed(breed, subBreed));
    }
}
