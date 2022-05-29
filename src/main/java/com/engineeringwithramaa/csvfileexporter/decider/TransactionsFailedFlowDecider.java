package com.engineeringwithramaa.csvfileexporter.decider;

import com.engineeringwithramaa.csvfileexporter.entity.ElectronicCardTransactions;
import com.engineeringwithramaa.csvfileexporter.repository.ElectronicCardTransactionsDAO;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionsFailedFlowDecider implements JobExecutionDecider {
    @Autowired
    private ElectronicCardTransactionsDAO dao;
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        List<ElectronicCardTransactions> failedTransactions = dao.findbyTransactionStatus();
        if(!failedTransactions.isEmpty()) {
            return new FlowExecutionStatus("TRANSACTIONS_FAILED");
        }
        return new FlowExecutionStatus("COMPLETED");
    }
}
