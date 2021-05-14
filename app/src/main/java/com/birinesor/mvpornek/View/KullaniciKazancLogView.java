package com.birinesor.mvpornek.View;

public interface KullaniciKazancLogView {
    void showKullaniciKazancProgress();
    void hideKullaniciKazancProgress();
    void setIbanHatasi(String error);
    void setIbanOnay();
    void setAdSoyadHatasi(String error);
    void setAdSoyadOnay();
    void kullaniciKazancToHome();
}
