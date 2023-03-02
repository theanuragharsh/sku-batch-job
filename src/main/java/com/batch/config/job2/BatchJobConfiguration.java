package com.batch.config.job2;

import com.batch.config.CatalogueItemProcessor;
import com.batch.config.JobProcessor;
import com.batch.config.MyEntityRowMapper;
import com.batch.config.MyEntityRowMapper2;
import com.batch.models.CatalogueItemJob;
import com.batch.models.CatalogueItems;
import com.batch.repo.CatalogueItemJobRepo;
import com.batch.repo.CatalogueItemRepo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import javax.sql.DataSource;
import java.util.HashMap;

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
    private CatalogueItemJobRepo catalogueItemJobRepo;

    @Autowired
    private CatalogueItemProcessor catalogueItemProcessor;
//    @Autowired
//    private JobProcessor jobProcessor;

    @Bean
    public Job updatePriceByTenPercentJob() {
        return jobBuilderFactory.get("updatePriceByTenPercentJob").incrementer(new RunIdIncrementer()).start(step1()).next(step2()).build();
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


//    @Bean
//    public RepositoryItemReader<CatalogueItems> reader2() {
//        HashMap<String, Sort.Direction> stringSortHashMap = new HashMap<>();
//        stringSortHashMap.put("id", Sort.Direction.ASC);
//        RepositoryItemReader<CatalogueItems> repositoryItemReader = new RepositoryItemReader<>();
//        repositoryItemReader.setRepository(catalogueItemRepo);
//        repositoryItemReader.setMethodName("findAll");
//        repositoryItemReader.setPageSize(10);
//        repositoryItemReader.setSort(stringSortHashMap);
//        return repositoryItemReader;
//    }
    @Bean
    public JdbcCursorItemReader<CatalogueItemJob> reader2() {
        JdbcCursorItemReader<CatalogueItemJob> reader2 = new JdbcCursorItemReader<>();
        reader2.setDataSource(dataSource);
        reader2.setSql("SELECT * FROM SKU_BATCH_JOBS");
        reader2.setRowMapper(new MyEntityRowMapper2());
        return reader2;
    }


    @Bean
    public JobProcessor jobProcessor() {
        return new JobProcessor();
    }

    @Bean
    public RepositoryItemWriter<CatalogueItemJob> writer2() {
        RepositoryItemWriter<CatalogueItemJob> catalogueItemJobRepositoryItemWriter = new RepositoryItemWriter<>();
        catalogueItemJobRepositoryItemWriter.setRepository(catalogueItemJobRepo);
        catalogueItemJobRepositoryItemWriter.setMethodName("save");
        return catalogueItemJobRepositoryItemWriter;
    }

//    @Bean
//    public Job job2() {
//        return jobBuilderFactory.get("job2").incrementer(new RunIdIncrementer()).start(step2()).build();
//    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<CatalogueItemJob, CatalogueItemJob>chunk(10)
                .reader(reader2())
                .writer(writer2()).build();
    }


}
