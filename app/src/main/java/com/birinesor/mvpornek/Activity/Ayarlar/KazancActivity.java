package com.birinesor.mvpornek.Activity.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.Models.KazancCevap;
import com.birinesor.mvpornek.Models.KazancSoru;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.KullaniciGetir;
import com.birinesor.mvpornek.Presenter.KazancCevap.KazancCevapPresenterImpl;
import com.birinesor.mvpornek.Presenter.KazancSoru.KazancSoruPresenterImpl;
import com.birinesor.mvpornek.Presenter.KullaniciGetir.UsersGetPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.KazancCevapView;
import com.birinesor.mvpornek.View.KazancSoruView;
import com.birinesor.mvpornek.View.UsersGetView;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.List;

public class KazancActivity extends AppCompatActivity implements View.OnClickListener, KazancCevapView, KazancSoruView {
    KazancCevapPresenterImpl kazancCevapPresenter;
    KazancSoruPresenterImpl kazancSoruPresenter;
    Kullanici kullanici;
    TextView toplamKazancText;
    List<KazancCevap> cevapData;
    double soru, cevap;
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
        kazancCevapPresenter=new KazancCevapPresenterImpl(this);
        kazancSoruPresenter=new KazancSoruPresenterImpl(this);
        kullanici= SharedPrefManager.getInstance(this).getKullanici();
        kazancCevapPresenter.loadData(kullanici.getId());
        kazancSoruPresenter.loadData(kullanici.getId());
        toplamKazancText=findViewById(R.id.toplamKazanctextView);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kazancGeriBtn:
                onBackPressed();
                break;
        }
    }
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
        if(cevapData == null){
            cevap=0;
        }else{
            cevap=cevapData.size() * 0.15;
        }
        if(data == null){
            cevap=0;
        }else{
            soru=data.size() * 0.10;
        }


        toplamKazancText.setText(String.valueOf(soru + cevap));
    }

    @Override
    public void onErrorKazancSoruLoading(String message) {
        System.out.println("Kazanc Soru Idleri Getirilirken Hata Oluştu");
    }

    @Override
    public void onErrorLoading(String message) {
        System.out.println("Kazanc Cevap Idleri Getirilirken Hata Oluştu");
    }
}