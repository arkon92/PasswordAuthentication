package com.arkon_learning.rest.response;

/**
 * Created by arkon92 on 23/09/2017.
 */
public class AuthenticationResponse {

    private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
