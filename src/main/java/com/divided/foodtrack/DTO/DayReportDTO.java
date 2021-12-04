package com.divided.foodtrack.DTO;

import java.math.BigDecimal;

public class DayReportDTO {

    //region Fields
    private BigDecimal calsEaten;
    private BigDecimal calsNeeded;
    private BigDecimal proteinsEaten;
    private BigDecimal proteinsNeeded;
    private BigDecimal fatsEaten;
    private BigDecimal fatsNeeded;
    private BigDecimal carbohydratesEaten;
    private BigDecimal carbohydratesNeeded;
    //endregion

    //region Constructors
    public DayReportDTO() {
    }

    public DayReportDTO(BigDecimal calsEaten, BigDecimal calsNeeded, BigDecimal proteinsEaten, BigDecimal proteinsNeeded, BigDecimal fatsEaten, BigDecimal fatsNeeded, BigDecimal carbohydratesEaten, BigDecimal carbohydratesNeeded) {
        this.calsEaten = calsEaten;
        this.calsNeeded = calsNeeded;
        this.proteinsEaten = proteinsEaten;
        this.proteinsNeeded = proteinsNeeded;
        this.fatsEaten = fatsEaten;
        this.fatsNeeded = fatsNeeded;
        this.carbohydratesEaten = carbohydratesEaten;
        this.carbohydratesNeeded = carbohydratesNeeded;
    }
    //endregion

    //region Properties

    public BigDecimal getCalsEaten() {
        return calsEaten;
    }

    public void setCalsEaten(BigDecimal calsEaten) {
        this.calsEaten = calsEaten;
    }

    public BigDecimal getCalsNeeded() {
        return calsNeeded;
    }

    public void setCalsNeeded(BigDecimal calsNeeded) {
        this.calsNeeded = calsNeeded;
    }

    public BigDecimal getProteinsEaten() {
        return proteinsEaten;
    }

    public void setProteinsEaten(BigDecimal proteinsEaten) {
        this.proteinsEaten = proteinsEaten;
    }

    public BigDecimal getProteinsNeeded() {
        return proteinsNeeded;
    }

    public void setProteinsNeeded(BigDecimal proteinsNeeded) {
        this.proteinsNeeded = proteinsNeeded;
    }

    public BigDecimal getFatsEaten() {
        return fatsEaten;
    }

    public void setFatsEaten(BigDecimal fatsEaten) {
        this.fatsEaten = fatsEaten;
    }

    public BigDecimal getFatsNeeded() {
        return fatsNeeded;
    }

    public void setFatsNeeded(BigDecimal fatsNeeded) {
        this.fatsNeeded = fatsNeeded;
    }

    public BigDecimal getCarbohydratesEaten() {
        return carbohydratesEaten;
    }

    public void setCarbohydratesEaten(BigDecimal carbohydratesEaten) {
        this.carbohydratesEaten = carbohydratesEaten;
    }

    public BigDecimal getCarbohydratesNeeded() {
        return carbohydratesNeeded;
    }

    public void setCarbohydratesNeeded(BigDecimal carbohydratesNeeded) {
        this.carbohydratesNeeded = carbohydratesNeeded;
    }

    //endregion
}
