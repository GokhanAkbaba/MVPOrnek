package com.example.mvpornek.Model.ModelGiris;

import com.example.mvpornek.Model.Kullanıcı.EtiketResponse;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeginingInteractorImpl implements BeginingInteractor {
    @Override
    public void optionsSave(int id, int il, int secim, final onBeginingFinishedListener listener) {

        if(il==0){
            listener.onIlHatasi();
            return;
        }

        if(secim ==0){
            listener.onEtiketHatasi();
            return;
        }
        Call<EtiketResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .etiketKayit(id,secim,il);

        call.enqueue(new Callback<EtiketResponse>() {
            @Override
            public void onResponse(Call<EtiketResponse> call, Response<EtiketResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    listener.onSuccess();
                }
            }
            @Override
            public void onFailure(Call<EtiketResponse> call, Throwable t) {
                System.out.println("Bağlantı Hatası "+t.getMessage());
            }
        });

    }
}
