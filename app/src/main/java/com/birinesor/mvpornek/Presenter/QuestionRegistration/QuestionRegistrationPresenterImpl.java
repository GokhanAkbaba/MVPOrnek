package com.birinesor.mvpornek.Presenter.QuestionRegistration;

import com.birinesor.mvpornek.Model.SoruKayit.QuestionRegistrationInteractor;
import com.birinesor.mvpornek.View.QuestionRegistrationView;

import java.util.ArrayList;

public class QuestionRegistrationPresenterImpl implements QuestionRegistrationPresenter, QuestionRegistrationInteractor.onQuestionRegistrationListener {

    private QuestionRegistrationView questionRegistrationView;
    private QuestionRegistrationInteractor questionRegistrationInteractor;

    public QuestionRegistrationPresenterImpl(QuestionRegistrationView questionRegistrationView, QuestionRegistrationInteractor questionRegistrationInteractor) {
        this.questionRegistrationView = questionRegistrationView;
        this.questionRegistrationInteractor = questionRegistrationInteractor;
    }


    @Override
    public void questionRegistrationValideCredentals(int id, String soru, ArrayList<Integer> etiket,ArrayList<Integer> il) {
        if(questionRegistrationView != null)
        {
            questionRegistrationView.showProgress();
        }
        questionRegistrationInteractor.QuestionRegistration(id,soru,etiket,il,this);
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
