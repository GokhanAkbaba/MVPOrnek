package com.example.mvpornek.Presenter;

import com.example.mvpornek.Model.Kullanıcı.QuestionModel;
import com.example.mvpornek.Model.Response.EtiketResponse;
import com.example.mvpornek.Model.Response.SorularResponse;
import com.example.mvpornek.View.QuestionView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionPresenterImpl implements QuestionPresenter {
    QuestionView questionView;

    public QuestionPresenterImpl(QuestionView questionView) {
        this.questionView = questionView;
    }

    @Override
    public void loadData(int kullaniciId) {

        questionView.showLoading();
        Call<List<QuestionModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .sorulariGetir(kullaniciId);

        call.enqueue(new Callback<List<QuestionModel>>() {
            @Override
            public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {
                questionView.hideLoading();
                if (response.isSuccessful() && response.body() !=null) {
                    questionView.onGetResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
                questionView.hideLoading();
                questionView.onErrorLoading(t.getMessage());
            }
        });
    }
}
