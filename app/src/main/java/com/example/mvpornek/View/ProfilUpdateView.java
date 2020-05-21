package com.example.mvpornek.View;

public interface ProfilUpdateView {
    void showProgress();
    void hideProgress();
    void setGuncelleEPostaAdiHatasi();
    void setGuncelleKullaniciAdiHatasi();
    void setGuncelleKullaniciAdiSoyad();
    void setGuncelleKullaniciProfilFoto(String message);
    void navigateToProfilUpdate();
}
