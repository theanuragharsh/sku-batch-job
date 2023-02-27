package com.batch.config;

import com.batch.models.CatalogueItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyEntityRowMapper implements RowMapper<CatalogueItems> {

    @Override
    public CatalogueItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        CatalogueItems catalogueItems = new CatalogueItems();
        catalogueItems.setId(rs.getLong("id"));
        catalogueItems.setSKU_NUMBER(rs.getString("sku_number"));
        catalogueItems.setITEM_NAME(rs.getString("item_name"));
        catalogueItems.setDESCRIPTION(rs.getString("description"));
        catalogueItems.setCATEGORY(rs.getString("category"));
        catalogueItems.setINVENTORY(rs.getInt("inventory"));
        catalogueItems.setPRICE(rs.getDouble("price"));
        return catalogueItems;
    }

}