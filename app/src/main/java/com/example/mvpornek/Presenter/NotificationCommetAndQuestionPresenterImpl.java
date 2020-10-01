package com.example.mvpornek.Presenter;

import com.example.mvpornek.Models.NotificationCommetAndQuestionModel;
import com.example.mvpornek.Response.NotificationResponse;
import com.example.mvpornek.View.NotificationCommetAndQuestionView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationCommetAndQuestionPresenterImpl implements NotificationCommetAndQuestionPresenter {

    NotificationCommetAndQuestionView notificationCommetAndQuestionView;

    public NotificationCommetAndQuestionPresenterImpl(NotificationCommetAndQuestionView notificationCommetAndQuestionView) {
        this.notificationCommetAndQuestionView = notificationCommetAndQuestionView;
    }


    @Override
    public void loadDataNotificationComment(int soruId) {
        Call<List<NotificationCommetAndQuestionModel>>call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .bildirimYorumIcerikGetir(soruId);

        call.enqueue(new Callback<List<NotificationCommetAndQuestionModel>>() {
            @Override
            public void onResponse(Call<List<NotificationCommetAndQuestionModel>> call, Response<List<NotificationCommetAndQuestionModel>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    notificationCommetAndQuestionView.onGetResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<NotificationCommetAndQuestionModel>> call, Throwable t) {
                notificationCommetAndQuestionView.onErrorLoading("NotificationCommetAndQuestionModel "+t.getMessage());

            }
        });
    }
}
