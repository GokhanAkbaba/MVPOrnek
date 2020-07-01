package com.example.mvpornek.Model.InternetBaglantiKontrol;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractor;

public class InternetConnectionInteractorImpl implements InternetConnectionInteractor {
    Context context;

    public InternetConnectionInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean internetBaglan(onIntBaglantiFinishedListener listener) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null){
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected ())
            {
                listener.onBaglanti();
                return true;
            }
        }
        listener.onBaglantiHatasi();
        return false;
    }
}
