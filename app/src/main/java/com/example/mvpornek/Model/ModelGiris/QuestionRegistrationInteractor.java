package com.example.mvpornek.Model.ModelGiris;

import java.util.ArrayList;
import java.util.List;

public interface QuestionRegistrationInteractor {
    interface onQuestionRegistrationListener{
        void onIlSecimHatasi();
        void onEtiketSecimHatasi();
        void onSoruAlaniHatasi();
        void onSuccess();
    }
     void QuestionRegistration (int id, String soru, ArrayList<Integer> il, ArrayList<Integer> etiket,onQuestionRegistrationListener listener);

}
