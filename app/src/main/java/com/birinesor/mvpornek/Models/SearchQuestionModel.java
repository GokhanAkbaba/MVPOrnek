package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchQuestionModel {
    @SerializedName("kullanici_id")
    @Expose
    private int kullanici_id;
    @SerializedName("ad_soyad")
    @Expose
    private String ad_soyad;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullanici_adi;
    @SerializedName("profil_foto")
    @Expose
    private String profil_foto;
    @SerializedName("soru_id")
    @Expose
    private int soru_id;
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("zaman")
    @Expose
    private String zaman;
    @SerializedName("etiket")
    @Expose
    private String etiket;
    @SerializedName("yorum_sayisi")
    @Expose
    private int yorum_sayisi;

    public String getEtiket() {
        return etiket;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public String getAd_soyad() {
        return ad_soyad;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public String getProfil_foto() {
        return profil_foto;
    }

    public int getSoru_id() {
        return soru_id;
    }

    public String getSoru() {
        return soru;
    }

    public String getZaman() {
        return zaman;
    }

    public int getYorum_sayisi() {
        return yorum_sayisi;
    }
}
