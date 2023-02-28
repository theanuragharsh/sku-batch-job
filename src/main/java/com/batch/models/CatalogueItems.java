package com.batch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "catalogue_items")
public class CatalogueItems {

    @Id
    private Long id;
    @Column(name = "sku_number")
    private String SKU_NUMBER;
    @Column(name = "item_name")
    private String ITEM_NAME;
    @Column(name = "description")
    private String DESCRIPTION;
    @Column(name = "category")
    private String CATEGORY;
    @Column(name = "price")
    private Double PRICE;
    @Column(name = "inventory")
    private Integer INVENTORY;

}
