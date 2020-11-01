package com.birinesor.mvpornek.Model.Kayit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.birinesor.mvpornek.Response.KullaniciResponse;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterInteractorImpl implements RegisterInteractor {
Context context;

    public RegisterInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void Register(final String kullaniciAdi, final String adSoyad, final String sifre, final String sifreTekrar, final String ePosta, final onRegisterFinishedListener listener) {

        if (TextUtils.isEmpty(adSoyad)) {
            listener.onAdSoyadHatasi();
            return;
        }else{
            listener.onAdSoyadOnay();
        }

        if (TextUtils.isEmpty(kullaniciAdi)) {
            listener.onKullaniciAdiHatasi("Kullanıcı Adı Boş Bırakmayınız");
            return;
        }else{
            listener.onKullaniciAdiOnay();
        }

        if (TextUtils.isEmpty(sifre)) {
            listener.onSifreHatasi();
            return;
        }else{
            listener.onSifreHatasiOnay();
        }

        if (TextUtils.isEmpty(sifreTekrar)) {
            listener.onSifreTekrarHatasi();
            return;
        }else{
            listener.onSifreTekrarHatasiOnay();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(ePosta).matches()){
            listener.onEpostaHatasi("Hatalı e-Posta Girişi");
            return;
        }else
        {
            listener.onEpostaOnay();
        }
        if (TextUtils.isEmpty(ePosta)) {
            listener.onEpostaHatasi("e-Posta Boş Bırakmayınız");
            return;
        }else{
            listener.onEpostaOnay();
        }

        if (!TextUtils.equals(sifre, sifreTekrar)) {
            listener.onSifreKontrol();
            return;
        }

        Call<KullaniciResponse> call=RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kullaniciKayit(adSoyad,kullaniciAdi,ePosta,sifre,sifreTekrar);

        call.enqueue(new Callback<KullaniciResponse>() {
            @Override
            public void onResponse(Call<KullaniciResponse> call, Response<KullaniciResponse> response) {
                KullaniciResponse kullaniciResponse=response.body();
                if (response.isSuccessful() && response.body() !=null)
                {
                    int a=response.body().getCode();
                        if(a==156)
                        {
                            listener.onEpostaHatasi(response.body().getMessage());
                        }else if(a==155){
                            listener.onKullaniciAdiHatasi(response.body().getMessage());
                        }else if(a==157)
                        {

                            SharedPrefManager.getInstance(context)
                                    .kullaniciKayit(kullaniciResponse.getKullanici());
                            listener.onSuccess();
                        }
                }
            }
            @Override
            public void onFailure(Call<KullaniciResponse> call, Throwable t) {
                System.out.println("Bağlantı Hatası(Kayıt Sayfası)"+t.getMessage());
            }
        });

    }

}
