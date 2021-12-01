package com.divided.foodtrack.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class UsersData {
    private int id;
    private int idData;
    private String fullName;
    private Date birthday;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IdData", nullable = false)
    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    @Basic
    @Column(name = "FullName", nullable = false, length = 300)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "Birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersData usersData = (UsersData) o;

        if (id != usersData.id) return false;
        if (idData != usersData.idData) return false;
        if (fullName != null ? !fullName.equals(usersData.fullName) : usersData.fullName != null) return false;
        if (birthday != null ? !birthday.equals(usersData.birthday) : usersData.birthday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idData;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
