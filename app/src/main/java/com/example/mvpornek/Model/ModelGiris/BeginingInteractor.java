package com.example.mvpornek.Model.ModelGiris;

public interface BeginingInteractor {
    interface onBeginingFinishedListener{
        void onIlHatasi();
        void onEtiketHatasi();
        void onSuccess();
    }
    void optionsSave(int id, int il,int secim,onBeginingFinishedListener listener);

}
