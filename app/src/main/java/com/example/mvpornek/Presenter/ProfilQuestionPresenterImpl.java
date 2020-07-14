package com.example.mvpornek.Presenter;

import com.example.mvpornek.Models.LikesModel;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.View.ProfilQuestionView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilQuestionPresenterImpl implements ProfilQuestionPresenter{

    ProfilQuestionView profilQuestionView;

    public ProfilQuestionPresenterImpl(ProfilQuestionView profilQuestionView) {
        this.profilQuestionView = profilQuestionView;
    }

    @Override
    public void loadData(int kullaniciId) {
        Call<List<QuestionModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kullaniciSorulariGetir(kullaniciId);

        call.enqueue(new Callback<List<QuestionModel>>() {
            @Override
            public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    profilQuestionView.onGetResult(response.body());
                }
                List<QuestionModel> data=response.body();
                if(data == null || data.isEmpty() ){
                    profilQuestionView.onGetResultControl();
                }
            }
            @Override
            public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
                profilQuestionView.onErrorLoading(t.getMessage());
            }
        });
    }
}
