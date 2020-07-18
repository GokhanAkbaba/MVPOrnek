package com.example.mvpornek.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchListResponse {
    @SerializedName("kullanici_id")
    @Expose
    public int kullaniciId;
    @SerializedName("ad_soyad")
    @Expose
    public String adSoyad;
    @SerializedName("kullanici_adi")
    @Expose
    public String kullanici_adi;

    public int getKullaniciId() {
        return kullaniciId;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }
}
