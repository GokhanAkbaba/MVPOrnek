package com.example.mvpornek.Presenter;

import com.example.mvpornek.Model.ModelGiris.QuestionRegistrationInteractor;
import com.example.mvpornek.View.QuestionRegistrationView;

import java.util.ArrayList;

public class QuestionRegistrationPresenterImpl implements QuestionRegistrationPresenter, QuestionRegistrationInteractor.onQuestionRegistrationListener {

    private QuestionRegistrationView questionRegistrationView;
    private QuestionRegistrationInteractor questionRegistrationInteractor;

    public QuestionRegistrationPresenterImpl(QuestionRegistrationView questionRegistrationView, QuestionRegistrationInteractor questionRegistrationInteractor) {
        this.questionRegistrationView = questionRegistrationView;
        this.questionRegistrationInteractor = questionRegistrationInteractor;
    }



    @Override
    public void questionRegistrationValideCredentals(int id, String soru, ArrayList<Integer> il, ArrayList<Integer> etiket) {
        if(questionRegistrationView != null)
        {
            questionRegistrationView.showProgress();
        }
        questionRegistrationInteractor.QuestionRegistration(id,soru,il,etiket,this);
    }

    @Override
    public void onDestroy() {
        questionRegistrationView = null;
    }

    @Override
    public void onIlSecimHatasi() {
        if(questionRegistrationView != null)
        {
            questionRegistrationView.setIlSecimHatasi();
            questionRegistrationView.hideProgress();
        }
    }

    @Override
    public void onEtiketSecimHatasi() {
        if(questionRegistrationView != null)
        {
            questionRegistrationView.setEtiketSecimHatasi();
            questionRegistrationView.hideProgress();
        }

    }

    @Override
    public void onSoruAlaniHatasi() {
        if(questionRegistrationView != null)
        {
            questionRegistrationView.setSoruAlaniHatasi();
            questionRegistrationView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(questionRegistrationView != null)
        {
            questionRegistrationView.navigateToQuestionRegistration();
            questionRegistrationView.hideProgress();
        }
    }
}
