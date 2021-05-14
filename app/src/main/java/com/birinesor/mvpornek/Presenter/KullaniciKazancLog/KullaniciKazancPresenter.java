package com.birinesor.mvpornek.Presenter.KullaniciKazancLog;

public interface KullaniciKazancPresenter {
    void validateKullaniciKazanc(int id,String tutar,String iban, String adSoyad);
    void onKullaniciKazancDestroy();
}
