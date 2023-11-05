package com.ritter.cursoextensao;

public class UserModel {
    private int id;
    private String user;
    private String password;
    private boolean isAdmin;

    public UserModel(int id, String user, String password, boolean isAdmin) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
