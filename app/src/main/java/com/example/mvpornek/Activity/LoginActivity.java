package com.example.mvpornek.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpornek.Model.ModelGiris.LoginInteractorImpl;
import com.example.mvpornek.Model.ModelGiris.RegisterInteractorImpl;
import com.example.mvpornek.Presenter.LoginPresenter;
import com.example.mvpornek.Presenter.LoginPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.View.LoginView;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
    private LoginPresenter loginPresenter;
    TextView kayitOlSecenek,girisKullaniciTxt,girisSifreTxt,sifreUnuttumTxt;
    ImageButton girisYap;
    String girisKullanici,girisSifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_kullanici_giris);
        kayitOlSecenek=findViewById(R.id.kayitOlSecenekTxt);
        kayitOlSecenek.setOnClickListener(this);

        girisYap=findViewById(R.id.girisYapBtn);
        girisYap.setOnClickListener(this);

        girisKullaniciTxt=findViewById(R.id.girisKullaniciText);
        girisSifreTxt=findViewById(R.id.girisSifreText);
        sifreUnuttumTxt=findViewById(R.id.sifreUnuttumText);
        sifreUnuttumTxt.setOnClickListener(this);

        loginPresenter=new LoginPresenterImpl(LoginActivity.this,new LoginInteractorImpl());
    }

    @Override
    public void onClick(View view) {
        girisKullanici=girisKullaniciTxt.getText().toString();
        girisSifre=girisSifreTxt.getText().toString();

        switch (view.getId())
        {
            case R.id.kayitOlSecenekTxt:
                startActivity(new Intent(getApplicationContext(),RegisterActivitiy.class));
                break;
            case R.id.girisYapBtn:
                loginPresenter.loginValideCredentals(girisKullanici,girisSifre);
                break;
            case R.id.sifreUnuttumText:
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
                break;
        }

    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setGirisEPostaAdiHatasi() {
            girisKullaniciTxt.setError("Eposta Boş Bırakmayınız");
    }

    @Override
    public void setGirisSifreHatasi() {
            girisSifreTxt.setError("Şifre Boş Bırakmayınız");

    }

    @Override
    public void navigateToGiris() {
        Toast.makeText(this,"Giriş İşleminiz Başarılı",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}
