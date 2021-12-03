package com.divided.foodtrack.DTO;

import java.util.List;

public class UserToCheckDTO {
    private List<String> roles;
    private int id;
    private String token;
    private String userName;

    public UserToCheckDTO(List<String> roles, int id, String token, String userName) {
        this.roles = roles;
        this.id = id;
        this.token = token;
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
