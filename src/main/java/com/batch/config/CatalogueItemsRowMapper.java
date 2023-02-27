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
                .ITEM_NAME(rs.getString("name"))
                .CATEGORY(rs.getString("category"))
                .DESCRIPTION(rs.getString("description"))
                .SKU_NUMBER(rs.getString("sku"))
                .INVENTORY(rs.getInt("inventory"))
                .PRICE(rs.getDouble("price"))
                .build();
    }
}
