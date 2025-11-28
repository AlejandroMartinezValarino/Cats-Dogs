package com.pets.dogs.domain.entity;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DogBreed {
    
    private String breed;
    private String[] subBreed;
}
