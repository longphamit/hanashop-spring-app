package com.longpc.hanashopspringapp.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {
    private String loginString;
    private String password;

    public String getLoginString() {
        return loginString;
    }

    public void setLoginString(String loginString) {
        this.loginString = loginString;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
