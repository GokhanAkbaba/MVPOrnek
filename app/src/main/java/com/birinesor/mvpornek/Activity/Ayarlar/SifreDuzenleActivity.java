package com.birinesor.mvpornek.Activity.Ayarlar;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.birinesor.mvpornek.BirineSorHelper.BirineSorUtil;
import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Model.SifreGuncelle.PasswordUpdateInteractorImpl;
import com.birinesor.mvpornek.Presenter.PasswordUpdate.PasswordUpdatePresenter;
import com.birinesor.mvpornek.Presenter.PasswordUpdate.PasswordUpdatePresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.PasswordUpdate;
import com.google.android.material.textfield.TextInputLayout;

public class SifreDuzenleActivity extends AppCompatActivity implements PasswordUpdate, View.OnClickListener {
    TextInputLayout ayarlarMevcutSifreInputLayout,ayarlarYeniSifreInputLayout,ayarlarYeniSifreTekrarInputLayout;
    Button sifreGuncelle,sifreGuncelleGeri;
    String mevcutSifreTxt,yeniSifreTxt,yeniTekrarSifreTxt;
    EditText ayarlarMevcutSifreTxt,ayarlarYeniSifreTxt,ayarlarYeniSifreTekrarTxt;
    Kullanici kullanici;
    private PasswordUpdatePresenter passwordUpdatePresenter;
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
        setContentView(R.layout.ayarlar_sifre_duzenle);
        ayarlarMevcutSifreInputLayout=findViewById(R.id.ayarlarMevcutSifreTextField);
        ayarlarYeniSifreInputLayout=findViewById(R.id.ayarlarYeniSifreTextField);
        ayarlarYeniSifreTekrarInputLayout=findViewById(R.id.ayarlarYeniSifreTekrarTextField);

        ayarlarMevcutSifreTxt=findViewById(R.id.ayarlarMevcutSifreTxt);
        ayarlarYeniSifreTxt=findViewById(R.id.ayarlarYeniSifreTxt);
        ayarlarYeniSifreTekrarTxt=findViewById(R.id.ayarlarYeniSifreTekrarTxt);

        sifreGuncelle=findViewById(R.id.sifreGuncelleButon);
        sifreGuncelle.setOnClickListener(this);

        sifreGuncelleGeri=findViewById(R.id.sifreGuncelleGeriBtn);
        sifreGuncelleGeri.setOnClickListener(this);

        passwordUpdatePresenter=new PasswordUpdatePresenterImpl(this,new PasswordUpdateInteractorImpl(SifreDuzenleActivity.this));
    }

    @Override
    public void showProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(this,null,"Şifre Güncelleniyor");
    }

    @Override
    public void hideProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBitir();
    }

    @Override
    public void setGuncelleMevcutSifreHatasi(String hata) {
        ayarlarMevcutSifreInputLayout.setError(hata);
    }

    @Override
    public void setGuncelleYeniSifreHatasi() {
        ayarlarYeniSifreInputLayout.setError("Yeni Şifre Boş Bırakmayınız");
    }

    @Override
    public void setGuncelleYeniTekrarSifreHatasi() {
        ayarlarYeniSifreTekrarInputLayout.setError("Yeni Tekrar Sifre Boş Bırakmayınız");
    }

    @Override
    public void setGuncelleYeniSifreKontrolHatasi() {
        ayarlarYeniSifreTekrarInputLayout.setError("Şifreler Eşleşmiyor");
    }

    @Override
    public void navigateToPasswordUpdate() {
        ayarlarYeniSifreTekrarInputLayout.setError(null);
        ayarlarYeniSifreInputLayout.setError(null);
        ayarlarMevcutSifreInputLayout.setError(null);
        Toast.makeText(this,"Şifreniz Güncellendi",Toast.LENGTH_SHORT).show();
        hideProgress();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        kullanici= SharedPrefManager.getInstance(this).getKullanici();
        mevcutSifreTxt=ayarlarMevcutSifreTxt.getText().toString();
        yeniSifreTxt=ayarlarYeniSifreTxt.getText().toString();
        yeniTekrarSifreTxt=ayarlarYeniSifreTekrarTxt.getText().toString();

        switch (view.getId()) {
            case R.id.sifreGuncelleButon:
                passwordUpdatePresenter.updatePasswordValideCredentals(kullanici.getId(),mevcutSifreTxt,yeniSifreTxt,yeniTekrarSifreTxt);
                break;
            case R.id.sifreGuncelleGeriBtn:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
    }
}
