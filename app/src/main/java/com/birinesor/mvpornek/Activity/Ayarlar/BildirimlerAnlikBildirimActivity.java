package com.birinesor.mvpornek.Activity.Ayarlar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.birinesor.mvpornek.Degiskenler;
import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.Models.IlgiAlanlariGetirModel;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Presenter.IlgiAlaniKayit.IlgiAlaniKayitPresenterImpl;
import com.birinesor.mvpornek.Presenter.IlgiAlanlari.IlgiAlanlariPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.IlgiAlanlariGetirView;
import com.birinesor.mvpornek.View.IlgiAlanıKayitView;

import java.util.List;

public class BildirimlerAnlikBildirimActivity extends AppCompatActivity implements View.OnClickListener, IlgiAlanlariGetirView, IlgiAlanıKayitView {
    List<IlgiAlanlariGetirModel> data;
    IlgiAlanlariPresenterImpl ilgiAlanlariPresenter;
    IlgiAlaniKayitPresenterImpl ilgiAlaniKayitPresenter;
    int secimSayac = 0;
    Switch switchAdres,switchDiger,switchYemek,switchSpor,switchTatil,switchAlisveris,switchSanat,switchYazilim,switchOto,switchModa,switchTarih,switchEgitim,switchMuzik,switchSaglik,switchOyun,switchTekno,switchKripto;
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
        setContentView(R.layout.activity_bildirimler_anlik_bildirim);
        Button bildirimIlgiAlaniGeriBtn=findViewById(R.id.bildirimIlgiAlaniGeriBtn);
        switchAdres=findViewById(R.id.switchAdres);
        switchYemek=findViewById(R.id.switchYemek);
        switchDiger=findViewById(R.id.switchDiger);
        switchTatil=findViewById(R.id.switchTatil);
        switchSpor=findViewById(R.id.switchSpor);
        switchOto=findViewById(R.id.switchOto);
        switchYazilim=findViewById(R.id.switchYazilim);
        switchSanat=findViewById(R.id.switchSanat);
        switchAlisveris=findViewById(R.id.switchAlisveris);
        switchModa=findViewById(R.id.switchModa);
        switchTarih=findViewById(R.id.switchTarih);
        switchEgitim=findViewById(R.id.switchEgitim);
        switchMuzik=findViewById(R.id.switchMuzik);
        switchSaglik=findViewById(R.id.switchSaglik);
        switchOyun=findViewById(R.id.switchOyun);
        switchTekno=findViewById(R.id.switchTekno);
        switchKripto=findViewById(R.id.switchKripto);
        bildirimIlgiAlaniGeriBtn.setOnClickListener(this);
        Kullanici kullanici= SharedPrefManager.getInstance(this).getKullanici();
        ilgiAlanlariPresenter=new IlgiAlanlariPresenterImpl(this);
        ilgiAlanlariPresenter.ilgiAlanlariGetir(kullanici.getId());
        ilgiAlaniKayitPresenter=new IlgiAlaniKayitPresenterImpl(this);


        switchMuzik.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.MUZIK_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.MUZIK_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchMuzik.setChecked(true);
                }
            }
        });

        switchSaglik.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.SAGLIK_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.SAGLIK_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchSaglik.setChecked(true);
                }
            }
        });
        switchAdres.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.ADRES_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.ADRES_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchAdres.setChecked(true);
                }
            }
        });
        switchYemek.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.YEMEK_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.YEMEK_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchYemek.setChecked(true);
                }
            }
        });
        switchSpor.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.SPOR_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.SPOR_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchSpor.setChecked(true);
                }
            }
        });
        switchTatil.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.TATIL_ID,data.get(0).getIl(),1);
            } else {
                if( 1 < secimSayac ){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.TATIL_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchTatil.setChecked(true);
                }
            }
        });
        switchAlisveris.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.ALISVERIS_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.ALISVERIS_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchAlisveris.setChecked(true);
                }
            }
        });
        switchSanat.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.SANAT_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(), Degiskenler.SANAT_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchSanat.setChecked(true);
                }
            }
        });
        switchYazilim.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.YAZILIM_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(), Degiskenler.YAZILIM_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchSanat.setChecked(true);
                }
            }
        });
        switchOto.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.OTO_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(), Degiskenler.OTO_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchOto.setChecked(true);
                }
            }
        });
        switchModa.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.MODA_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(), Degiskenler.MODA_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchModa.setChecked(true);
                }
            }
        });
        switchTarih.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.TARIH_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(), Degiskenler.TARIH_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchTarih.setChecked(true);
                }
            }
        });
        switchEgitim.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.EGITIM_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(), Degiskenler.EGITIM_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchEgitim.setChecked(true);
                }
            }
        });
        switchTekno.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.TEKNOLOJI_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(), Degiskenler.TEKNOLOJI_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchTekno.setChecked(true);
                }
            }
        });
        switchOyun.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.OYUN_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(), Degiskenler.OYUN_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchOyun.setChecked(true);
                }
            }

        });
        switchKripto.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.KRIPTO_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.KRIPTO_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchKripto.setChecked(true);
                }
            }
        });
        switchDiger.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                secimSayac++;
                ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.DIGER_ID,data.get(0).getIl(),1);
            } else {
                if(1 < secimSayac){
                    ilgiAlaniKayitPresenter.validateIlgiAlaniKayit(kullanici.getId(),Degiskenler.DIGER_ID,data.get(0).getIl(),2);
                    secimSayac--;
                }else{
                    Toast.makeText(this,"En Az 1 Tane Alan Seçilmelidir",Toast.LENGTH_SHORT).show();
                    switchKripto.setChecked(true);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    public void onGetResultIlgiAlanlari(List<IlgiAlanlariGetirModel> data) {
        this.data=data;
        for (int i=0;i<data.size();i++){
            if(data.get(i).getEtiketID()==1){
                switchAdres.setChecked(true);

            }else if(data.get(i).getEtiketID()==2){
                switchYemek.setChecked(true);

            }else if(data.get(i).getEtiketID()==3){
                switchSpor.setChecked(true);

            }else if(data.get(i).getEtiketID()==4){
                switchTatil.setChecked(true);

            }else if(data.get(i).getEtiketID()==5){
                switchAlisveris.setChecked(true);

            }else if(data.get(i).getEtiketID()==6){
                switchSanat.setChecked(true);

            }else if(data.get(i).getEtiketID()==7){
                switchYazilim.setChecked(true);

            }else if(data.get(i).getEtiketID()==17){
                switchOto.setChecked(true);

            }else if(data.get(i).getEtiketID()==16){
                switchModa.setChecked(true);

            }else if(data.get(i).getEtiketID()==15){
                switchTarih.setChecked(true);

            }else if(data.get(i).getEtiketID()==14){
                switchEgitim.setChecked(true);

            }else if(data.get(i).getEtiketID()==10){
                switchTekno.setChecked(true);

            }else if(data.get(i).getEtiketID()==11){
                switchOyun.setChecked(true);

            }else if(data.get(i).getEtiketID()==12){
                switchSaglik.setChecked(true);
            }else if(data.get(i).getEtiketID()==13){
                switchMuzik.setChecked(true);
            }else if(data.get(i).getEtiketID()==18){
                switchKripto.setChecked(true);
            }else if(data.get(i).getEtiketID()==19){
                switchDiger.setChecked(true);
            }

        }

    }

    @Override
    public void onErrorLoadingIlgiAlanlari(String message) {
    System.out.println("BildirimlerAnlikBildirimActivity HATA MESAJI"+message);
    }

    @Override
    public void succesIlgiAlanıKayit() {
       //
    }

    @Override
    public void failedIlgiAlanıKayit() {
        //
    }
}