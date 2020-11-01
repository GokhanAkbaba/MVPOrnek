package com.birinesor.mvpornek.Presenter;

import com.birinesor.mvpornek.Response.SoruSilResponse;
import com.birinesor.mvpornek.View.QuestionsDeleteView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsDeletePresenterImpl implements QuestionsDeletePresenter{
    QuestionsDeleteView questionsDeleteView;

    public QuestionsDeletePresenterImpl(QuestionsDeleteView questionsDeleteView) {
        this.questionsDeleteView = questionsDeleteView;
    }

    @Override
    public void deleteOptions(int soruId) {
        Call<SoruSilResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .soruSil(soruId);

        call.enqueue(new Callback<SoruSilResponse>() {
            @Override
            public void onResponse(Call<SoruSilResponse> call, Response<SoruSilResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    questionsDeleteView.showSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<SoruSilResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                questionsDeleteView.showFailedMessage();

            }
        });
    }
}
