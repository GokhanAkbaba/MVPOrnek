package com.birinesor.mvpornek.View;

public interface ProfilUpdateView {
    void showProgress();
    void hideProgress();
    void setGuncelleEPostaAdiHatasi(String message);
    void setGuncelleKullaniciAdiHatasi(String message);
    void setGuncelleKullaniciAdiSoyad();
    void setGuncelleKullaniciProfilFoto(String message);
    void navigateToProfilUpdate();
}
