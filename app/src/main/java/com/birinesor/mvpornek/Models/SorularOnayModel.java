package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SorularOnayModel {

    @SerializedName("kullaniciId")
    @Expose
    private int kullaniciId;
    @SerializedName("adSoyad")
    @Expose
    private String adSoyad;
    @SerializedName("profilFoto")
    @Expose
    private String profilFoto;
    @SerializedName("kullaniciAdi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("zaman")
    @Expose
    private String zaman;
    @SerializedName("soruId")
    @Expose
    private String soruId;

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getProfilFoto() {
        return profilFoto;
    }

    public void setProfilFoto(String profilFoto) {
        this.profilFoto = profilFoto;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }

    public String getSoruId() {
        return soruId;
    }

    public void setSoruId(String soruId) {
        this.soruId = soruId;
    }
}

