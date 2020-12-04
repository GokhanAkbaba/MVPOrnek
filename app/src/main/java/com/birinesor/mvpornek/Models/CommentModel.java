package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentModel {
    @SerializedName("cevap_id")
    @Expose
    private int cevap_id;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("kullanici_id")
    @Expose
    private int kullaniciId;
    @SerializedName("cevap")
    @Expose
    private String cevap;
    @SerializedName("zaman")
    @Expose
    private String zaman;
    @SerializedName("profil_foto")
    @Expose
    private String profilFoto;
    @SerializedName("begeni_sayisi")
    @Expose
    private String begeniSayisi;
    @SerializedName("kim_begendi")
    @Expose
    private int kimBegendi;
    @SerializedName("ad_soyad")
    @Expose
    private String ad_soyad;

    public String getAd_soyad() {
        return ad_soyad;
    }

    public int getKimBegendi() {
        return kimBegendi;
    }

    public void setKimBegendi(int kimBegendi) {
        this.kimBegendi = kimBegendi;
    }

    public String getBegeniSayisi() {
        return begeniSayisi;
    }

    public void setBegeniSayisi(String begeniSayisi) {
        this.begeniSayisi = begeniSayisi;
    }

    public int getCevap_id() {
        return cevap_id;
    }

    public void setCevap_id(int cevap_id) {
        this.cevap_id = cevap_id;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public int getId() {
        return cevap_id;
    }

    public void setId(int cevap_id) {
        this.cevap_id = cevap_id;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
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
}
