package com.engineeringwithramaa.csvfileexporter.batch;

import com.engineeringwithramaa.csvfileexporter.entity.ElectronicCardTransactions;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ElectronicCardTransactionsReader
       extends JdbcCursorItemReader<ElectronicCardTransactions>
       implements ItemReader<ElectronicCardTransactions> {

    public ElectronicCardTransactionsReader(@Autowired DataSource dataSource) {
        setDataSource(dataSource);
        setSql("SELECT * FROM card_transactions where transaction_status = \"TRANSACTION FAILED\"");
        setFetchSize(100);
        setRowMapper(new ElectronicCardTransactionsRowMapper());
    }

    public class ElectronicCardTransactionsRowMapper implements RowMapper<ElectronicCardTransactions> {
        @Override
        public ElectronicCardTransactions mapRow(ResultSet rs, int rowNum) throws SQLException {
            ElectronicCardTransactions electronicCardTransaction  = new ElectronicCardTransactions();
            electronicCardTransaction.setTransactionId(rs.getInt("transaction_id"));
            electronicCardTransaction.setPeriod(rs.getString("period"));
            electronicCardTransaction.setReference(rs.getString("reference"));
            electronicCardTransaction.setTransactionStatus(rs.getString("transaction_status"));
            electronicCardTransaction.setUnits(rs.getString("units"));
            electronicCardTransaction.setMagnitude(rs.getInt("magnitude"));
            electronicCardTransaction.setSubject(rs.getString("subject"));
            electronicCardTransaction.setTransactionGroup(rs.getString("transaction_group"));
            electronicCardTransaction.setSeriesTitle1(rs.getString("series_title1"));
            electronicCardTransaction.setSeriesTitle2(rs.getString("series_title2"));
            return electronicCardTransaction;
        }

    }

}
