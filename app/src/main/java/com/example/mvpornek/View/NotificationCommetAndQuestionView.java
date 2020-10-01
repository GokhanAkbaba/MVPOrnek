package com.example.mvpornek.View;


import com.example.mvpornek.Models.NotificationCommetAndQuestionModel;

import java.util.List;

public interface NotificationCommetAndQuestionView {
    void onGetResult(List<NotificationCommetAndQuestionModel> data);
    void onErrorLoading(String message);
}
