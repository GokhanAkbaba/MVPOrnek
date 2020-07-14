package com.example.mvpornek.Presenter.ProfilBegeni;

import com.example.mvpornek.Models.LikesModel;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.View.ProfilLikeView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilLikesPresenterImpl implements ProfilLikesPresenter{
    ProfilLikeView profilLikeView;

    public ProfilLikesPresenterImpl(ProfilLikeView profilLikeView) {
        this.profilLikeView = profilLikeView;
    }

    @Override
    public void loadData(int kullaniciId) {
        Call<List<LikesModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kullaniciBegenileriGetir(kullaniciId);

        call.enqueue(new Callback<List<LikesModel>>() {
            @Override
            public void onResponse(Call<List<LikesModel>> call, Response<List<LikesModel>> response) {

                if (response.isSuccessful() && response.body() !=null) {
                    profilLikeView.onGetResult(response.body());
                }
                List<LikesModel> data=response.body();
                if(data == null || data.isEmpty() ){
                    profilLikeView.onGetResultControl();
                }
            }
            @Override
            public void onFailure(Call<List<LikesModel>> call, Throwable t) {
                profilLikeView.onErrorLoading();
            }
        });
    }
}
