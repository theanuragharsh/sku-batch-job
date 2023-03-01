package com.batch.config;

import com.batch.models.CatalogueItems;
import org.springframework.batch.item.ItemProcessor;

import java.time.Instant;


public class JobProcessor implements ItemProcessor<CatalogueItems, CatalogueItems> {

    @Override
    public CatalogueItems process(CatalogueItems catalogueItems) throws Exception {
        catalogueItems.setPrice(catalogueItems.getPrice()*1000);
        catalogueItems.setUpdatedOn(Instant.now());
        return catalogueItems;
        /*CatalogueItemJob catalogueItemJob = new CatalogueItemJob();
        catalogueItemJob.setId(catalogueItems.getId());
        catalogueItemJob.setItemName(catalogueItems.getItemName());
        catalogueItemJob.setSkuNumber(catalogueItems.getSkuNumber());
        catalogueItemJob.setDescription(catalogueItems.getDescription());
        catalogueItemJob.setCategory(catalogueItems.getCategory());
        catalogueItemJob.setPrice(catalogueItems.getPrice() * 1000);
        catalogueItemJob.setInventory(catalogueItems.getInventory());
        catalogueItemJob.setUpdatedOn(Instant.now());
        return catalogueItemJob;*/
/*        return CatalogueItemJob.builder()
                .id(catalogueItems.getId())
                .skuNumber(catalogueItems.getSkuNumber())
                .itemName(catalogueItems.getItemName())
                .description(catalogueItems.getDescription())
                .category(catalogueItems.getCategory())
                .price(catalogueItems.getPrice() * 1000)
                .inventory(catalogueItems.getInventory())
                .updatedOn(Instant.now()).build();*/
    }
}
