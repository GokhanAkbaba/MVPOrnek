package com.birinesor.mvpornek.Presenter.Register;

public interface RegisterPresenter {
    void validateCredentials(String kullaniciAdi,String adSoyad, String sifre, String sifreTekrar, String ePosta );
    void onDestroy();
}
