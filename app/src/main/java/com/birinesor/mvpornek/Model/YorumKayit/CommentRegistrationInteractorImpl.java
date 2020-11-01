package com.birinesor.mvpornek.Model.YorumKayit;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.birinesor.mvpornek.Response.CevapKaydetResponse;
import com.birinesor.mvpornek.View.CommentRegistrationView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRegistrationInteractorImpl implements CommentRegistrationInteractor {
    Context context;
    CommentRegistrationView commentRegistrationView;

    public CommentRegistrationInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void CommentRegistration(int kullaniciId, int soruId, String cevap, onCommentRegistrationInteractor listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Call<CevapKaydetResponse> call= RetrofitClientInstance
                        .getInstance()
                        .getDataService()
                        .cevapKaydet(kullaniciId,soruId,cevap);

                call.enqueue(new Callback<CevapKaydetResponse>() {
                    @Override
                    public void onResponse(Call<CevapKaydetResponse> call, Response<CevapKaydetResponse> response) {
                        CevapKaydetResponse cevapKaydetResponse=response.body();
                        Boolean hata=cevapKaydetResponse.getError();
                        if (response.isSuccessful() && response.body() !=null)
                        {
                            if(hata==false)
                            {
                                listener.onSuccess();
                            }else{
                                Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                commentRegistrationView.hideProgress();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<CevapKaydetResponse> call, Throwable t) {
                        System.out.println("Bağlantı Hatası(YorumKayit) "+t.getMessage());
                    }
                });
            }
        },1000);


    }
}
