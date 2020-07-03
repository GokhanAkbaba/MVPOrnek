package com.example.mvpornek.Presenter.ProfilCevaplarim;

import com.example.mvpornek.Models.AnswersModel;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.View.ProfilAnswersView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilAnswersPresenterImpl implements ProfilAnswersPresenter {
    ProfilAnswersView profilAnswersView;

    public ProfilAnswersPresenterImpl(ProfilAnswersView profilAnswersView) {
        this.profilAnswersView = profilAnswersView;
    }

    @Override
    public void loadData(int kullaniciId) {
        Call<List<AnswersModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kullaniciCevaplariGetir(kullaniciId);

        call.enqueue(new Callback<List<AnswersModel>>() {
            @Override
            public void onResponse(Call<List<AnswersModel>> call, Response<List<AnswersModel>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    profilAnswersView.onGetResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<AnswersModel>> call, Throwable t) {
                profilAnswersView.onErrorLoading();
            }
        });
    }
}
