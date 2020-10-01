package com.example.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationCommetAndQuestionModel {
    @SerializedName("soru_id")
    @Expose
    private int soruID;
    @SerializedName("cevap_id")
    @Expose
    private int cevapID;
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
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("soru_zaman")
    @Expose
    private String soruZaman;
    @SerializedName("cevap")
    @Expose
    private String cevap;
    @SerializedName("cevap_zaman")
    @Expose
    private String cevapZaman;
    @SerializedName("begeniSayisi")
    @Expose
    private int begeniSayisi;
    @SerializedName("kim_begendi")
    @Expose
    private int kimBegendi;

    public NotificationCommetAndQuestionModel(int soruID, int cevapID, int kullaniciID, String adSoyad, String kullaniciAdi, String profilFoto, String soru, String soruZaman, String cevap, String cevapZaman, int begeniSayisi, int kimBegendi) {
        this.soruID = soruID;
        this.cevapID = cevapID;
        this.kullaniciID = kullaniciID;
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.profilFoto = profilFoto;
        this.soru = soru;
        this.soruZaman = soruZaman;
        this.cevap = cevap;
        this.cevapZaman = cevapZaman;
        this.begeniSayisi = begeniSayisi;
        this.kimBegendi = kimBegendi;
    }

    public int getSoruID() {
        return soruID;
    }

    public void setSoruID(int soruID) {
        this.soruID = soruID;
    }

    public int getCevapID() {
        return cevapID;
    }

    public void setCevapID(int cevapID) {
        this.cevapID = cevapID;
    }

    public int getKullaniciID() {
        return kullaniciID;
    }

    public void setKullaniciID(int kullaniciID) {
        this.kullaniciID = kullaniciID;
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

    public String getProfilFoto() {
        return profilFoto;
    }

    public void setProfilFoto(String profilFoto) {
        this.profilFoto = profilFoto;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getSoruZaman() {
        return soruZaman;
    }

    public void setSoruZaman(String soruZaman) {
        this.soruZaman = soruZaman;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public String getCevapZaman() {
        return cevapZaman;
    }

    public void setCevapZaman(String cevapZaman) {
        this.cevapZaman = cevapZaman;
    }

    public int getBegeniSayisi() {
        return begeniSayisi;
    }

    public void setBegeniSayisi(int begeniSayisi) {
        this.begeniSayisi = begeniSayisi;
    }

    public int getKimBegendi() {
        return kimBegendi;
    }

    public void setKimBegendi(int kimBegendi) {
        this.kimBegendi = kimBegendi;
    }
}
