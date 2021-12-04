package com.divided.foodtrack.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class UsersParamsDTO {

    //region Fields
    private int id;
    private int idParams;
    private Date paramsDate;
    private BigDecimal userWeight;
    private int userHeight;
    //endregion

    //region Constructors
    public UsersParamsDTO(int id, int idParams, Date paramsDate, BigDecimal userWeight, int userHeight) {
        this.id = id;
        this.idParams = idParams;
        this.paramsDate = paramsDate;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
    }


    public UsersParamsDTO(int idParams, Date paramsDate, BigDecimal userWeight, int userHeight) {
        this.idParams = idParams;
        this.paramsDate = paramsDate;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
    }

    public UsersParamsDTO(Date paramsDate, BigDecimal userWeight, int userHeight) {
        this.paramsDate = paramsDate;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
    }

    public UsersParamsDTO() {
    }
    //endregion

    //region Properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParams() {
        return idParams;
    }

    public void setIdParams(int idParams) {
        this.idParams = idParams;
    }

    public Date getParamsDate() {
        return paramsDate;
    }

    public void setParamsDate(Date paramsDate) {
        this.paramsDate = paramsDate;
    }

    public BigDecimal getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(BigDecimal userWeight) {
        this.userWeight = userWeight;
    }

    public int getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(int userHeight) {
        this.userHeight = userHeight;
    }
    //endregion
}
