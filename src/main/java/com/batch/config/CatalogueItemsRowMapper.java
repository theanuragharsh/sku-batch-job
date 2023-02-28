package com.batch.config;

import com.batch.models.CatalogueItems;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogueItemsRowMapper implements org.springframework.jdbc.core.RowMapper<com.batch.models.CatalogueItems> {
    @Override
    public CatalogueItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        CatalogueItems catalogueItems = new CatalogueItems();
        return CatalogueItems.builder()
                .id(rs.getLong("id"))
                .itemName(rs.getString("name"))
                .category(rs.getString("category"))
                .description(rs.getString("description"))
                .skuNumber(rs.getString("sku"))
                .inventory(rs.getInt("inventory"))
                .price(rs.getDouble("price"))
                .build();
    }
}
