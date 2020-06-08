package com.example.mvpornek.Model.Kullanıcı;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ad_soyad")
    @Expose
    private String adSoyad;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("etiket_adi")
    @Expose
    private String etiketAdi;
    @SerializedName("zaman")
    @Expose
    private String zaman;
    @SerializedName("profil_foto")
    @Expose
    private String profilFoto;

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

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getEtiketAdi() {
        return etiketAdi;
    }

    public void setEtiketAdi(String etiketAdi) {
        this.etiketAdi = etiketAdi;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }

    public String getProfilFoto() {
        return profilFoto;
    }

    public void setProfilFoto(String profilFoto) {
        this.profilFoto = profilFoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}