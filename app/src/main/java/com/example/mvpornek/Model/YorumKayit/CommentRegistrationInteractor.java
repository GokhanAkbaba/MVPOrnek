package com.example.mvpornek.Model.YorumKayit;

import java.util.ArrayList;

public interface CommentRegistrationInteractor {
    interface onCommentRegistrationInteractor{
        void onSuccess();
    }
    void QuestionRegistration (int kullaniciId,int id, String soru, onCommentRegistrationInteractor listener);
}
