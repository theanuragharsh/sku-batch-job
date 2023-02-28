package com.batch.repo;

import com.batch.models.CatalogueItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueItemRepo extends JpaRepository<CatalogueItems,Long> {
}
