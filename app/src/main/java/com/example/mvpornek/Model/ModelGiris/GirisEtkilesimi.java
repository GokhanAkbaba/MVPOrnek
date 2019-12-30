package com.example.mvpornek.Model.ModelGiris;

import com.example.mvpornek.Model.Kullanici;

public interface GirisEtkilesimi {

    interface GirisDinleyicisi{

        void girisKontrolIslemleri(Kullanici kullanici);

        void uygulamayiYokEt();

        void adSoyadHatasi();

        void eMailHatasi();

        void sifreHatasi();

        void sifreTekrarHatasi();

        void sifrelerFarkliHatasi();

        void kullaniciAdiHatasi();

        void girisBasarili();
    }

    void giris(Kullanici kullanici, GirisDinleyicisi dinleyici);
}
