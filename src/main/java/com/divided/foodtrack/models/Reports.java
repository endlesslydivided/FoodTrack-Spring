package com.divided.foodtrack.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Reports {
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

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IdReport", nullable = false)
    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    @Basic
    @Column(name = "ProductName", nullable = false, length = 200)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "ReportDate", nullable = false)
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Basic
    @Column(name = "EatPeriod", nullable = false, length = 8)
    public String getEatPeriod() {
        return eatPeriod;
    }

    public void setEatPeriod(String eatPeriod) {
        this.eatPeriod = eatPeriod;
    }

    @Basic
    @Column(name = "DayGram", nullable = false, precision = 2)
    public BigDecimal getDayGram() {
        return dayGram;
    }

    public void setDayGram(BigDecimal dayGram) {
        this.dayGram = dayGram;
    }

    @Basic
    @Column(name = "DayCalories", nullable = false, precision = 2)
    public BigDecimal getDayCalories() {
        return dayCalories;
    }

    public void setDayCalories(BigDecimal dayCalories) {
        this.dayCalories = dayCalories;
    }

    @Basic
    @Column(name = "DayProteins", nullable = false, precision = 2)
    public BigDecimal getDayProteins() {
        return dayProteins;
    }

    public void setDayProteins(BigDecimal dayProteins) {
        this.dayProteins = dayProteins;
    }

    @Basic
    @Column(name = "DayFats", nullable = false, precision = 2)
    public BigDecimal getDayFats() {
        return dayFats;
    }

    public void setDayFats(BigDecimal dayFats) {
        this.dayFats = dayFats;
    }

    @Basic
    @Column(name = "DayCarbohydrates", nullable = false, precision = 2)
    public BigDecimal getDayCarbohydrates() {
        return dayCarbohydrates;
    }

    public void setDayCarbohydrates(BigDecimal dayCarbohydrates) {
        this.dayCarbohydrates = dayCarbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reports reports = (Reports) o;

        if (id != reports.id) return false;
        if (idReport != reports.idReport) return false;
        if (productName != null ? !productName.equals(reports.productName) : reports.productName != null) return false;
        if (reportDate != null ? !reportDate.equals(reports.reportDate) : reports.reportDate != null) return false;
        if (eatPeriod != null ? !eatPeriod.equals(reports.eatPeriod) : reports.eatPeriod != null) return false;
        if (dayGram != null ? !dayGram.equals(reports.dayGram) : reports.dayGram != null) return false;
        if (dayCalories != null ? !dayCalories.equals(reports.dayCalories) : reports.dayCalories != null) return false;
        if (dayProteins != null ? !dayProteins.equals(reports.dayProteins) : reports.dayProteins != null) return false;
        if (dayFats != null ? !dayFats.equals(reports.dayFats) : reports.dayFats != null) return false;
        if (dayCarbohydrates != null ? !dayCarbohydrates.equals(reports.dayCarbohydrates) : reports.dayCarbohydrates != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idReport;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (reportDate != null ? reportDate.hashCode() : 0);
        result = 31 * result + (eatPeriod != null ? eatPeriod.hashCode() : 0);
        result = 31 * result + (dayGram != null ? dayGram.hashCode() : 0);
        result = 31 * result + (dayCalories != null ? dayCalories.hashCode() : 0);
        result = 31 * result + (dayProteins != null ? dayProteins.hashCode() : 0);
        result = 31 * result + (dayFats != null ? dayFats.hashCode() : 0);
        result = 31 * result + (dayCarbohydrates != null ? dayCarbohydrates.hashCode() : 0);
        return result;
    }
}
