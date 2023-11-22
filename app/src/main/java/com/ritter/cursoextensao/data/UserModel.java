package com.ritter.cursoextensao.data;

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

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }


    public static UserModel createInstance(int id, String user, String password, boolean isAdmin) {
        return new UserModel(id, user, password, isAdmin);
    }
}