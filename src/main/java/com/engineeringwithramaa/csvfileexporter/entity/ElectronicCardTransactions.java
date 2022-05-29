package com.engineeringwithramaa.csvfileexporter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="card_transactions")
public class ElectronicCardTransactions {
    @Id
    private int transactionId;
    private String reference;
    private String period;
    private String transactionStatus;
    private String units;
    private int magnitude;
    private String subject;
    private String transactionGroup;
    private String seriesTitle1;
    private String seriesTitle2;

    public ElectronicCardTransactions() {
    }

    public ElectronicCardTransactions(int transactionId, String reference, String period,
                                      String transactionStatus, String units, int magnitude,
                                      String subject, String transactionGroup, String seriesTitle1, String seriesTitle2) {
        this.transactionId = transactionId;
        this.reference = reference;
        this.period = period;
        this.transactionStatus = transactionStatus;
        this.units = units;
        this.magnitude = magnitude;
        this.subject = subject;
        this.transactionGroup = transactionGroup;
        this.seriesTitle1 = seriesTitle1;
        this.seriesTitle2 = seriesTitle2;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTransactionGroup() {
        return transactionGroup;
    }

    public void setTransactionGroup(String transactionGroup) {
        this.transactionGroup = transactionGroup;
    }

    public String getSeriesTitle1() {
        return seriesTitle1;
    }

    public void setSeriesTitle1(String seriesTitle1) {
        this.seriesTitle1 = seriesTitle1;
    }

    public String getSeriesTitle2() {
        return seriesTitle2;
    }

    public void setSeriesTitle2(String seriesTitle2) {
        this.seriesTitle2 = seriesTitle2;
    }

    @Override
    public String toString() {
        return "com.engineeringwithramaa.csvfileexporter.entity.ElectronicCardTransactions{" +
                "transactionId=" + transactionId +
                ", reference='" + reference + '\'' +
                ", period='" + period + '\'' +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", units='" + units + '\'' +
                ", magnitude=" + magnitude +
                ", subject='" + subject + '\'' +
                ", transactionGroup='" + transactionGroup + '\'' +
                ", seriesTitle1='" + seriesTitle1 + '\'' +
                ", seriesTitle2='" + seriesTitle2 + '\'' +
                '}';
    }
}
