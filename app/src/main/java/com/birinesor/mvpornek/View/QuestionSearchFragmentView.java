package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.SearchQuestionModel;

import java.util.List;

public interface QuestionSearchFragmentView {
    void onGetResult(List<SearchQuestionModel> data);
    void onErrorLoading(String message);
    void showLoading();
    void hideLoading();
    void onGetResultControl();
}
