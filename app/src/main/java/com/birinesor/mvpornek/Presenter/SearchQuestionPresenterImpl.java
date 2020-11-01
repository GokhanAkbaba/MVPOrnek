package com.birinesor.mvpornek.Presenter;

import com.birinesor.mvpornek.Models.SearchQuestionModel;
import com.birinesor.mvpornek.View.SearchQuestionView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchQuestionPresenterImpl implements SearchQuestionPresenter{

    SearchQuestionView searchQuestionView;

    public SearchQuestionPresenterImpl(SearchQuestionView searchQuestionView) {
        this.searchQuestionView = searchQuestionView;
    }

    @Override
    public void loadData() {

        Call<List<SearchQuestionModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .aramaSorulariGetir("test");

        call.enqueue(new Callback<List<SearchQuestionModel>>() {
            @Override
            public void onResponse(Call<List<SearchQuestionModel>> call, Response<List<SearchQuestionModel>> response) {
                searchQuestionView.hideLoading();
                if (response.isSuccessful() && response.body() !=null) {
                    searchQuestionView.onGetResult(response.body());
                }
                List<SearchQuestionModel> data=response.body();
                if(data == null || data.isEmpty() ){
                    searchQuestionView.onGetResultControl();
                }
            }
            @Override
            public void onFailure(Call<List<SearchQuestionModel>> call, Throwable t) {
                searchQuestionView.hideLoading();
                searchQuestionView.onErrorLoading(t.getMessage());
            }
        });

    }
}
