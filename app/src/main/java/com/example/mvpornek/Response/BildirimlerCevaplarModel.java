package com.example.mvpornek.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BildirimlerCevaplarModel {
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
    @SerializedName("soru_id")
    @Expose
    private int soruID;

    public BildirimlerCevaplarModel(int kullaniciID, String adSoyad, String kullaniciAdi, String profilFoto, String cevap, int soruID) {
        this.kullaniciID = kullaniciID;
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.profilFoto = profilFoto;
        this.cevap = cevap;
        this.soruID = soruID;
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

    public int getSoruID() {
        return soruID;
    }
}
