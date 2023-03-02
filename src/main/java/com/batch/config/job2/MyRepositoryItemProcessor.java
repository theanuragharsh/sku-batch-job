package com.batch.config.job2;

import com.batch.models.CatalogueItemJob;
import com.batch.models.CatalogueItems;
import org.springframework.batch.item.ItemProcessor;

import java.time.Instant;

public class MyRepositoryItemProcessor implements ItemProcessor<CatalogueItems, CatalogueItemJob> {
    @Override
    public CatalogueItemJob process(CatalogueItems catalogueItems) throws Exception {
        CatalogueItemJob catalogueItemJob = new CatalogueItemJob();
        // copy any other fields that need to be mapped from InputEntity to OutputEntity

        catalogueItemJob.setId(catalogueItems.getId());
        catalogueItemJob.setSkuNumber(catalogueItems.getSkuNumber());
        catalogueItemJob.setItemName(catalogueItems.getItemName().toUpperCase());
        catalogueItemJob.setDescription(catalogueItems.getDescription());
        catalogueItemJob.setCategory(catalogueItems.getCategory());
        catalogueItemJob.setInventory(catalogueItems.getInventory());
        catalogueItemJob.setPrice(catalogueItems.getPrice());
        catalogueItemJob.setUpdatedOn(Instant.now());
        return catalogueItemJob;
    }
}
