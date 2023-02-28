package com.batch.config;

import com.batch.models.CatalogueItems;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class BatchJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DataSource targetDataSource;

    @Autowired
    private CatalogueItemProcessor catalogueItemProcessor;

    @Bean
    public Job updatePriceByTenPercentJob() {
        return jobBuilderFactory.get("updatePriceByTenPercentJob").incrementer(new RunIdIncrementer()).start(step1()).build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get("step1").<CatalogueItems, CatalogueItems>chunk(10)
                .reader(reader())
                .processor(catalogueItemProcessor)
                .writer(writer())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<CatalogueItems> reader() {
        JdbcCursorItemReader<CatalogueItems> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT * FROM CATALOGUE_ITEMS");
        reader.setRowMapper(new MyEntityRowMapper());
        return reader;
    }

    @Bean
    public CatalogueItemProcessor processor() {
        return new CatalogueItemProcessor();
    }

    @Bean
    public ItemWriter<CatalogueItems> writer() {

        JdbcBatchItemWriter<CatalogueItems> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO SKU_BATCH_JOBS (id, item_name, sku_number, description, category, price, inventory) VALUES (:id, :itemName, :skuNumber, :description, :category, :price, :inventory)");
        writer.setDataSource(targetDataSource);
        return writer;
    }


}
