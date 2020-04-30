package com.example.mvpornek.Model.KullaniciGiris;


import com.example.mvpornek.Model.KullaniciGiris.KullaniciGirisModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KullaniciGirisResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("users")
    @Expose
    private KullaniciGirisModel kullaniciGirisModel;

    public KullaniciGirisResponse(Boolean error, String message, KullaniciGirisModel kullaniciGirisModel) {
        this.error = error;
        this.message = message;
        this.kullaniciGirisModel = kullaniciGirisModel;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public KullaniciGirisModel getKullaniciGirisModel() {
        return kullaniciGirisModel;
    }

    public void setKullaniciGirisModel(KullaniciGirisModel kullaniciGirisModel) {
        this.kullaniciGirisModel = kullaniciGirisModel;
    }
}

