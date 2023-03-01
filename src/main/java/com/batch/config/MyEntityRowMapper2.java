package com.batch.config;

import com.batch.models.CatalogueItemJob;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyEntityRowMapper2 implements RowMapper<CatalogueItemJob> {
    @Override
    public CatalogueItemJob mapRow(ResultSet rs, int rowNum) throws SQLException {
         CatalogueItemJob catalogueItemsItemJob = new CatalogueItemJob();
        catalogueItemsItemJob.setId(rs.getLong("id"));
        catalogueItemsItemJob.setSkuNumber(rs.getString("sku_number"));
        catalogueItemsItemJob.setItemName(rs.getString("item_name"));
        catalogueItemsItemJob.setDescription(rs.getString("description"));
        catalogueItemsItemJob.setCategory(rs.getString("category"));
        catalogueItemsItemJob.setInventory(rs.getInt("inventory"));
        catalogueItemsItemJob.setPrice(rs.getDouble("price"));
        return catalogueItemsItemJob;
    }
}
