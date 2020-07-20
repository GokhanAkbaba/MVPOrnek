package com.example.mvpornek.View;

import com.example.mvpornek.Models.SearchQuestionModel;

import java.util.List;

public interface SearchQuestionView {
    void onGetResult(List<SearchQuestionModel> data);
    void onErrorLoading(String message);
    void showLoading();
    void hideLoading();
    void onGetResultControl();
}
