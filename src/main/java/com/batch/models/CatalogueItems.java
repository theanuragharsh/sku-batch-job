package com.batch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogueItems {


    private Long id;
    private String SKU_NUMBER;
    private String ITEM_NAME;
    private String DESCRIPTION;
    private String CATEGORY;
    private Double PRICE;
    private Integer INVENTORY;

}
