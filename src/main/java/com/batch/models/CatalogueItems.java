package com.batch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class CatalogueItems {


    private Long id;
    private String sku;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer inventory;
    private Instant createdOn;
    private Instant updatedOn;

}
