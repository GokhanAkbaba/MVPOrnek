package com.example.mvpornek.Presenter.YorumKayit;

import com.example.mvpornek.Model.YorumKayit.CommentRegistrationInteractor;
import com.example.mvpornek.Presenter.YorumKayit.CommentRegistrationPresenter;
import com.example.mvpornek.View.CommentRegistrationView;


public class CommentRegistrationPresenterImpl implements CommentRegistrationPresenter,CommentRegistrationInteractor.onCommentRegistrationInteractor {
    private CommentRegistrationView commentRegistrationView;
    private CommentRegistrationInteractor commentRegistrationInteractor;

    public CommentRegistrationPresenterImpl(CommentRegistrationView commentRegistrationView, CommentRegistrationInteractor commentRegistrationInteractor) {
        this.commentRegistrationView = commentRegistrationView;
        this.commentRegistrationInteractor = commentRegistrationInteractor;
    }

    @Override
    public void commentRegistrationValideCredentals(int kullaniciId,int soruId, String cevap) {
        if(commentRegistrationView != null)
        {
            commentRegistrationView.showProgress();
        }
        commentRegistrationInteractor.CommentRegistration(kullaniciId,soruId,cevap,this );
    }

    @Override
    public void onSuccess() {
        if(commentRegistrationView != null)
        {
            commentRegistrationView.navigateToCommentRegistration();
        }
    }
}
