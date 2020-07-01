package com.example.mvpornek.View;

import com.example.mvpornek.Models.QuestionModel;

import java.util.List;

public interface QuestionMenuView {
    void onGetResult(List<QuestionModel> data);
    void onErrorLoading(String message);
    void showLoading();
    void hideLoading();
    void onGetResultControl();
}
