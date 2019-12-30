package com.example.mvpornek.Model.ModelGiris;
import android.os.Handler;
import android.text.TextUtils;
import com.example.mvpornek.Model.Kullanici;

public class GirisUygulamasi implements GirisEtkilesimi {

    @Override
    public void giris(final Kullanici kullanici, final GirisDinleyicisi dinleyici) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(kullanici.getAdSoyad().equals("")){
                    dinleyici.adSoyadHatasi();
                    return;
                }

                if(kullanici.getSifre().equals(""))
                {
                    dinleyici.sifreHatasi();
                    return;
                }

                if(kullanici.getSifreTekrar().equals(""))
                {
                    dinleyici.sifreTekrarHatasi();
                    return;
                }

                if(kullanici.getKullaniciAdi().equals(""))
                {
                    dinleyici.kullaniciAdiHatasi();
                    return;
                }

                if(kullanici.geteMail().equals(""))
                {
                    dinleyici.eMailHatasi();
                    return;
                }

                if(!kullanici.getSifre().equals(kullanici.getSifreTekrar()))
                {
                    dinleyici.sifrelerFarkliHatasi();
                    return;
                }
                //giris işlemlerindeki tüm kontroller burada sağlanacaktır.
                dinleyici.girisBasarili();
            }
        },1000);
    }
}
