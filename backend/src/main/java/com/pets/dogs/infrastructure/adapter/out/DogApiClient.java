package com.pets.dogs.infrastructure.adapter.out;

import java.util.List;

import com.pets.dogs.application.port.out.DogApiPort;
import com.pets.dogs.domain.entity.DogImage;
import com.pets.dogs.domain.entity.DogBreed;
import com.pets.dogs.domain.exception.DogImageNotFoundException;
import com.pets.dogs.domain.exception.DogApiConnectionException;
import com.pets.dogs.domain.exception.DogBreedNotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;

import java.util.stream.Collectors;


@Component
public class DogApiClient implements DogApiPort{
    private final String API_URL = "https://dog.ceo/api/breeds";

    private final RestTemplate restTemplate;

    @Autowired
    public DogApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<DogImage> getRandomImages(int limit) {
        try{
            if (limit <= 0 || limit > 50) {
                throw new IllegalArgumentException("El límite debe ser mayor a 0 y menor a 51");
            }
            String url = API_URL + "/image/random/" + limit;
            DogApiListResponse response = restTemplate.getForObject(url, DogApiListResponse.class);
            if (response == null || response.getMessageList() == null) {
                throw new DogBreedNotFoundException("vacío");
            }
            return response.getMessageList().stream()
            .map(u -> DogImage.builder()
            .url(u)
            .build())
            .collect(Collectors.toList());
            
        } catch (RestClientException e) {
            throw new DogApiConnectionException( e.getMessage());
        }
    }

    @Override
    public DogImage getRandomImage() {  
        try{
            String url = API_URL + "/image/random";
            DogApiResponse response = restTemplate.getForObject(url, DogApiResponse.class);
            if (response == null || response.getMessage() == null) {
                throw new DogImageNotFoundException("No se encontró la imagen aleatoria de perro");
            }
            return DogImage.builder()
            .url(response.getMessage())
            .build();
        } catch (RestClientException e) {
            throw new DogApiConnectionException( e.getMessage());
        }
    }

    @Override
    public List<DogBreed> getAllBreeds() {   
        try{ 
            String url = API_URL + "/list/all";
            DogApiBreedsResponse response = restTemplate.getForObject(url, DogApiBreedsResponse.class);
            if (response == null || response.getMessage() == null) {
                throw new DogBreedNotFoundException("vacío");
            }
            return response.getMessage().entrySet().stream()
            .map(u -> DogBreed.builder()
            .breed(u.getKey())
            .subBreed(u.getValue().toArray(new String[0]))
            .build())
            .collect(Collectors.toList());
        } catch (RestClientException e) {
            throw new DogApiConnectionException( e.getMessage());
        }
    }

    @Override
    public List<DogImage> getImagesByBreed(String breed) {
        try{
            String url = API_URL + "/images/breed/" + breed + "/images";
            DogApiListResponse response = restTemplate.getForObject(url, DogApiListResponse.class);
            if (response == null || response.getMessageList() == null) {
                throw new DogImageNotFoundException("No se encontraron imágenes de perro por raza");
            }
            return response.getMessageList().stream()
            .map(u -> DogImage.builder()
            .url(u)
            .build())
            .collect(Collectors.toList());
        } catch (RestClientException e) {
            throw new DogApiConnectionException( e.getMessage());
        }
    }

    @Override
    public List<DogImage> getImagesBySubBreed(String breed, String subBreed) {
        try{
            String url = API_URL + "/images/breed/" + breed + "/" + subBreed + "/images";
            DogApiListResponse response = restTemplate.getForObject(url, DogApiListResponse.class); 
            if (response == null || response.getMessageList() == null) {
                throw new DogImageNotFoundException("No se encontraron imágenes de perro por subraza");
            }
            return response.getMessageList().stream()
            .map(u -> DogImage.builder()
            .url(u)
            .build())
            .collect(Collectors.toList());
        } catch (RestClientException e) {
            throw new DogApiConnectionException( e.getMessage());
        }
    }
}
