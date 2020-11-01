package com.birinesor.mvpornek.Models;

public class NotificationModel {
    public String kullaniciAdi;
    public String bildirimAciklama;
    public String bildirimNeden;
    public String bildirimZaman;
    public int kullaniciResimleri;

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public void setBildirimAciklama(String bildirimAciklama) {
        this.bildirimAciklama = bildirimAciklama;
    }

    public void setBildirimNeden(String bildirimNeden) {
        this.bildirimNeden = bildirimNeden;
    }

    public void setBildirimZaman(String bildirimZaman) {
        this.bildirimZaman = bildirimZaman;
    }

    public void setKullaniciResimleri(int kullaniciResimleri) {
        this.kullaniciResimleri = kullaniciResimleri;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getBildirimAciklama() {
        return bildirimAciklama;
    }

    public String getBildirimNeden() {
        return bildirimNeden;
    }

    public String getBildirimZaman() {
        return bildirimZaman;
    }

    public int getKullaniciResimleri() {
        return kullaniciResimleri;
    }

    public NotificationModel(String kullaniciAdi, String bildirimAciklama, String bildirimNeden, String bildirimZaman, int kullaniciResimleri) {
        this.kullaniciAdi = kullaniciAdi;
        this.bildirimAciklama = bildirimAciklama;
        this.bildirimNeden = bildirimNeden;
        this.bildirimZaman = bildirimZaman;
        this.kullaniciResimleri = kullaniciResimleri;
    }
}
