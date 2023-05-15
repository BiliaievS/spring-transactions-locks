package com.example.transactionslocks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Votes(String code, String technology, @JsonProperty("votes") int votes) {
}
