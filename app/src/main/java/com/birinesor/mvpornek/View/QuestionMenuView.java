package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.QuestionModel;

import java.util.List;

public interface QuestionMenuView {
    void onGetResult(List<QuestionModel> data);
    void onErrorLoading(String message);
    void showLoading();
    void hideLoading();
    void onGetResultControl();
}
