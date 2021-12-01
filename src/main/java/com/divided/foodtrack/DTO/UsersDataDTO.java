package com.divided.foodtrack.DTO;

import java.sql.Date;

public class UsersDataDTO {

    //region Fields
    private int id;
    private int idData;
    private String fullName;
    private Date birthday;
    //endregion

    //region Constructors
    public UsersDataDTO(int id, int idData, String fullName, Date birthday) {
        this.id = id;
        this.idData = idData;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public UsersDataDTO(int idData, String fullName, Date birthday) {
        this.idData = idData;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public UsersDataDTO() {
    }
    //endregion

    //region Properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    //endregion
}
