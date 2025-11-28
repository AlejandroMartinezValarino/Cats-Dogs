package com.pets.cats.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatBreed {
    
    private String id;
    private String name;
    private String temperament;
    
    @JsonProperty("life_span")
    private String lifeSpan;
    
    @JsonProperty("alt_names")
    private String altNames;
    
    @JsonProperty("wikipedia_url")
    private String wikipediaUrl;
    
    private String origin;
    
    private Weight weight;
    
    @JsonProperty("cfa_url")
    private String cfaUrl;
    
    @JsonProperty("vetstreet_url")
    private String vetstreetUrl;
    
    @JsonProperty("vcahospitals_url")
    private String vcahospitalsUrl;
    
    @JsonProperty("country_codes")
    private String countryCodes;
    
    private String description;
    
    private Integer indoor;
    private Integer lap;
    private Integer adaptability;
    
    @JsonProperty("affection_level")
    private Integer affectionLevel;
    
    @JsonProperty("child_friendly")
    private Integer childFriendly;
    
    @JsonProperty("dog_friendly")
    private Integer dogFriendly;
    
    @JsonProperty("energy_level")
    private Integer energyLevel;
    
    private Integer grooming;
    
    @JsonProperty("health_issues")
    private Integer healthIssues;
    
    private Integer intelligence;
    
    @JsonProperty("shedding_level")
    private Integer sheddingLevel;
    
    @JsonProperty("social_needs")
    private Integer socialNeeds;
    
    @JsonProperty("stranger_friendly")
    private Integer strangerFriendly;
    
    private Integer vocalisation;
    private Integer experimental;
    private Integer hairless;
    private Integer natural;
    private Integer rare;
    private Integer rex;
    
    @JsonProperty("suppressed_tail")
    private Integer suppressedTail;
    
    @JsonProperty("short_legs")
    private Integer shortLegs;
    
    private Integer hypoallergenic;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weight {
        private String imperial;
        private String metric;
    }
}