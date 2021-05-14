package com.birinesor.mvpornek.Model.KullaniciKazanc;

public interface KullaniciKazancInteractor {
    interface onKullaniciKazancLogKayitListener{
        void onIbanHata(String error);
        void onIbanOnay();
        void onAdSoyadHatasi(String error);
        void onAdSoyadOnay();
        void onKullaniciKazancSuccess();
    }
    void KullaniciKazancLogKayit(int kullaniciID,String tutar, String iban, String adSoyad,onKullaniciKazancLogKayitListener listener);
}

