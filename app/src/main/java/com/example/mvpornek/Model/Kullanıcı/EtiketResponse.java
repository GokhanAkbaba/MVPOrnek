package com.example.mvpornek.Model.Kullanıcı;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EtiketResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public EtiketResponse(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
