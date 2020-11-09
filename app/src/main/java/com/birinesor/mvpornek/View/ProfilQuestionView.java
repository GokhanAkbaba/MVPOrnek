package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.QuestionModel;

import java.util.List;

public interface ProfilQuestionView {
    void onGetResult(List<QuestionModel> data);
    void onErrorLoading(String message);
    void onGetResultControl();
    void onProfilQuestionShow();
    void onProfilQuestionHide();
}
