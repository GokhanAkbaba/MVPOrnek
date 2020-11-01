package com.birinesor.mvpornek.Presenter.YorumSil;

import com.birinesor.mvpornek.Response.CevapSilResponse;
import com.birinesor.mvpornek.View.CommentDeleteView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;
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
