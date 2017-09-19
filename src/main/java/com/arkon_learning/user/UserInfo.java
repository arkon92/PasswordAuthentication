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

    public UserInfo() {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
