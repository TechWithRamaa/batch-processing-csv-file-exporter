package com.engineeringwithramaa.csvfileexporter.batch;

import com.engineeringwithramaa.csvfileexporter.entity.ElectronicCardTransactions;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class ElectronicCardTransactionsWriter extends FlatFileItemWriter<ElectronicCardTransactions> {
    public ElectronicCardTransactionsWriter(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = sdf.format(new Date());

        setResource(new FileSystemResource("data/csv-export-"+stringDate+".csv"));
        setLineAggregator(getDelimitedLineAggregator());
        setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("transactionId, reference, period, transactionStatus, units, magnitude, subject, transactionGroup, seriesTitle1, seriesTitle2");
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
        beanWrapperFieldExtractor.setNames(new String[] {"transactionId","reference","period","transactionStatus",
                "units","magnitude","subject","transactionGroup","seriesTitle1","seriesTitle2"});

        DelimitedLineAggregator<ElectronicCardTransactions> delimitedLineAggregator = new DelimitedLineAggregator<ElectronicCardTransactions>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return delimitedLineAggregator;

    }
}
