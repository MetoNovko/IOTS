package com.mps.transferables.DTOs.Security;

public class JwtAuthRequest {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    public JwtAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtAuthRequest() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
