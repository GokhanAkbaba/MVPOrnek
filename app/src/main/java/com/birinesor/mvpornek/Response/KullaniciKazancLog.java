package com.birinesor.mvpornek.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KullaniciKazancLog {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;


    public KullaniciKazancLog(Boolean error, int code, String message) {
        this.code = code;
        this.message = message;
        this.error = error;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public Boolean getError() {
        return error;
    }

}
