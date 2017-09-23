package com.arkon_learning.rest.response;

/**
 * Created by arkon92 on 23/09/2017.
 */
public class RegistrationResponse {

    private final String token;

    public RegistrationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
