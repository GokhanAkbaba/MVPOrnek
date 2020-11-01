package com.birinesor.mvpornek.Presenter.Yorum;

import com.birinesor.mvpornek.Models.CommentModel;
import com.birinesor.mvpornek.View.CommentView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentPresenterImpl implements CommentPresenter {
    CommentView commentView;

    public CommentPresenterImpl(CommentView commentView) {
        this.commentView = commentView;
    }

    @Override
    public void loadData(int soruId) {
        Call<List<CommentModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .yorumlariGetir(soruId);

        call.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                   commentView.onGetResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                commentView.onErrorLoading(t.getMessage());
            }
        });
    }
}
