package com.pets.cats.infrastructure.adapter.out;

import com.pets.cats.application.port.out.CatApiPort;
import com.pets.cats.domain.entity.CatImage;
import com.pets.cats.domain.entity.CatBreed;
import com.pets.cats.domain.exception.CatImageNotFoundException;
import com.pets.cats.domain.exception.CatApiConnectionException;
import com.pets.cats.domain.exception.CatBreedNotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Component
public class CatApiClient implements CatApiPort {
    
    private static final String API_URL = "https://api.thecatapi.com/v1";
    
    private final RestTemplate restTemplate;
    
    @Autowired
    public CatApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Override
    public CatImage getRandomCatImage() {
        try {
            String url = API_URL + "/images/search?limit=1";
            
            ResponseEntity<List<CatImage>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CatImage>>() {}
            );
            
            if (response.getBody() == null || response.getBody().isEmpty()) {
                throw new CatImageNotFoundException("No se encontró la imagen aleatoria de gato");
            }
            
            return response.getBody().get(0);
            
        } catch (RestClientException e) {
            throw new CatApiConnectionException("Error al obtener imagen aleatoria: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CatImage> getRandomCatImages(int limit) {
        try {
            if (limit <= 0 || limit > 50){
                throw new IllegalArgumentException("El límite debe ser mayor a 0 y menor o igual a 50");
            }
            
            String url = API_URL + "/images/search?limit=" + limit;
            
            ResponseEntity<List<CatImage>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CatImage>>() {}
            );
            
            if (response.getBody() == null || response.getBody().isEmpty()) {
                throw new CatImageNotFoundException("No se encontraron imágenes aleatorias");
            }
            
            return response.getBody();
            
        } catch (RestClientException e) {
            throw new CatApiConnectionException("Error al obtener imágenes aleatorias: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CatBreed> getAllBreeds() {
        try {
            String url = API_URL + "/breeds";
            
            ResponseEntity<List<CatBreed>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CatBreed>>() {}
            );
            
            if (response.getBody() == null || response.getBody().isEmpty()) {
                throw new CatBreedNotFoundException("No se encontraron razas de gatos");
            }
            
            return response.getBody();
            
        } catch (RestClientException e) {
            throw new CatApiConnectionException("Error al obtener razas: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CatImage> getImagesByBreed(String breed, int limit) {
        try {
            if (limit <= 0 || limit > 50) {
                throw new IllegalArgumentException("El límite debe ser mayor a 0 y menor o igual a 50");
            }

            List<CatBreed> allBreeds = getAllBreeds();

            CatBreed foundBreed = allBreeds.stream()
                .filter(b -> (b.getId() != null && b.getId().equals(breed)) || 
                            (b.getName() != null && b.getName().equalsIgnoreCase(breed)))
                .findFirst()
                .orElseThrow(() -> new CatBreedNotFoundException("No se encontró la raza: " + breed));

            if (foundBreed.getId() == null) {
                throw new CatBreedNotFoundException("La raza encontrada no tiene ID válido: " + breed);
            }
            
            return getImagesByBreedId(foundBreed.getId(), limit);
        } catch (CatBreedNotFoundException e) {
            throw e;
        } catch (RestClientException e) {
            throw new CatApiConnectionException("Error al obtener imágenes por raza: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CatBreed getBreedById(String breedId) {
        try {
            String url = API_URL + "/breeds/" + breedId;
            
            CatBreed breed = restTemplate.getForObject(url, CatBreed.class);
            
            if (breed == null) {
                throw new CatBreedNotFoundException("No se encontró la raza con ID: " + breedId);
            }
            
            return breed;
            
        } catch (RestClientException e) {
            throw new CatApiConnectionException("Error al obtener raza por ID: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CatImage> getImagesByBreedId(String breedId, int limit) {
        try {
            if (limit <= 0 || limit > 50) {
                throw new IllegalArgumentException("El límite debe ser mayor a 0 y menor o igual a 50");
            }
            
            String url = API_URL + "/images/search?breed_ids=" + breedId + "&limit=" + limit;
            
            ResponseEntity<List<CatImage>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CatImage>>() {}
            );
            
            if (response.getBody() == null || response.getBody().isEmpty()) {
                throw new CatImageNotFoundException("No se encontraron imágenes para la raza con ID: " + breedId);
            }
            
            return response.getBody();
            
        } catch (RestClientException e) {
            throw new CatApiConnectionException("Error al obtener imágenes por ID de raza: " + e.getMessage(), e);
        }
    }
}