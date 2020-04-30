package com.example.mvpornek.Model.KullaniciGiris;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KullaniciGirisModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ad_soyad")
    @Expose
    private String adSoyad;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("kullanici_sifre")
    @Expose
    private String kullaniciSifre;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("kapak_foto")
    @Expose
    private String kapakFoto;
    @SerializedName("profil_foto")
    @Expose
    private String profilFoto;
    @SerializedName("kullanici_eposta")
    @Expose
    private String kullaniciEposta;

    public KullaniciGirisModel(int id, String adSoyad, String kullaniciAdi, String kullaniciSifre, String salt, String kapakFoto, String profilFoto, String kullaniciEposta) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.kullaniciSifre = kullaniciSifre;
        this.salt = salt;
        this.kapakFoto = kapakFoto;
        this.profilFoto = profilFoto;
        this.kullaniciEposta = kullaniciEposta;
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

    public String getKullaniciSifre() {
        return kullaniciSifre;
    }

    public void setKullaniciSifre(String kullaniciSifre) {
        this.kullaniciSifre = kullaniciSifre;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getKapakFoto() {
        return kapakFoto;
    }

    public void setKapakFoto(String kapakFoto) {
        this.kapakFoto = kapakFoto;
    }

    public String getProfilFoto() {
        return profilFoto;
    }

    public void setProfilFoto(String profilFoto) {
        this.profilFoto = profilFoto;
    }

    public String getKullaniciEposta() {
        return kullaniciEposta;
    }

    public void setKullaniciEposta(String kullaniciEposta) {
        this.kullaniciEposta = kullaniciEposta;
    }

}
