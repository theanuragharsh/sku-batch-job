package com.batch.config;

import com.batch.models.CatalogueItems;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MyEntityRowMapper implements RowMapper<CatalogueItems> {

    @Override
    public CatalogueItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        CatalogueItems catalogueItems = new CatalogueItems();
        catalogueItems.setId(rs.getLong("id"));
        catalogueItems.setSkuNumber(rs.getString("sku_number"));
        catalogueItems.setItemName(rs.getString("item_name"));
        catalogueItems.setDescription(rs.getString("description"));
        catalogueItems.setCategory(rs.getString("category"));
        catalogueItems.setInventory(rs.getInt("inventory"));
        catalogueItems.setPrice(rs.getDouble("price"));
        return catalogueItems;
    }

}