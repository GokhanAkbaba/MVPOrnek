package com.example.mvpornek.Response;

import com.example.mvpornek.Models.Kullanici;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KullaniciResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("users")
    @Expose
    private Kullanici kullanici;

    public KullaniciResponse(Boolean error, int code, String message, Kullanici kullanici) {
        this.code = code;
        this.message = message;
        this.error = error;
        this.kullanici = kullanici;
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

    public Kullanici getKullanici() {
        return kullanici;
    }

}
