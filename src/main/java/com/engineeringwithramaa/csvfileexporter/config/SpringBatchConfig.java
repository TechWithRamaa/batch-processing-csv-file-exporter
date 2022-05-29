package com.engineeringwithramaa.csvfileexporter.config;

import com.engineeringwithramaa.csvfileexporter.batch.*;
import com.engineeringwithramaa.csvfileexporter.decider.TransactionsFailedFlowDecider;
import com.engineeringwithramaa.csvfileexporter.entity.ElectronicCardTransactions;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    ElectronicCardTransactionsReader electronicCardTransactionsReader;
    @Autowired
    ElectronicCardTransactionsWriter electronicCardTransactionsWriter;
    @Autowired
    TransactionsFailedFlowDecider transactionsFailedFlowDecider;
    @Autowired
    FailedTransactionsWriter failedTransactionsWriter;


    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("CSV Exporter Step")
                .<ElectronicCardTransactions, ElectronicCardTransactions> chunk(1000)
                .reader(electronicCardTransactionsReader)
                .writer(electronicCardTransactionsWriter)
                .build();
    }
    @Bean
    public Step transactionsfailedStep() {
        return  stepBuilderFactory.get("Failed Transactions step")
                .<ElectronicCardTransactions, ElectronicCardTransactions> chunk(1000)
                .reader(electronicCardTransactionsReader)
                .writer(failedTransactionsWriter)
                .build();

    }

    @Bean
    public Step refundInitiatedStep() {
        return this.stepBuilderFactory.get("Refund Initiated Step")
                .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
                    System.out.println("Refund Initiated step");
                    return RepeatStatus.FINISHED;

                }).build();
    }

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("MyJob")
                .incrementer(new RunIdIncrementer())
                .start(createStep())
                .next(transactionsFailedFlowDecider)
                .from(transactionsFailedFlowDecider)
                .on("TRANSACTIONS_FAILED")
                .to(transactionsfailedStep())
                .end()
                .build();
    }



}


