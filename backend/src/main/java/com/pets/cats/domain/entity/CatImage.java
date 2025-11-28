package com.pets.cats.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatImage {
    
    private String id;
    private String url;
    private Integer width;
    private Integer height;
    
    @JsonProperty("original_filename")
    private String originalFilename;
    
    private Integer pending;
    private Integer approved;
    
    private List<CatBreed> breeds;
    
    public boolean hasBreeds() {
        return breeds != null && !breeds.isEmpty();
    }
    
    public CatBreed getFirstBreed() {
        return hasBreeds() ? breeds.get(0) : null;
    }
}