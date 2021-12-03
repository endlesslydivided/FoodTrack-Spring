package com.divided.foodtrack.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class ProductUsersDietDTO {

    //region Fields
    private Integer idReport;
    private String productName;
    private Date reportDate;
    private String eatPeriod;
    private BigDecimal grams;
    //endregion

    //region Constructors
    public ProductUsersDietDTO() {
    }

    public ProductUsersDietDTO(Integer idReport, String productName, Date reportDate, String eatPeriod, BigDecimal grams) {
        this.idReport = idReport;
        this.productName = productName;
        this.reportDate = reportDate;
        this.eatPeriod = eatPeriod;
        this.grams = grams;
    }
    //endregion

    //region Properties


    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getGrams() {
        return grams;
    }

    public void setGrams(BigDecimal grams) {
        this.grams = grams;
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

    //endregion
}
