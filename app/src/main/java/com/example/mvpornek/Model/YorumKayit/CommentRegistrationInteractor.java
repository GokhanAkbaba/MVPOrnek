package com.example.mvpornek.Model.YorumKayit;

import java.util.ArrayList;

public interface CommentRegistrationInteractor {
    interface onCommentRegistrationInteractor{
        void onSuccess();
    }
    void CommentRegistration (int kullaniciId,int soruId, String cevap, onCommentRegistrationInteractor listener);
}
