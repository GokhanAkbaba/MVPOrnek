package com.example.mvpornek.Presenter;

import com.example.mvpornek.Response.SoruSilResponse;
import com.example.mvpornek.Response.TokenOlusturResponse;
import com.example.mvpornek.View.TokenCreateView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class TokenCreatePresenterImpl implements TokenCreatePresenter {
    TokenCreateView tokenCreateView;

    public TokenCreatePresenterImpl(TokenCreateView tokenCreateView) {
        this.tokenCreateView = tokenCreateView;
    }

    @Override
    public void createToken(int kullaniciID, String token) {
        Call<TokenOlusturResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .tokenOlustur(kullaniciID,token);

        call.enqueue(new Callback<TokenOlusturResponse>() {
            @Override
            public void onResponse(Call<TokenOlusturResponse> call, Response<TokenOlusturResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    tokenCreateView.showTokenSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<TokenOlusturResponse> call, Throwable t) {
                  tokenCreateView.showTokenFailedMessage();

            }
        });
    }
}
