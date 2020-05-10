package com.example.mvpornek.Activity.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpornek.BirineSorHelper.BirineSorUtil;
import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.Kullanici;
import com.example.mvpornek.Model.ModelGiris.PasswordUpdateInteractor;
import com.example.mvpornek.Model.ModelGiris.PasswordUpdateInteractorImpl;
import com.example.mvpornek.Presenter.PasswordUpdatePresenter;
import com.example.mvpornek.Presenter.PasswordUpdatePresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.PasswordUpdate;
import com.google.android.material.textfield.TextInputLayout;

public class SifreDuzenleActivity extends Activity implements PasswordUpdate, View.OnClickListener {
    TextInputLayout ayarlarMevcutSifreInputLayout,ayarlarYeniSifreInputLayout,ayarlarYeniSifreTekrarInputLayout;
    Button sifreGuncelle,sifreGuncelleGeri;
    String mevcutSifreTxt,yeniSifreTxt,yeniTekrarSifreTxt;
    EditText ayarlarMevcutSifreTxt,ayarlarYeniSifreTxt,ayarlarYeniSifreTekrarTxt;
    Kullanici kullanici;
    private PasswordUpdatePresenter passwordUpdatePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
