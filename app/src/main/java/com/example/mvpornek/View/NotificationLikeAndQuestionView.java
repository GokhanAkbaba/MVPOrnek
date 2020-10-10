package com.example.mvpornek.View;

import com.example.mvpornek.Models.NotificationCommetAndQuestionModel;
import com.example.mvpornek.Response.NotificationCommentAndLikeModel;

import java.util.List;

public
interface NotificationLikeAndQuestionView {
    void onNotificationCommetAndLikeGetResult(List<NotificationCommentAndLikeModel> data);
    void onNotificationCommetAndLikeErrorLoading(String message);
    void onGetNotificationCommetAndLikeKontrol();
    void NotificationCommetAndLikeShowLoading();
    void NotificationCommetAndLikeHideLoading();
}
