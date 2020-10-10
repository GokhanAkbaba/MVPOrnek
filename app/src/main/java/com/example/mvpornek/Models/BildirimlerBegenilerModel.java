package com.example.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class BildirimlerBegenilerModel {
    @SerializedName("kullanici_id")
    @Expose
    private int kullaniciID;
    @SerializedName("ad_soyad")
    @Expose
    private String adSoyad;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("profil_foto")
    @Expose
    private String profilFoto;
    @SerializedName("cevap")
    @Expose
    private String cevap;
    @SerializedName("cevap_id")
    @Expose
    private int cevapID;
    @SerializedName("soru_id")
    @Expose
    private int soruID;

    public BildirimlerBegenilerModel(int kullaniciID, String adSoyad, String kullaniciAdi, String profilFoto, String cevap, int cevapID,int soruID) {
        this.kullaniciID = kullaniciID;
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.profilFoto = profilFoto;
        this.cevap = cevap;
        this.cevapID = cevapID;
        this.soruID=soruID;
    }

    public int getKullaniciID() {
        return kullaniciID;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getProfilFoto() {
        return profilFoto;
    }

    public String getCevap() {
        return cevap;
    }

    public int getCevapID() {
        return cevapID;
    }

    public int getSoruID() {
        return soruID;
    }
}
