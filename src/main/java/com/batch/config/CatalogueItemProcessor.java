package com.batch.config;

import com.batch.models.CatalogueItems;
import org.springframework.batch.item.ItemProcessor;

public class CatalogueItemProcessor implements ItemProcessor<CatalogueItems, CatalogueItems> {

    @Override
    public CatalogueItems process(CatalogueItems catalogueItems) throws Exception {
        return catalogueItems;
    }
}
