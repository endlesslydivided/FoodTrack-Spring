package com.divided.foodtrack.DTO;

public class UsersDTO {

    //region Fields
    private int id;
    private boolean isAdmin;
    private String userLogin;
    private String userPassword;
    //endregion

    //region Constructors
    public UsersDTO(int id, boolean isAdmin, String userLogin, String userPassword) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public UsersDTO(boolean isAdmin, String userLogin, String userPassword) {
        this.isAdmin = isAdmin;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public UsersDTO() {
    }
    //endregion

    //region Properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    //endregion
}
