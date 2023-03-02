package com.batch.config;

import com.batch.models.CatalogueItems;
import org.springframework.batch.item.ItemProcessor;

import java.time.Instant;


public class JobProcessor implements ItemProcessor<CatalogueItems, CatalogueItems> {

    @Override
    public CatalogueItems process(CatalogueItems catalogueItems) throws Exception {
        catalogueItems.setPrice(catalogueItems.getPrice() * 1000);
        catalogueItems.setUpdatedOn(Instant.now());
        return catalogueItems;
    }
}
