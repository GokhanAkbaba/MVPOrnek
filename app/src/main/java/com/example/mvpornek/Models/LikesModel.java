package com.example.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikesModel {

    @SerializedName("kullanici_id")
    @Expose
    private int kullaniciId;
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
    @SerializedName("zaman")
    @Expose
    private String zaman;
    @SerializedName("begeni_sayisi")
    @Expose
    private int begeni_sayisi;
    @SerializedName("kim_begendi")
    @Expose
    private int kim_begendi;
    @SerializedName("cevap_id")
    @Expose
    private int cevap_id;

    public int getCevap_id() {
        return cevap_id;
    }

    public int getKim_begendi() {
        return kim_begendi;
    }

    public int getKullaniciId() {
        return kullaniciId;
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

    public String getZaman() {
        return zaman;
    }
    public int getBegeni_sayisi() {
        return begeni_sayisi;
    }
}
