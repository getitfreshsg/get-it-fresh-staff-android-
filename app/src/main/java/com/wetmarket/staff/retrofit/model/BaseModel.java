package com.wetmarket.staff.retrofit.model;

import java.io.Serializable;


public class BaseModel implements Serializable {
    private String success;
    private String message;
    private  boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
