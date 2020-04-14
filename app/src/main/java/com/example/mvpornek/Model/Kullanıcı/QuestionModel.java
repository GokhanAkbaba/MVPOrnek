package com.example.mvpornek.Model.Kullanıcı;

public class QuestionModel {
    public String kullaniciAdi;
    public String soru;
    public String yorumSayisi;
    public int kullaniciProfilResmi;
    public String etiket;


    public void setEtiket(String etiket) {
        this.etiket = etiket;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public void setYorumSayisi(String yorumSayisi) {
        this.yorumSayisi = yorumSayisi;
    }

    public void setKullaniciProfilResmi(int kullaniciProfilResmi) {
        this.kullaniciProfilResmi = kullaniciProfilResmi;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getSoru() {
        return soru;
    }

    public String getYorumSayisi() {
        return yorumSayisi;
    }

    public int getKullaniciProfilResmi() {
        return kullaniciProfilResmi;
    }
    public String getEtiket() {
        return etiket;
    }

    public QuestionModel(String kullaniciAdi, String soru, String yorumSayisi,String etiket, int kullaniciProfilResmi) {
        this.kullaniciAdi = kullaniciAdi;
        this.soru = soru;
        this.yorumSayisi = yorumSayisi;
        this.kullaniciProfilResmi = kullaniciProfilResmi;
        this.etiket=etiket;
    }
}
