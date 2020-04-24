package com.example.mvpornek.Model.Kullanıcı;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kullanici {
    @Expose
    @SerializedName("ad_soyad") private String adSoyad;
    @Expose
    @SerializedName("kullaniciAdi") private String kullaniciAdi;
    @Expose
    @SerializedName("kullanici_eposta") private String eMail;
    @Expose
    @SerializedName("kullanici_sifre") private String sifre;
    @Expose
    @SerializedName("kullanici_tekrar_sifre") private String sifreTekrar;

    public Kullanici(String adSoyad, String kullaniciAdi, String eMail, String sifre, String sifreTekrar) {
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.eMail = eMail;
        this.sifre = sifre;
        this.sifreTekrar = sifreTekrar;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getSifreTekrar() {
        return sifreTekrar;
    }

    public void setSifreTekrar(String sifreTekrar) {
        this.sifreTekrar = sifreTekrar;
    }




}
