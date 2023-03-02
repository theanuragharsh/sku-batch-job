package com.batch.config.job2;

import com.batch.models.CatalogueItemJob;
import com.batch.models.CatalogueItems;
import com.batch.repo.CatalogueItemJobRepo;
import com.batch.repo.CatalogueItemRepo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;

//TODO: This job is used to convert itemNames into UPPERCASE  using RepositoryItemReader, RepositoryItemProcessor, RepositoryItemWriter

@Configuration
@EnableBatchProcessing
public class SecondBatchJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CatalogueItemRepo inputRepository;

    @Autowired
    private CatalogueItemJobRepo outputRepository;

    @Bean
    public RepositoryItemReader<CatalogueItems> reader3() {
        RepositoryItemReader<CatalogueItems> reader = new RepositoryItemReader<>();
        reader.setRepository(inputRepository);
        reader.setMethodName("findAll");
        reader.setSort(Collections.singletonMap("id", Sort.Direction.ASC));
        return reader;
    }

    @Bean
    public ItemProcessor<CatalogueItems, CatalogueItemJob> processor3() {
        return new MyRepositoryItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<CatalogueItemJob> writer3() {
        RepositoryItemWriter<CatalogueItemJob> writer = new RepositoryItemWriter<>();
        writer.setRepository(outputRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step3() {
        return this.stepBuilderFactory.get("step3")
                .<CatalogueItems, CatalogueItemJob>chunk(10)
                .reader(reader3())
                .processor(processor3())
                .writer(writer3())
                .build();
    }

    @Bean
    public Job myJob() {
        return this.jobBuilderFactory.get("myJob")
                .start(step3())
                .build();
    }
}
