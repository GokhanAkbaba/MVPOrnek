package com.example.mvpornek.Presenter;

import com.example.mvpornek.Model.ModelGiris.CommentRegistrationInteractor;
import com.example.mvpornek.View.CommentRegistrationView;


public class CommentRegistrationPresenterImpl implements CommentRegistrationPresenter,CommentRegistrationInteractor.onCommentRegistrationInteractor {
    private CommentRegistrationView commentRegistrationView;
    private CommentRegistrationInteractor commentRegistrationInteractor;

    public CommentRegistrationPresenterImpl(CommentRegistrationView commentRegistrationView, CommentRegistrationInteractor commentRegistrationInteractor) {
        this.commentRegistrationView = commentRegistrationView;
        this.commentRegistrationInteractor = commentRegistrationInteractor;
    }

    @Override
    public void commentRegistrationValideCredentals(int kullaniciId,int id, String soru) {
        if(commentRegistrationView != null)
        {
            commentRegistrationView.showProgress();
        }
        commentRegistrationInteractor.QuestionRegistration(id,kullaniciId,soru,this );
    }

    @Override
    public void onSuccess() {
        if(commentRegistrationView != null)
        {
            commentRegistrationView.navigateToCommentRegistration();
        }
    }
}
