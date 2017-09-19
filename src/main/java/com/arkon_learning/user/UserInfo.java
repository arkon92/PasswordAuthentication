package com.arkon_learning.user;

/**
 * Created by arkon92 on 10/09/2017.
 */
public class UserInfo {

    private String email;

    private String username;

    private String password;

    public UserInfo(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
