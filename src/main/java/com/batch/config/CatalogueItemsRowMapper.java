package com.batch.config;

import com.batch.models.CatalogueItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class CatalogueItemsRowMapper implements org.springframework.jdbc.core.RowMapper<com.batch.models.CatalogueItems> {
    @Override
    public CatalogueItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CatalogueItems.builder()
                .id(rs.getLong("id"))
                .itemName(rs.getString("name"))
                .category(rs.getString("category"))
                .description(rs.getString("description"))
                .skuNumber(rs.getString("sku"))
                .inventory(rs.getInt("inventory"))
                .price(rs.getDouble("price"))
                .updatedOn(Instant.now())
                .build();
    }
}
