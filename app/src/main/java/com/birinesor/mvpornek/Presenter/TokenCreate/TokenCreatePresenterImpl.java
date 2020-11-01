package com.birinesor.mvpornek.Presenter.TokenCreate;

import com.birinesor.mvpornek.Response.TokenOlusturResponse;
import com.birinesor.mvpornek.View.TokenCreateView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

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
