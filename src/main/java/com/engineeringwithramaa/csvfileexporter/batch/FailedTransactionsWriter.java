package com.engineeringwithramaa.csvfileexporter.batch;

import com.engineeringwithramaa.csvfileexporter.entity.ElectronicCardTransactions;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FailedTransactionsWriter extends FlatFileItemWriter<ElectronicCardTransactions> {
    public FailedTransactionsWriter(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = sdf.format(new Date());

        setResource(new FileSystemResource("fail/"+stringDate+".csv"));
        setLineAggregator(getDelimitedLineAggregator());
        setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("transactionId, period, transactionStatus," +
                        " units, magnitude, subject");
            }
        });
        setFooterCallback(new FlatFileFooterCallback() {
            @Override
            public void writeFooter(Writer writer) throws IOException {
                writer.write("EOF - File Auto-generated on " + stringDate );
            }
        });
    }
    public DelimitedLineAggregator<ElectronicCardTransactions> getDelimitedLineAggregator() {
        BeanWrapperFieldExtractor<ElectronicCardTransactions> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<ElectronicCardTransactions>();
        beanWrapperFieldExtractor.setNames(new String[] {"transactionId","period","transactionStatus",
                "units","magnitude","subject"});

        DelimitedLineAggregator<ElectronicCardTransactions> delimitedLineAggregator = new DelimitedLineAggregator<ElectronicCardTransactions>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return delimitedLineAggregator;

    }

}
