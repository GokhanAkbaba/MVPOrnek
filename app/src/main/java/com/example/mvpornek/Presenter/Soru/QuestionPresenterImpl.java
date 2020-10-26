package com.example.mvpornek.Presenter.Soru;

import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.Presenter.Soru.QuestionPresenter;
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
                List<QuestionModel> data=response.body();
                if(data == null || data.isEmpty() ){
                    questionView.onGetQuestionResultControl("İlgi Alanlarınız ile ilgili Soru Bulunamadı");
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
