package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Response.NotificationCommentAndLikeModel;

import java.util.List;

public
interface NotificationLikeAndQuestionView {
    void onNotificationCommetAndLikeGetResult(List<NotificationCommentAndLikeModel> data);
    void onNotificationCommetAndLikeErrorLoading(String message);
    void onGetNotificationCommetAndLikeKontrol();
    void NotificationCommetAndLikeShowLoading();
    void NotificationCommetAndLikeHideLoading();
}
