package com.birinesor.mvpornek.Model.SifremiUnuttum;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.birinesor.mvpornek.Response.SifremiUnuttumResponse;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class ForgotPasswordInteractorImpl implements ForgotPasswordInteractor {
    Context context;

    public ForgotPasswordInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void EmailControl(String eposta, onForgotFinishedListener listener) {

        if (TextUtils.isEmpty(eposta)) {
            listener.onEpostaHatasi("e-Posta Boş Bırakmayınız");
            return;
        }

        Call<SifremiUnuttumResponse> call=RetrofitClientInstance
                .getInstance()
                .getDataService()
                .sifremiUnuttum(eposta);
        call.enqueue(new Callback<SifremiUnuttumResponse>() {
            @Override
            public void onResponse(Call<SifremiUnuttumResponse> call, Response<SifremiUnuttumResponse> response) {
                SifremiUnuttumResponse sifremiUnuttumResponse= response.body();
                Boolean hata=sifremiUnuttumResponse.getError();

                if (response.isSuccessful() && response.body() !=null)
                {
                    if(hata==false)
                    {
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        listener.onSuccess();
                    }else{
                        listener.onEpostaHatasi(sifremiUnuttumResponse.getMessage());
                    }
                }
                else
                {
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SifremiUnuttumResponse> call, Throwable t) {
                System.out.println("HATA"+t.getMessage());
            }
        });

    }
}
