package com.example.mvpornek.View;

import com.example.mvpornek.Models.SearchQuestionModel;

import java.util.List;

public interface QuestionSearchFragmentView {
    void onGetResult(List<SearchQuestionModel> data);
    void onErrorLoading(String message);
    void showLoading();
    void hideLoading();
    void onGetResultControl();
}
