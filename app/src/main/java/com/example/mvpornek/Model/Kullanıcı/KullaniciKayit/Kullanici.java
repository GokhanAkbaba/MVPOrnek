package com.example.mvpornek.Model.Kullanıcı.KullaniciKayit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kullanici {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ad_soyad")
    @Expose
    private String adSoyad;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;

    public Kullanici(int id,String adSoyad, String kullaniciAdi) {
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }


}
