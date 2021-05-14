package com.birinesor.mvpornek.Model.KullaniciKazanc;

import android.content.Context;
import android.text.TextUtils;

import com.birinesor.mvpornek.Response.KullaniciKazancLog;
import com.birinesor.mvpornek.Response.KullaniciResponse;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KullaniciKazancInteractorImpl implements KullaniciKazancInteractor {
    Context context;
    public KullaniciKazancInteractorImpl(Context context){
        this.context=context;

    }
    @Override
    public void KullaniciKazancLogKayit(int kullaniciID, String tutar, String iban, String adSoyad, onKullaniciKazancLogKayitListener listener) {
        if(TextUtils.isEmpty(iban) || iban.trim().equals("")){
            listener.onIbanHata("Boş Bırakılmaz");
            return;
        }else{
            listener.onIbanOnay();
        }

        if(TextUtils.isEmpty(adSoyad) || adSoyad.trim().equals("")){
            listener.onAdSoyadHatasi("Boş Bırakılmaz");
            return;
        }else{
            listener.onAdSoyadOnay();
        }
        Call<KullaniciKazancLog> call=RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kullaniciKazancLogKayit(kullaniciID,tutar,iban,adSoyad);

        call.enqueue(new Callback<KullaniciKazancLog>() {
            @Override
            public void onResponse(Call<KullaniciKazancLog> call, Response<KullaniciKazancLog> response) {
                KullaniciKazancLog kullaniciResponse=response.body();
                if (response.isSuccessful() && response.body() !=null)
                {
                    listener.onKullaniciKazancSuccess();
                }
            }
            @Override
            public void onFailure(Call<KullaniciKazancLog> call, Throwable t) {
                System.out.println("Bağlantı Hatası(Kullanici Kazanc Sayfası)"+t.getMessage());
            }
        });
    }
}
