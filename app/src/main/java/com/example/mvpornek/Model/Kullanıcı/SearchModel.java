package com.example.mvpornek.Model.Kullanıcı;

import java.io.Serializable;

public class SearchModel  implements Serializable {
    public String kullaniciAdi;
    public String soru;
    public String yorumSayisi;
    public int kullaniciProfilResmi;
    public String etiket;

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

    public String getYorumSayisi() {
        return yorumSayisi;
    }

    public void setYorumSayisi(String yorumSayisi) {
        this.yorumSayisi = yorumSayisi;
    }

    public int getKullaniciProfilResmi() {
        return kullaniciProfilResmi;
    }

    public void setKullaniciProfilResmi(int kullaniciProfilResmi) {
        this.kullaniciProfilResmi = kullaniciProfilResmi;
    }

    public String getEtiket() {
        return etiket;
    }

    public void setEtiket(String etiket) {
        this.etiket = etiket;
    }

    public SearchModel(String kullaniciAdi, String soru, String yorumSayisi, String etiket, int kullaniciProfilResmi) {
        this.kullaniciAdi = kullaniciAdi;
        this.soru = soru;
        this.yorumSayisi = yorumSayisi;
        this.kullaniciProfilResmi = kullaniciProfilResmi;
        this.etiket = etiket;
    }


}
