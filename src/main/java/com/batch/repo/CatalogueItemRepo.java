package com.batch.repo;

import com.batch.models.CatalogueItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueItemRepo extends JpaRepository<CatalogueItems,Long> {
}
