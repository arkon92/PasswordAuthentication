package com.arkon_learning.user;

/**
 * Created by arkon92 on 22/09/2017.
 */
public class UserInfo {


    protected String username;

    protected String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo() {

    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegistrationInfo{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
