package com.birinesor.mvpornek.Model.SoruKayit;

import java.util.ArrayList;

public interface QuestionRegistrationInteractor {
    interface onQuestionRegistrationListener{
        void onIlSecimHatasi();
        void onEtiketSecimHatasi();
        void onSoruAlaniHatasi();
        void onSuccess();
    }
     void QuestionRegistration (int id, String soru, ArrayList<Integer> il, ArrayList<Integer> etiket,onQuestionRegistrationListener listener);

}
