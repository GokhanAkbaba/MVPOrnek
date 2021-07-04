package com.birinesor.mvpornek.Activity.Ayarlar;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.BirineSorHelper.BirineSorUtil;
import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.Model.KullaniciKazanc.KullaniciKazancInteractorImpl;
import com.birinesor.mvpornek.Models.KazancCevap;
import com.birinesor.mvpornek.Models.KazancHesaplaModels;
import com.birinesor.mvpornek.Models.KazancSoru;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Presenter.CevapKazancGuncellePresenterImpl;
import com.birinesor.mvpornek.Presenter.KazancCevap.KazancCevapPresenterImpl;
import com.birinesor.mvpornek.Presenter.KazancSoru.KazancSoruPresenterImpl;
import com.birinesor.mvpornek.Presenter.KazancSoruGuncellePresenterImpl;
import com.birinesor.mvpornek.Presenter.KullaniciKazanHesapla.KullaniciKazancHesaplaPresenterImpl;
import com.birinesor.mvpornek.Presenter.KullaniciKazancLog.KullaniciKazancPresenter;
import com.birinesor.mvpornek.Presenter.KullaniciKazancLog.KullaniciKazancPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.CevapKazancGuncelle;
import com.birinesor.mvpornek.View.KazancCevapView;
import com.birinesor.mvpornek.View.KazancSoruGuncelleView;
import com.birinesor.mvpornek.View.KazancSoruView;
import com.birinesor.mvpornek.View.KullaniciKazancHesapla;
import com.birinesor.mvpornek.View.KullaniciKazancLogView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class KazancActivity extends AppCompatActivity implements View.OnClickListener, KullaniciKazancHesapla, KazancCevapView, KazancSoruView, KullaniciKazancLogView, CevapKazancGuncelle, KazancSoruGuncelleView {
    KazancCevapPresenterImpl kazancCevapPresenter;
    KazancSoruPresenterImpl kazancSoruPresenter;
    KullaniciKazancHesaplaPresenterImpl kullaniciKazancHesaplaPresenter;
    CevapKazancGuncellePresenterImpl cevapKazancGuncellePresenter;
    KazancSoruGuncellePresenterImpl kazancSoruGuncellePresenter;
    KullaniciKazancPresenter kullaniciKazancPresenter;
    SwipeRefreshLayout swipeRefreshLayout;
    Kullanici kullanici;
    TextView toplamKazancText;
    TextInputLayout kazancAdSoyadTextField;
    TextInputLayout kazancIbanTextField;
    List<KazancCevap> cevapData;
    List<KazancSoru> soruData;
    List<KazancHesaplaModels> kazancHesaplaModels;
    EditText ıbanText,kazancAdSoyadInputTxt;
    String tutar,iban,adSoyad;
    Button trasferEtBtn;
    ArrayList<Integer> cevapKazancList=new ArrayList<Integer>();
    ArrayList<Integer> soruKazancList=new ArrayList<Integer>();
    double soru, cevap,toplamUcret ;
    private static final String AD_UNIT_ID = "ca-app-pub-5898900112999132/5538403889";
    private FrameLayout adContainerView;
    private AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance(this).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
            if (Build.VERSION.SDK_INT >= 27) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            }
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getWindow().setStatusBarColor(getResources().getColor(R.color.whiteStatus));
            if (Build.VERSION.SDK_INT >= 27) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.whiteStatus));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        setContentView(R.layout.activity_kazanc);
        Button kazancGeriButon=findViewById(R.id.kazancGeriBtn);
        kazancGeriButon.setOnClickListener(this);


        kullanici= SharedPrefManager.getInstance(this).getKullanici();
        kullaniciKazancHesaplaPresenter=new KullaniciKazancHesaplaPresenterImpl(this);
        cevapKazancGuncellePresenter=new CevapKazancGuncellePresenterImpl(this);
        kazancSoruGuncellePresenter = new KazancSoruGuncellePresenterImpl(this);
        kazancCevapPresenter=new KazancCevapPresenterImpl(this);
        kazancSoruPresenter=new KazancSoruPresenterImpl(this);
        kullaniciKazancHesaplaPresenter.loadKazancData(kullanici.getId());
        kazancCevapPresenter.loadData(kullanici.getId());
        kazancSoruPresenter.loadData(kullanici.getId());
        toplamKazancText=findViewById(R.id.toplamKazanctextView);
        kazancAdSoyadTextField=findViewById(R.id.kazancAdSoyadTextField);
        kazancIbanTextField=findViewById(R.id.kazancIbanTextField);
        swipeRefreshLayout = findViewById(R.id.kazancSwipeLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);
        swipeRefreshLayout.setOnRefreshListener(() ->{
            kullaniciKazancHesaplaPresenter.loadKazancData(kullanici.getId());
        });
        kullaniciKazancPresenter = new KullaniciKazancPresenterImpl(this,new KullaniciKazancInteractorImpl(this));

        ıbanText=findViewById(R.id.kazancIbanInputTxt);
        kazancAdSoyadInputTxt=findViewById(R.id.kazancAdSoyadInputTxt);
        trasferEtBtn = findViewById(R.id.kazancTransferEtButon);
        trasferEtBtn.setOnClickListener(this);
        ıbanText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println(s);
            }
        });
        HomeActivity.getInstance().startAds();
        // Initialize the Mobile Ads SDK.


        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("TAG", "onAdLoaded.");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.d("TAG", "adError" + adError);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.d("TAG", "onAdOpened.");
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.d("TAG", "onAdClicked.");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.d("TAG", "onAdClosed.");
            }
        });
    }
    /*
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }


    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
    }

    @Override
    public void onGetResult(List<KazancCevap> data) {
        this.cevapData=data;
    }

    @Override
    public void onGetKazancSoruResult(List<KazancSoru> data) {
        this.soruData=data;
    }

    @SuppressLint("SetTextI18n")
    public void kazancHesapla(){
        double cevapDataSize = (double) this.kazancHesaplaModels.get(0).getCevapSayisi();
        cevap=cevapDataSize * 0.08;

        double dataSize= (double) this.kazancHesaplaModels.get(0).getSoruSayisi();
        soru=dataSize * 0.05;

        DecimalFormat precision = new DecimalFormat("0.00");
        trasferEtBtn.setEnabled(Double.parseDouble(String.valueOf(soru + cevap)) >= 20.0);
        toplamUcret=(soru + cevap);
        toplamKazancText.setText(precision.format(toplamUcret)+ " TL");
    }
    @Override
    public void onErrorKazancSoruLoading(String message) {
        System.out.println("Kazanc Soru Idleri Getirilirken Hata Oluştu");
    }

    @Override
    public void onErrorLoading(String message) {
        System.out.println("Kazanc Cevap Idleri Getirilirken Hata Oluştu");
    }

    @Override
    public void onKazancCevapShowLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onKazancCevapHideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showKullaniciKazancProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(this,null,"Ödeme Talebi Oluşturuluyor");

    }

    @Override
    public void hideKullaniciKazancProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBitir();

    }

    @Override
    public void setIbanHatasi(String error) {
        kazancIbanTextField.setError(error);
    }

    @Override
    public void setIbanOnay() {
        kazancIbanTextField.setError(null);
    }

    @Override
    public void setAdSoyadHatasi(String error) {
        kazancAdSoyadTextField.setError(error);
    }

    @Override
    public void setAdSoyadOnay() {
        kazancAdSoyadTextField.setError(null);
    }

    @Override
    public void kullaniciKazancToHome() {
        setAdSoyadOnay();
        setIbanOnay();
        Toast.makeText(this,"Ödeme Talebiniz Başarılı Bir Şekilde Oluşturuldu.",Toast.LENGTH_LONG).show();
        toplamKazancText.setText("");
        hideKullaniciKazancProgress();
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        tutar = toplamKazancText.getText().toString();
        iban = ıbanText.getText().toString();
        adSoyad = kazancAdSoyadInputTxt.getText().toString();

        switch (v.getId()){
            case R.id.kazancGeriBtn:
                onBackPressed();
                break;
            case R.id.kazancTransferEtButon:
                kullaniciKazancPresenter.validateKullaniciKazanc(kullanici.getId(),tutar,iban,adSoyad);
                for(int i=0; i< cevapData.size();i++){
                    cevapKazancList.add(cevapData.get(i).getCevapId());
                    cevapKazancGuncellePresenter.cevapKazancGuncelle(cevapKazancList);
                }
                cevapData.clear();
                for(int j=0; j<soruData.size();j++){
                    soruKazancList.add(soruData.get(j).getSorId());
                    kazancSoruGuncellePresenter.soruKazancGuncelle(soruKazancList);
                }
                trasferEtBtn.setEnabled(false);
        }

    }

    @Override
    public void showCevapKazancGuncelleSuccesMessage() {
        System.out.println("CevapKazancGuncelle Başarılı");
    }

    @Override
    public void showCevapKazancGuncelleFailedMessage() {

        Toast.makeText(this,"Bir Sorun Oluştu",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showKazancSoruGuncelleSuccesMessage() {
        System.out.println("KazancSoruGuncelle Başarılı");
    }

    @Override
    public void showKazancSoruGuncelleFailedMessage() {
        Toast.makeText(this,"Bir Sorun Oluştu",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onKullaniciKazancHesaplaGetResult(List<KazancHesaplaModels> data) {
        this.kazancHesaplaModels=data;
        kazancHesapla();
    }

    @Override
    public void onKullaniciKazancHesaplaErrorLoading(String message) {
        System.out.println("Kazanc Hesaplarken Bir Sorun Oluştu"+message);
    }

    @Override
    public void onKullaniciKazancHesaplaShowLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onKullaniciKazancHesaplaHideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }
}