package com.example.mvpornek.Presenter.Like;

import com.example.mvpornek.Presenter.Like.LikesPresenter;
import com.example.mvpornek.Response.LikeModel;
import com.example.mvpornek.View.LikesView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikesPresenterImpl implements LikesPresenter {
    LikesView likesView;

    public LikesPresenterImpl(LikesView likesView) {
        this.likesView = likesView;
    }
    @Override
    public void loadLikes(int cevapId, int kullaniciId, int begeni, int durum) {
        Call<LikeModel> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .begeniYap(cevapId,kullaniciId,begeni,durum);

        call.enqueue(new Callback<LikeModel>() {
            @Override
            public void onResponse(Call<LikeModel> call, Response<LikeModel> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    likesView.onGetLike(response.body());
                }
            }
            @Override
            public void onFailure(Call<LikeModel> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
