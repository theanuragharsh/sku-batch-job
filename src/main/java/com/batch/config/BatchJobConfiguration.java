package com.batch.config;

import com.batch.models.CatalogueItems;
import com.batch.repo.CatalogueItemRepo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
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
    private CatalogueItemRepo catalogueItemRepo;

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
                .writer(catalogueItemsRepositoryItemWriter())
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
    public RepositoryItemWriter<CatalogueItems> catalogueItemsRepositoryItemWriter() {
        RepositoryItemWriter<CatalogueItems> catalogueItemsRepositoryItemWriter = new RepositoryItemWriter<>();
        catalogueItemsRepositoryItemWriter.setRepository(catalogueItemRepo);
        catalogueItemsRepositoryItemWriter.setMethodName("save");
        return catalogueItemsRepositoryItemWriter;
    }


/* The below writer is using JdbcBatchItemWriter<>() which is replaced above by RepositoryItemWriter<>()
   RepositoryItemWriter should be used when dealing with JPA Entities.

  @Bean
    public ItemWriter<CatalogueItems> writer() {

        JdbcBatchItemWriter<CatalogueItems> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO SKU_BATCH_JOBS (id, item_name, sku_number, description, category, price, inventory) VALUES (:id, :itemName, :skuNumber, :description, :category, :price, :inventory)");
        writer.setDataSource(targetDataSource);
        return writer;
    }*/
}
