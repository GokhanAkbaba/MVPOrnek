package com.example.mvpornek.Model.ModelGiris;

import java.util.ArrayList;

public interface CommentRegistrationInteractor {
    interface onCommentRegistrationInteractor{
        void onSuccess();
    }
    void QuestionRegistration (int kullaniciId,int id, String soru, onCommentRegistrationInteractor listener);
}
