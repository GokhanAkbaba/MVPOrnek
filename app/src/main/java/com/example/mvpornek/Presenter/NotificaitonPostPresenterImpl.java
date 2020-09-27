package com.example.mvpornek.Presenter;

import com.example.mvpornek.Response.NotificationResponse;
import com.example.mvpornek.Response.TokenOlusturResponse;
import com.example.mvpornek.View.NotificaitonPostView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class NotificaitonPostPresenterImpl implements NotificaitonPostPresenter {
    NotificaitonPostView notificaitonPostView;

    public NotificaitonPostPresenterImpl(NotificaitonPostView notificaitonPostView) {
        this.notificaitonPostView = notificaitonPostView;
    }

    @Override
    public void postNotification(String kullaniciAdi, int hedefID, String token) {
        Call<NotificationResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .bildirimGonder(hedefID,kullaniciAdi,token);

        call.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    notificaitonPostView.showNotificaitonSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                notificaitonPostView.showNotificaitonFailedMessage();

            }
        });
    }
}
