package com.birinesor.mvpornek.View;

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void setKullaniciAdiHatasi(String error);
    void setKullaniciAdiOnay();
    void setAdSoyadHatasi();
    void setAdSoyadOnay();
    void setSifeHatasi();
    void setSifreHatasiOnay();
    void setEpostaHatasi(String error);
    void setEpostaOnay();
    void setSifreTekrarHatasi();
    void setSifreTekrarOnay();
    void setSifreKontrol();
    void navigateToHome();
}
