package com.example.mvpornek.Response;

import com.example.mvpornek.Models.KullaniciGetir;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KullaniciGetirResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("users")
    @Expose
    private KullaniciGetir kullaniciGetir;

    public KullaniciGetirResponse(Boolean error, KullaniciGetir kullaniciGetir) {
        this.error = error;
        this.kullaniciGetir = kullaniciGetir;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public KullaniciGetir getKullaniciGetir() {
        return kullaniciGetir;
    }
    public void setKullaniciGetir(KullaniciGetir kullaniciGetir) {
        this.kullaniciGetir = kullaniciGetir;
    }
}
