package com.birinesor.mvpornek.Model.YorumKayit;

public interface CommentRegistrationInteractor {
    interface onCommentRegistrationInteractor{
        void onSuccess();
    }
    void CommentRegistration (int kullaniciId,int soruId, String cevap, onCommentRegistrationInteractor listener);
}
