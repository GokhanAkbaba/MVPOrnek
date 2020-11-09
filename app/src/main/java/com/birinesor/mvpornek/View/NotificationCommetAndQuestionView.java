package com.birinesor.mvpornek.View;


import com.birinesor.mvpornek.Models.NotificationCommetAndQuestionModel;

import java.util.List;

public interface NotificationCommetAndQuestionView {
    void onGetResult(List<NotificationCommetAndQuestionModel> data);
    void onErrorLoading(String message);
    void onGetNotificationCommetAndQuestionKontrol();
}
