package com.birinesor.mvpornek.BirineSorHelper;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tapadoo.alerter.Alerter;

public class BirineSorUtil {

    /*Singleton BirineSorUtil*/
    private static BirineSorUtil birineSorUtil;

    public BirineSorUtil(){}

    public static BirineSorUtil getInstanceBirineSorUtil(){
        if (birineSorUtil == null)
            birineSorUtil = new BirineSorUtil();

        return birineSorUtil;
    }
    /*End Singleton BirineSorUtil*/


    /*Alert */

    public void UyariMesajiVer(Activity activity, @Nullable String baslik,@Nullable String icerik){

        if (baslik==null || baslik.equals(""))
            baslik = "Uyarı!";

        if (icerik==null || icerik.equals(""))
            baslik = "";

        Alerter.create(activity)
                .setTitle(baslik)
                .setText(icerik)
                .setDuration(2000)
                .show();
    }



    /*End Alert */

    /*Loader*/
    private KProgressHUD kProgressHUD;
    public void yükleniyorBaslat(Activity activity,@Nullable String baslik,@Nullable String icerik){

        if (baslik==null || baslik.equals(""))
            baslik = "Lütfen Bekleyin";

        if (icerik==null || icerik.equals(""))
            icerik = "Yükleniyor";


        kProgressHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(baslik)
                .setDetailsLabel(icerik)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    public void yükleniyorBitir(){
        if (kProgressHUD!=null)
            kProgressHUD.dismiss();
    }

    /*End Loader*/

}
