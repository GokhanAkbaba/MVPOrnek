package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class YorumAyrintiSorusuYorumlarModel {
    @SerializedName("kullanici_id")
    @Expose
    private int kullaniciId;
    @SerializedName("ad_soyad")
    @Expose
    private String adSoyad;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("cevap")
    @Expose
    private String cevap;
    @SerializedName("cevap_id")
    @Expose
    private int cevapId;
    @SerializedName("profil_foto")
    @Expose
    private String profil_foto;
    @SerializedName("zaman")
    @Expose
    private String zaman;
    @SerializedName("begeniSayisi")
    @Expose
    private int begeni_sayisi;
    @SerializedName("kim_begendi")
    @Expose
    private int kim_begendi;

    public int getKullaniciId() {
        return kullaniciId;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getCevap() {
        return cevap;
    }

    public int getCevapId() {
        return cevapId;
    }

    public String getProfil_foto() {
        return profil_foto;
    }

    public String getZaman() {
        return zaman;
    }

    public int getBegeni_sayisi() {
        return begeni_sayisi;
    }

    public int getKim_begendi() {
        return kim_begendi;
    }

    public YorumAyrintiSorusuYorumlarModel(int kullaniciId, String adSoyad, String kullaniciAdi, String cevap, int cevapId, String profil_foto, String zaman, int begeni_sayisi, int kim_begendi) {
        this.kullaniciId = kullaniciId;
        this.adSoyad = adSoyad;
        this.kullaniciAdi = kullaniciAdi;
        this.cevap = cevap;
        this.cevapId = cevapId;
        this.profil_foto = profil_foto;
        this.zaman = zaman;
        this.begeni_sayisi = begeni_sayisi;
        this.kim_begendi = kim_begendi;
    }
}
