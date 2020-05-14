package com.example.mvpornek.Model.ModelGiris;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;

public class QuestionRegistrationInteractorImpl implements QuestionRegistrationInteractor {
    Context context;

    public QuestionRegistrationInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void QuestionRegistration(int id, String soru, ArrayList<Integer> il, ArrayList<Integer> etiket, onQuestionRegistrationListener listener) {

        if(il.isEmpty()){
            listener.onIlSecimHatasi();
            return;
        }
        if(etiket.isEmpty()){
            listener.onEtiketSecimHatasi();
            return;
        }

        for(int i = 0; i < il.size(); i++)
        {
            System.out.println(soru+"  "+il.get(i)+" "+etiket.get(i)+" "+id);
        }
    }
}
