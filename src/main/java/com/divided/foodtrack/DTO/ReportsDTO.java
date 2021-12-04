package com.divided.foodtrack.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class ReportsDTO {

    //region Fields
    private int id;
    private int idReport;
    private String productName;
    private Date reportDate;
    private String eatPeriod;
    private BigDecimal dayGram;
    private BigDecimal dayCalories;
    private BigDecimal dayProteins;
    private BigDecimal dayFats;
    private BigDecimal dayCarbohydrates;
    //endregion

    //region ReportsDTO


    public ReportsDTO(int id, int idReport, String productName, Date reportDate, String eatPeriod, BigDecimal dayGram) {
        this.id = id;
        this.idReport = idReport;
        this.productName = productName;
        this.reportDate = reportDate;
        this.eatPeriod = eatPeriod;
        this.dayGram = dayGram;
    }

    public ReportsDTO() {
    }

    public ReportsDTO(int idReport, String productName, Date reportDate, String eatPeriod, BigDecimal dayGram, BigDecimal dayCalories, BigDecimal dayProteins, BigDecimal dayFats, BigDecimal dayCarbohydrates) {
        this.idReport = idReport;
        this.productName = productName;
        this.reportDate = reportDate;
        this.eatPeriod = eatPeriod;
        this.dayGram = dayGram;
        this.dayCalories = dayCalories;
        this.dayProteins = dayProteins;
        this.dayFats = dayFats;
        this.dayCarbohydrates = dayCarbohydrates;
    }

    public ReportsDTO(int id, int idReport, String productName, Date reportDate, String eatPeriod, BigDecimal dayGram, BigDecimal dayCalories, BigDecimal dayProteins, BigDecimal dayFats, BigDecimal dayCarbohydrates) {
        this.id = id;
        this.idReport = idReport;
        this.productName = productName;
        this.reportDate = reportDate;
        this.eatPeriod = eatPeriod;
        this.dayGram = dayGram;
        this.dayCalories = dayCalories;
        this.dayProteins = dayProteins;
        this.dayFats = dayFats;
        this.dayCarbohydrates = dayCarbohydrates;
    }

    public ReportsDTO(Date reportDate, BigDecimal dayGram) {
        this.reportDate = reportDate;
        this.dayGram = dayGram;
    }

    //endregion

    //region Properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getEatPeriod() {
        return eatPeriod;
    }

    public void setEatPeriod(String eatPeriod) {
        this.eatPeriod = eatPeriod;
    }

    public BigDecimal getDayGram() {
        return dayGram;
    }

    public void setDayGram(BigDecimal dayGram) {
        this.dayGram = dayGram;
    }

    public BigDecimal getDayCalories() {
        return dayCalories;
    }

    public void setDayCalories(BigDecimal dayCalories) {
        this.dayCalories = dayCalories;
    }

    public BigDecimal getDayProteins() {
        return dayProteins;
    }

    public void setDayProteins(BigDecimal dayProteins) {
        this.dayProteins = dayProteins;
    }

    public BigDecimal getDayFats() {
        return dayFats;
    }

    public void setDayFats(BigDecimal dayFats) {
        this.dayFats = dayFats;
    }

    public BigDecimal getDayCarbohydrates() {
        return dayCarbohydrates;
    }

    public void setDayCarbohydrates(BigDecimal dayCarbohydrates) {
        this.dayCarbohydrates = dayCarbohydrates;
    }
    //endregion
}
