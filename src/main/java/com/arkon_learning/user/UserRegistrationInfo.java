package com.arkon_learning.user;

/**
 * Created by arkon92 on 10/09/2017.
 */
public class UserRegistrationInfo extends UserInfo{

    private String email;

    public UserRegistrationInfo(String email, String username, String password) {
        super(username,password);
        this.email = email;
    }

    public UserRegistrationInfo() {
        super();
    }


    @Override
    public String toString() {
        return "UserRegistrationInfo{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
