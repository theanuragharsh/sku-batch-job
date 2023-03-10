package com.batch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "catalogue_items")
@Table(name = "sku_batch_jobs")
public class CatalogueItems {

    @Id
    private Long id;
    @Column(name = "sku_number")
    private String skuNumber;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private Double price;
    @Column(name = "inventory")
    private Integer inventory;
    @Column(name = "updated_on")
    private Instant updatedOn;

}
