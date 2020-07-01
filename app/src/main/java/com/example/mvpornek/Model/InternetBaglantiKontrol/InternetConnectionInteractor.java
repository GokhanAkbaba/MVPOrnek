package com.example.mvpornek.Model.InternetBaglantiKontrol;

public interface InternetConnectionInteractor {
    interface onIntBaglantiFinishedListener{
        void onBaglantiHatasi();
        void onBaglanti();
    }

    boolean internetBaglan(onIntBaglantiFinishedListener listener);
}
