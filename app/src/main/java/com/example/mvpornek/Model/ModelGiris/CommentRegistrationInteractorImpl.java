package com.example.mvpornek.Model.ModelGiris;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mvpornek.Model.Response.CevapKaydetResponse;
import com.example.mvpornek.View.CommentRegistrationView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

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
    public void QuestionRegistration(int kullaniciId, int id, String soru, onCommentRegistrationInteractor listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Call<CevapKaydetResponse> call= RetrofitClientInstance
                        .getInstance()
                        .getDataService()
                        .cevapKaydet(kullaniciId,id,soru);

                call.enqueue(new Callback<CevapKaydetResponse>() {
                    @Override
                    public void onResponse(Call<CevapKaydetResponse> call, Response<CevapKaydetResponse> response) {
                        CevapKaydetResponse cevapKaydetResponse=response.body();
                        Boolean hata=cevapKaydetResponse.getError();
                        if (response.isSuccessful() && response.body() !=null)
                        {
                            if(hata==false)
                            {
                                Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                listener.onSuccess();
                            }else{
                                Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                commentRegistrationView.hideProgress();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<CevapKaydetResponse> call, Throwable t) {
                        System.out.println("Bağlantı Hatası "+t.getMessage());
                    }
                });
            }
        },1000);


    }
}
