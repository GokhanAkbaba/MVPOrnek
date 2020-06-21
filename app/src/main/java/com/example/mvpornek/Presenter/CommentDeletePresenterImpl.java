package com.example.mvpornek.Presenter;

import android.widget.Toast;

import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.CommentModel;
import com.example.mvpornek.Model.Response.CevapSilResponse;
import com.example.mvpornek.View.CommentDeleteView;
import com.example.mvpornek.WebService.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentDeletePresenterImpl implements CommentDeletePresenter {
    CommentDeleteView commentDeleteView;

    public CommentDeletePresenterImpl(CommentDeleteView commentDeleteView) {
        this.commentDeleteView = commentDeleteView;
    }

    @Override
    public void deleteOptions(int cevapId) {
        Call<CevapSilResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .cevapSil(cevapId);

        call.enqueue(new Callback<CevapSilResponse>() {
            @Override
            public void onResponse(Call<CevapSilResponse> call, Response<CevapSilResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    commentDeleteView.showSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<CevapSilResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                    commentDeleteView.showFailedMessage();

            }
        });
    }
}
