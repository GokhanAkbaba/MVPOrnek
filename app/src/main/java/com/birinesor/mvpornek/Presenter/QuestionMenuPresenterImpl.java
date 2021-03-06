package com.birinesor.mvpornek.Presenter;

import com.birinesor.mvpornek.Models.QuestionModel;
import com.birinesor.mvpornek.View.QuestionMenuView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionMenuPresenterImpl implements QuestionMenuPresenter {
    QuestionMenuView questionMenuView;

    public QuestionMenuPresenterImpl(QuestionMenuView questionMenuView) {
        this.questionMenuView = questionMenuView;
    }

    @Override
    public void loadData(int etiketId) {
        questionMenuView.showLoading();
        Call<List<QuestionModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .menuSorulariGetir(etiketId);

        call.enqueue(new Callback<List<QuestionModel>>() {
            @Override
            public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {
                questionMenuView.hideLoading();
                if (response.isSuccessful() && response.body() !=null) {
                    questionMenuView.onGetResult(response.body());
                }
                List<QuestionModel> data=response.body();
                if(data == null || data.isEmpty() ){
                    questionMenuView.onGetResultControl();
                }
            }
            @Override
            public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
                questionMenuView.hideLoading();
                questionMenuView.onErrorLoading(t.getMessage());
            }
        });
    }
}
