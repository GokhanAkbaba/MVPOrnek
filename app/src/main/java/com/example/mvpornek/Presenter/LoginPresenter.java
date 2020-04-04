package com.example.mvpornek.Presenter;

public interface LoginPresenter {

    void validateCredentials(String kullaniciAdi,String adSoyad, String sifre, String sifreTekrar, String ePosta );
    void onDestroy();
}
