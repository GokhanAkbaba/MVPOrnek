package com.birinesor.mvpornek.Presenter.TokenSil;

import com.birinesor.mvpornek.Response.TokenSilResponse;
import com.birinesor.mvpornek.View.TokenDeleteView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class TokenDeletePresenterImpl implements TokenDeletePresenter {
    TokenDeleteView tokenDeleteView;

    public TokenDeletePresenterImpl(TokenDeleteView tokenDeleteView) {
        this.tokenDeleteView = tokenDeleteView;
    }

    @Override
    public void deleteToken(int kullaniciID) {
        Call<TokenSilResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .tokenSil(kullaniciID);

        call.enqueue(new Callback<TokenSilResponse>() {
            @Override
            public void onResponse(Call<TokenSilResponse> call, Response<TokenSilResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    tokenDeleteView.showTokenDeleteSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<TokenSilResponse> call, Throwable t) {
                tokenDeleteView.showTokenDeleteFailedMessage(t.getMessage());

            }
        });
    }
}
