package com.ritter.cursoextensao.data;

public class UserInfo {
    private static String username;
    private static String password;

    private static UserModel userModel;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserInfo.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserInfo.password = password;
    }
    public static UserModel getUserModel() {
        return userModel;
    }

    public static void setUserModel(UserModel userModel) {
        UserInfo.userModel = userModel;
    }

}
