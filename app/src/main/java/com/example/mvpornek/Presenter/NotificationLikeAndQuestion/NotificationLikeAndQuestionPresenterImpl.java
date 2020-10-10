package com.example.mvpornek.Presenter.NotificationLikeAndQuestion;

import com.example.mvpornek.Models.NotificationCommetAndQuestionModel;
import com.example.mvpornek.Response.BildirimlerCevaplarModel;
import com.example.mvpornek.Response.NotificationCommentAndLikeModel;
import com.example.mvpornek.View.NotificationLikeAndQuestionView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class NotificationLikeAndQuestionPresenterImpl implements NotificationLikeAndQuestionPresenter {
    NotificationLikeAndQuestionView notificationLikeAndQuestionView;

    public NotificationLikeAndQuestionPresenterImpl(NotificationLikeAndQuestionView notificationLikeAndQuestionView) {
        this.notificationLikeAndQuestionView = notificationLikeAndQuestionView;
    }

    @Override
    public void loadDataNotificationLike(int soruID,int cevapId) {
        notificationLikeAndQuestionView.NotificationCommetAndLikeShowLoading();
        Call<List<NotificationCommentAndLikeModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .bildirimBegeniIcerikGetir(soruID,cevapId);

        call.enqueue(new Callback<List<NotificationCommentAndLikeModel>>() {
            @Override
            public void onResponse(Call<List<NotificationCommentAndLikeModel>> call, Response<List<NotificationCommentAndLikeModel>> response) {
                notificationLikeAndQuestionView.NotificationCommetAndLikeHideLoading();
                if(response.body().size() >0 ){
                    if (response.isSuccessful() && response.body() !=null) {
                        notificationLikeAndQuestionView.onNotificationCommetAndLikeGetResult(response.body());
                    }
                }else{
                    notificationLikeAndQuestionView.onGetNotificationCommetAndLikeKontrol();
                }
            }
            @Override
            public void onFailure(Call<List<NotificationCommentAndLikeModel>> call, Throwable t) {
                notificationLikeAndQuestionView.NotificationCommetAndLikeHideLoading();
                notificationLikeAndQuestionView.onNotificationCommetAndLikeErrorLoading("NotificationLikeAndQuestion "+t.getMessage());

            }
        });
    }
}
