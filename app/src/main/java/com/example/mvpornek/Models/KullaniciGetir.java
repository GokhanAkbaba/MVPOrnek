package com.example.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KullaniciGetir {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ad_soyad")
    @Expose
    private String adSoyad;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("profil_foto")
    @Expose
    private String profilFoto;
    @SerializedName("cevapSayisi")
    @Expose
    private int cevapSayisi;
    @SerializedName("soruSayisi")
    @Expose
    private int soruSayisi;

    public KullaniciGetir(int id, String adSoyad, String kullaniciAdi, String profilFoto, int cevapSayisi,int soruSayisi) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.profilFoto = profilFoto;
        this.cevapSayisi = cevapSayisi;
        this.soruSayisi=soruSayisi;
    }

    public int getSoruSayisi() {
        return soruSayisi;
    }

    public void setSoruSayisi(int soruSayisi) {
        this.soruSayisi = soruSayisi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCevapSayisi() {
        return cevapSayisi;
    }

    public void setCevapSayisi(int cevapSayisi) {
        this.cevapSayisi = cevapSayisi;
    }
}
