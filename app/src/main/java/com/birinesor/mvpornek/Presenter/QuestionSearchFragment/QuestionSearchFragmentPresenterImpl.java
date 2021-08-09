package com.birinesor.mvpornek.Presenter.QuestionSearchFragment;

import com.birinesor.mvpornek.Models.SearchQuestionModel;
import com.birinesor.mvpornek.Presenter.QuestionSearchFragment.QuestionSearchFragmentPresenter;
import com.birinesor.mvpornek.View.QuestionSearchFragmentView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionSearchFragmentPresenterImpl implements QuestionSearchFragmentPresenter {
    QuestionSearchFragmentView questionSearchFragmentView;

    public QuestionSearchFragmentPresenterImpl(QuestionSearchFragmentView questionSearchFragmentView) {
        this.questionSearchFragmentView = questionSearchFragmentView;
    }

    @Override
    public void loadData(String ifade,int etiketID,int secenek) {
        questionSearchFragmentView.showLoading();
        Call<List<SearchQuestionModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .aramaSoruGetir(ifade,etiketID,secenek);

        call.enqueue(new Callback<List<SearchQuestionModel>>() {
            @Override
            public void onResponse(Call<List<SearchQuestionModel>> call, Response<List<SearchQuestionModel>> response) {
                questionSearchFragmentView.hideLoading();
                if (response.isSuccessful() && response.body() !=null) {
                    questionSearchFragmentView.onGetResult(response.body());
                }
                List<SearchQuestionModel> data=response.body();
                if(data == null || data.isEmpty() ){
                    questionSearchFragmentView.onGetResultControl();
                }
            }
            @Override
            public void onFailure(Call<List<SearchQuestionModel>> call, Throwable t) {
                questionSearchFragmentView.hideLoading();
                questionSearchFragmentView.onErrorLoading(t.getMessage());
            }
        });
    }
    }

