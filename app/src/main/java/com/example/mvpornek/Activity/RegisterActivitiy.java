package com.example.mvpornek.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvpornek.Model.ModelGiris.RegisterInteractorImpl;
import com.example.mvpornek.Presenter.RegisterPresenter;
import com.example.mvpornek.Presenter.RegisterPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.View.RegisterView;

public class RegisterActivitiy extends Activity implements RegisterView,View.OnClickListener {

    private RegisterPresenter presenter;
    private ProgressBar progressBar;
    private EditText editTextadSoyad,editTextkullaniciAdi,editTextMail,editTextsifre,editTextsifreTekrar;
    String ePosta,sifre,adSoyad,kullaniciAdi,sifreTekrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_kullanici_kayit);

        progressBar=findViewById(R.id.progressBar2);

        editTextadSoyad = findViewById(R.id.adSoyadText);
        editTextkullaniciAdi = findViewById(R.id.kullaniciAdiText);
        editTextMail = findViewById(R.id.ePostaText);
        editTextsifre = findViewById(R.id.sifreText);
        editTextsifreTekrar = findViewById(R.id.sifreTekrariText);


        findViewById(R.id.kayitYapBtn).setOnClickListener(this);
        presenter = new RegisterPresenterImpl(RegisterActivitiy.this, new RegisterInteractorImpl());


    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        ePosta = editTextMail.getText().toString();
        sifre=editTextsifre.getText().toString();
        adSoyad=editTextadSoyad.getText().toString();
        kullaniciAdi=editTextkullaniciAdi.getText().toString();
        sifreTekrar=editTextsifreTekrar.getText().toString();

        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("EPosta",ePosta);
        editor.putBoolean("girisYapildi",true);
        editor.apply();

        presenter.validateCredentials(kullaniciAdi,adSoyad,sifre,sifreTekrar,ePosta);

    }

    @Override
    public void showProgress() {
        ///progressBar.setVisibility(View.VISIBLE);


    }

    @Override
    public void hideProgress() {
        ///progressBar.setVisibility(View.GONE);
        Log.e("hideProgress","hideProgress");
    }

    @Override
    public void setKullaniciAdiHatasi()
    {
        editTextkullaniciAdi.setError("Kullanıcı Adını Boş Bırakmayınız");
    }

    @Override
    public void setAdSoyadHatasi()
    {
        editTextadSoyad.setError("Ad Soyad Boş Bırakmayınız");
    }

    @Override
    public void setSifeHatasi()
    {
        editTextsifre.setError("Sifrenizi Boş Bırakmayınız");
    }

    @Override
    public void setEpostaHatasi()
    {
        editTextMail.setError("e-Posta Boş Bırakmayınız");
    }

    @Override
    public void setSifreTekrarHatasi()
    {
        editTextsifreTekrar.setError("Şifre Tekrar Boş Bırakmayınız");
    }

    @Override
    public void navigateToHome()
    {
        Toast.makeText(this,"Kayıt İşleminiz Başarılı",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),BaslarkenActivity.class));
    }


       /* Call<ResponseBody> call = RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kullaniciOlustur(adSoyad,kullaniciAdi,ePosta,sifre,sifreTekrar);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s= response.body().string();
                    Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }*/
}
