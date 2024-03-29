package com.birinesor.mvpornek.Model.ProfilGuncelle;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.birinesor.mvpornek.Response.KullaniciResponse;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilUpdateInteractorImpl implements ProfilUpdateInteractor {

    Context context;

    public ProfilUpdateInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void profilUpdate(int id,String adSoyad, String kullaniciAdi, String kullaniciEposta,String profilResmi,onProfilUpdateFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(kullaniciEposta)){
                    listener.onGuncelleEPostaHatasi("E-posta Adresini Boş Bırakmayınız");
                    return;
                }
                if(kullaniciEposta.trim().equals("")){
                    listener.onGuncelleEPostaHatasi("E-posta Adresini Boş Bırakmayınız");
                    return;
                }
                if(TextUtils.isEmpty(kullaniciAdi)){
                    listener.onGuncelleKullaniciAdiHatasi("Kullanıcı Adını Boş Bırakmayınız");
                    return;
                }
                if(kullaniciAdi.trim().equals("")){
                    listener.onGuncelleKullaniciAdiHatasi("Kullanıcı Adını Boş Bırakmayınız");
                    return;
                }
                if(TextUtils.isEmpty(adSoyad)){
                    listener.onGuncelleKullaniciAdSoyad();
                    return;
                }
                if(adSoyad.trim().equals("")){
                    listener.onGuncelleKullaniciAdSoyad();
                    return;
                }

                Call<KullaniciResponse> call= RetrofitClientInstance
                        .getInstance()
                        .getDataService()
                        .profilGuncelle(id,adSoyad,kullaniciAdi,kullaniciEposta,profilResmi);
                call.enqueue(new Callback<KullaniciResponse>() {
                    @Override
                    public void onResponse(Call<KullaniciResponse> call, Response<KullaniciResponse> response) {
                        KullaniciResponse kullaniciResponse=response.body();
                        if (response.isSuccessful() && response.body() !=null)
                        {
                            int hata=response.body().getCode();
                            if(hata==156)
                            {
                                listener.onGuncelleEPostaHatasi(response.body().getMessage());

                            }else if(hata==155){
                                listener.onGuncelleKullaniciAdiHatasi(response.body().getMessage());
                            }else if(hata==104)
                            {
                                SharedPrefManager.getInstance(context)
                                        .kullaniciKayit(kullaniciResponse.getKullanici());
                                listener.onSuccess();
                            }

                        }
                        else
                        {
                            Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<KullaniciResponse> call, Throwable t) {
                            listener.onGuncelleKullaniciProfilFoto("Bir Sorun Oluştu");
                     
                    }
                });
            }
        },1000);

    }
}
