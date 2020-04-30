package com.example.mvpornek.Model.ModelGiris;

public interface InternetConnectionInteractor {
    interface onIntBaglantiFinishedListener{
        void onBaglantiHatasi();
        void onBaglanti();
    }

    boolean internetBaglan(onIntBaglantiFinishedListener listener);
}
