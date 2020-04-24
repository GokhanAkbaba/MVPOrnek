package com.example.mvpornek.Model.ModelGiris;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.mvpornek.Activity.RegisterFragment;
import com.example.mvpornek.Model.Kullanıcı.Kullanici;
import com.example.mvpornek.WebService.GetDataService;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterInteractorImpl implements RegisterInteractor {
    String hata=null;
    @Override
    public void Register(final String kullaniciAdi, final String adSoyad, final String sifre, final String sifreTekrar, final String ePosta, final onRegisterFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (TextUtils.isEmpty(adSoyad)) {
                    listener.onAdSoyadHatasi();
                    return;
                }

                if (TextUtils.isEmpty(kullaniciAdi)) {
                    listener.onKullaniciAdiHatasi("Kullanıcı Adı Boş Bırakmayınız");
                    return;
                }

                if (TextUtils.isEmpty(sifre)) {
                    listener.onSifreHatasi();
                    return;
                }

                if (TextUtils.isEmpty(sifreTekrar)) {
                    listener.onSifreTekrarHatasi();
                    return;
                }

                if (TextUtils.isEmpty(ePosta)) {
                    listener.onEpostaHatasi("e-Posta Boş Bırakmayınız");
                    return;
                }

                if (!TextUtils.equals(sifre, sifreTekrar)) {
                    listener.onSifreKontrol();
                    return;
                }
                    Call<ResponseBody> call=RetrofitClientInstance
                            .getInstance()
                            .getDataService()
                            .kullaniciKayit(adSoyad,kullaniciAdi,ePosta,sifre,sifreTekrar);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    if(response.code() ==201)
                                    {
                                        hata=response.body().string();

                                    }else{
                                        hata=response.body().string();
                                    }
                                }catch (IOException e){
                                    e.printStackTrace();
                                }

                                if (hata!=null)
                                {
                                    int a;
                                    try{
                                        JSONObject jsonObject= new JSONObject(hata);
                                        a=jsonObject.getInt("code");
                                        if(a==156)
                                        {
                                            listener.onEpostaHatasi(jsonObject.getString("message"));
                                        }else if(a==155){
                                            listener.onKullaniciAdiHatasi(jsonObject.getString("message"));
                                        }
                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            System.out.println("Bağlantı Hatası "+t.getMessage());
                        }
                    });

                    listener.onSuccess();

            }
        }, 1000);
    }

}
