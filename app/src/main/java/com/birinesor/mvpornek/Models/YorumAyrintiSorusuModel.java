package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class YorumAyrintiSorusuModel {

    @SerializedName("ad_soyad")
    @Expose
    private String adSoyad;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("soru_id")
    @Expose
    private int soru_id;
    @SerializedName("kullanici_id")
    @Expose
    private int kullanici_id;
    @SerializedName("profil_foto")
    @Expose
    private String profilFoto;
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("soruZaman")
    @Expose
    private String soruZaman;
    @SerializedName("etiket")
    @Expose
    private String etiket;
    @SerializedName("yorum_sayisi")
    @Expose
    private int yorum_sayisi;

    public YorumAyrintiSorusuModel(String adSoyad, String kullaniciAdi, int soru_id, int kullanici_id, String profilFoto, String soru, String soruZaman, String etiket, int yorumSayisi) {
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.soru_id = soru_id;
        this.kullanici_id = kullanici_id;
        this.profilFoto = profilFoto;
        this.soru = soru;
        this.soruZaman = soruZaman;
        this.etiket = etiket;
        this.yorum_sayisi = yorumSayisi;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public int getSoru_id() {
        return soru_id;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public String getProfilFoto() {
        return profilFoto;
    }

    public String getSoru() {
        return soru;
    }

    public String getSoruZaman() {
        return soruZaman;
    }

    public String getEtiket() {
        return etiket;
    }

    public int getYorumSayisi() {
        return yorum_sayisi;
    }
}
