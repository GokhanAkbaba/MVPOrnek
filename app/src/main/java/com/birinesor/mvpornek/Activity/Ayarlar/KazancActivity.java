package com.birinesor.mvpornek.Activity.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.BirineSorHelper.BirineSorUtil;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.Model.KullaniciKazanc.KullaniciKazancInteractorImpl;
import com.birinesor.mvpornek.Models.KazancCevap;
import com.birinesor.mvpornek.Models.KazancSoru;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.KullaniciGetir;
import com.birinesor.mvpornek.Presenter.CevapKazancGuncellePresenterImpl;
import com.birinesor.mvpornek.Presenter.KazancCevap.KazancCevapPresenterImpl;
import com.birinesor.mvpornek.Presenter.KazancSoru.KazancSoruPresenterImpl;
import com.birinesor.mvpornek.Presenter.KazancSoruGuncellePresenter;
import com.birinesor.mvpornek.Presenter.KazancSoruGuncellePresenterImpl;
import com.birinesor.mvpornek.Presenter.KullaniciGetir.UsersGetPresenterImpl;
import com.birinesor.mvpornek.Presenter.KullaniciKazancLog.KullaniciKazancPresenter;
import com.birinesor.mvpornek.Presenter.KullaniciKazancLog.KullaniciKazancPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.CevapKazancGuncelle;
import com.birinesor.mvpornek.View.KazancCevapView;
import com.birinesor.mvpornek.View.KazancSoruGuncelleView;
import com.birinesor.mvpornek.View.KazancSoruView;
import com.birinesor.mvpornek.View.KullaniciKazancLogView;
import com.birinesor.mvpornek.View.UsersGetView;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class KazancActivity extends AppCompatActivity implements View.OnClickListener, KazancCevapView, KazancSoruView, KullaniciKazancLogView, CevapKazancGuncelle, KazancSoruGuncelleView {
    KazancCevapPresenterImpl kazancCevapPresenter;
    KazancSoruPresenterImpl kazancSoruPresenter;
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
    EditText ıbanText,kazancAdSoyadInputTxt;
    String tutar,iban,adSoyad;
    Button trasferEtBtn;
    ArrayList<Integer> cevapKazancList=new ArrayList<Integer>();
    ArrayList<Integer> soruKazancList=new ArrayList<Integer>();
    double soru, cevap,toplamUcret ;

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
        cevapKazancGuncellePresenter=new CevapKazancGuncellePresenterImpl(this);
        kazancSoruGuncellePresenter = new KazancSoruGuncellePresenterImpl(this);
        kullanici= SharedPrefManager.getInstance(this).getKullanici();
        //kazancCevapPresenter.loadData(kullanici.getId());
        //kazancSoruPresenter.loadData(kullanici.getId());
        kazancCevapPresenter.loadData(1034);
        kazancSoruPresenter.loadData(1034);
        toplamKazancText=findViewById(R.id.toplamKazanctextView);
        kazancAdSoyadTextField=findViewById(R.id.kazancAdSoyadTextField);
        kazancIbanTextField=findViewById(R.id.kazancIbanTextField);
        swipeRefreshLayout = findViewById(R.id.kazancSwipeLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);
        swipeRefreshLayout.setOnRefreshListener(() ->{
            //kazancCevapPresenter.loadData(kullanici.getId());
            //kazancSoruPresenter.loadData(kullanici.getId());
            kazancCevapPresenter.loadData(1034);
            kazancSoruPresenter.loadData(1034);
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
            Double cevapDataSize = Double.valueOf(cevapData.size());
            cevap=cevapDataSize * Double.valueOf(0.10);
        }
        if(data == null){
            soru=0;
        }else{
            Double dataSize=Double.valueOf(data.size());
            soru=dataSize * Double.valueOf(0.05);
        }
        this.soruData=data;
        DecimalFormat precision = new DecimalFormat("0.00");
        if(Double.parseDouble(String.valueOf(soru + cevap)) >= 0.0){
            trasferEtBtn.setEnabled(true);
        }else{
            trasferEtBtn.setEnabled(false);

        }
        System.out.println("Soru "+soru);
        System.out.println("Cevap "+cevap);
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
}