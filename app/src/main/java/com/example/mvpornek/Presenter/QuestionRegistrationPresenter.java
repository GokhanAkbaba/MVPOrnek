package com.example.mvpornek.Presenter;

import java.util.ArrayList;

public interface QuestionRegistrationPresenter {
    void  questionRegistrationValideCredentals(int id,String soru, ArrayList<Integer> il,ArrayList<Integer> etiket);
    void  onDestroy();
}
