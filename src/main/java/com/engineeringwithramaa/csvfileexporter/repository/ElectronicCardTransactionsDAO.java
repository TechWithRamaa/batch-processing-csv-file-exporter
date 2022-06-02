package com.engineeringwithramaa.csvfileexporter.repository;

import com.engineeringwithramaa.csvfileexporter.entity.ElectronicCardTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElectronicCardTransactionsDAO extends JpaRepository<ElectronicCardTransactions, Integer> {
    @Query(value = "SELECT * FROM `card_transactions` WHERE transaction_status = \"TRANSACTION FAILED\";", nativeQuery = true)
     List<ElectronicCardTransactions> findbyTransactionStatus();
}
