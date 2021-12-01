package com.divided.foodtrack.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class UsersParams {
    private int id;
    private int idParams;
    private Date paramsDate;
    private BigDecimal userWeight;
    private int userHeight;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IdParams", nullable = false)
    public int getIdParams() {
        return idParams;
    }

    public void setIdParams(int idParams) {
        this.idParams = idParams;
    }

    @Basic
    @Column(name = "ParamsDate", nullable = false)
    public Date getParamsDate() {
        return paramsDate;
    }

    public void setParamsDate(Date paramsDate) {
        this.paramsDate = paramsDate;
    }

    @Basic
    @Column(name = "UserWeight", nullable = false, precision = 1)
    public BigDecimal getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(BigDecimal userWeight) {
        this.userWeight = userWeight;
    }

    @Basic
    @Column(name = "UserHeight", nullable = false)
    public int getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(int userHeight) {
        this.userHeight = userHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersParams that = (UsersParams) o;

        if (id != that.id) return false;
        if (idParams != that.idParams) return false;
        if (userHeight != that.userHeight) return false;
        if (paramsDate != null ? !paramsDate.equals(that.paramsDate) : that.paramsDate != null) return false;
        if (userWeight != null ? !userWeight.equals(that.userWeight) : that.userWeight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idParams;
        result = 31 * result + (paramsDate != null ? paramsDate.hashCode() : 0);
        result = 31 * result + (userWeight != null ? userWeight.hashCode() : 0);
        result = 31 * result + userHeight;
        return result;
    }
}
