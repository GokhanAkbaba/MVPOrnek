package com.example.mvpornek.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class NotificationCommentAndLikeModel {
    @SerializedName("yorumSayisi")
    @Expose
    private int yorumSayisi;
    @SerializedName("soruSoranID")
    @Expose
    private int soruSoranID;
    @SerializedName("soruSoranAdSoyad")
    @Expose
    private String soruSoranAdSoyad;
    @SerializedName("soruSoranKullaniciAdi")
    @Expose
    private String soruSoranKullaniciAdi;
    @SerializedName("soruSoranProfilFoto")
    @Expose
    private String soruSoranProfilFoto;
    @SerializedName("soruID")
    @Expose
    private int soruID;
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("soruEtiket")
    @Expose
    private String soruEtiket;
    @SerializedName("soruZaman")
    @Expose
    private String soruZaman;
    @SerializedName("cevapVerenKullaniciID")
    @Expose
    private int cevapVerenKullaniciID;
    @SerializedName("cevapVerenAdSoyad")
    @Expose
    private String cevapVerenAdSoyad;
    @SerializedName("cevapVerenKullaniciAdi")
    @Expose
    private String cevapVerenKullaniciAdi;
    @SerializedName("cevapVerenKullaniciProfilFoto")
    @Expose
    private String cevapVerenKullaniciProfilFoto;
    @SerializedName("cevapID")
    @Expose
    private int cevapID;
    @SerializedName("cevap")
    @Expose
    private String cevap;
    @SerializedName("cevapZaman")
    @Expose
    private String cevapZaman;
    @SerializedName("cevapBegeniSayisi")
    @Expose
    private int cevapBegeniSayisi;

    public NotificationCommentAndLikeModel(int yorumSayisi, int soruSoranID, String soruSoranAdSoyad, String soruSoranKullaniciAdi, String soruSoranProfilFoto, int soruID, String soru, String soruEtiket, String soruZaman, int cevapVerenKullaniciID, String cevapVerenAdSoyad, String cevapVerenKullaniciAdi,String cevapVerenKullaniciProfilFoto, int cevapID, String cevap, String cevapZaman, int cevapBegeniSayisi) {
        this.yorumSayisi = yorumSayisi;
        this.soruSoranID = soruSoranID;
        this.soruSoranAdSoyad = soruSoranAdSoyad;
        this.soruSoranKullaniciAdi = soruSoranKullaniciAdi;
        this.soruSoranProfilFoto = soruSoranProfilFoto;
        this.soruID = soruID;
        this.soru = soru;
        this.soruEtiket = soruEtiket;
        this.soruZaman = soruZaman;
        this.cevapVerenKullaniciID = cevapVerenKullaniciID;
        this.cevapVerenAdSoyad = cevapVerenAdSoyad;
        this.cevapVerenKullaniciAdi = cevapVerenKullaniciAdi;
        this.cevapID = cevapID;
        this.cevap = cevap;
        this.cevapZaman = cevapZaman;
        this.cevapBegeniSayisi = cevapBegeniSayisi;
        this.cevapVerenKullaniciProfilFoto=cevapVerenKullaniciProfilFoto;
    }

    public int getYorumSayisi() {
        return yorumSayisi;
    }

    public int getSoruSoranID() {
        return soruSoranID;
    }

    public String getSoruSoranAdSoyad() {
        return soruSoranAdSoyad;
    }

    public String getSoruSoranKullaniciAdi() {
        return soruSoranKullaniciAdi;
    }

    public String getSoruSoranProfilFoto() {
        return soruSoranProfilFoto;
    }

    public int getSoruID() {
        return soruID;
    }

    public String getSoru() {
        return soru;
    }

    public String getSoruEtiket() {
        return soruEtiket;
    }

    public String getSoruZaman() {
        return soruZaman;
    }

    public int getCevapVerenKullaniciID() {
        return cevapVerenKullaniciID;
    }

    public String getCevapVerenAdSoyad() {
        return cevapVerenAdSoyad;
    }

    public String getCevapVerenKullaniciAdi() {
        return cevapVerenKullaniciAdi;
    }

    public String getCevapVerenKullaniciProfilFoto() {
        return cevapVerenKullaniciProfilFoto;
    }

    public int getCevapID() {
        return cevapID;
    }

    public String getCevap() {
        return cevap;
    }

    public String getCevapZaman() {
        return cevapZaman;
    }

    public int getCevapBegeniSayisi() {
        return cevapBegeniSayisi;
    }
}

