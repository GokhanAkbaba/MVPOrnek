package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.SearchQuestionModel;

import java.util.List;

public interface SearchQuestionView {
    void onGetResult(List<SearchQuestionModel> data);
    void onErrorLoading(String message);
    void showLoading();
    void hideLoading();
    void onGetResultControl();
}
