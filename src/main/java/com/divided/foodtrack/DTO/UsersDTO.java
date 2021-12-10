package com.divided.foodtrack.DTO;

public class UsersDTO {

    //region Fields
    private int idEditor;
    private int id;
    private boolean isAdmin;
    private String userLogin;
    private String userPassword;
    private String eMail;

    //endregion


    //region Constructors


    public UsersDTO(int idEditor, int id, boolean isAdmin, String userLogin, String userPassword, String eMail) {
        this.idEditor = idEditor;
        this.id = id;
        this.isAdmin = isAdmin;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.eMail = eMail;
    }

    public UsersDTO(int idEditor, boolean isAdmin, String userLogin, String userPassword, String eMail) {
        this.idEditor = idEditor;
        this.isAdmin = isAdmin;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.eMail = eMail;
    }

    public UsersDTO() {
    }
    //endregion

    //region Properties


    public int getIdEditor() {
        return idEditor;
    }

    public void setIdEditor(int idEditor) {
        this.idEditor = idEditor;
    }

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
