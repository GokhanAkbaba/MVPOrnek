package com.example.mvpornek.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpornek.Model.ModelGiris.RegisterInteractorImpl;
import com.example.mvpornek.Presenter.RegisterPresenter;
import com.example.mvpornek.Presenter.RegisterPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.View.RegisterView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static android.content.Context.MODE_PRIVATE;


public class RegisterFragment extends Fragment implements RegisterView,View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RegisterPresenter presenter;
    private ProgressBar progressBar;
    TextInputLayout adSoyadInputLayout,kullaniciAdiInputLayout,ePostaInputLayout,sifreInputLayout,sifreTekrarInputLayout;
    private EditText editTextadSoyad,editTextkullaniciAdi,editTextMail,editTextsifre,editTextsifreTekrar;
    private Button girisSecenekBtn;
    String ePosta,sifre,adSoyad,kullaniciAdi,sifreTekrar;
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {

    }
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kullanici_kayit, container, false);

        progressBar=view.findViewById(R.id.progressBar2);
        adSoyadInputLayout=view.findViewById(R.id.adSoyadTextInputLayout);
        kullaniciAdiInputLayout=view.findViewById(R.id.kullaniciAdiTextInputLayout);
        ePostaInputLayout=view.findViewById(R.id.ePostaTextInputLayout);
        sifreInputLayout=view.findViewById(R.id.sifreTextInputLayout);
        sifreTekrarInputLayout=view.findViewById(R.id.sifreTekrarTextInputLayout);
        editTextadSoyad = view.findViewById(R.id.adSoyadText);
        editTextkullaniciAdi = view.findViewById(R.id.kullaniciAdiText);
        editTextMail = view.findViewById(R.id.ePostaText);
        editTextsifre = view.findViewById(R.id.sifreText);
        editTextsifreTekrar = view.findViewById(R.id.sifreTekrariText);

        girisSecenekBtn=view.findViewById(R.id.girisSecenektxt);
        girisSecenekBtn.setClickable(false);
        girisSecenekBtn.setAlpha(.5f);
        girisSecenekBtn.setOnClickListener(this);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.uygulamaMavisiTwo));

        view.findViewById(R.id.kayitYapBtn).setOnClickListener(this);
        presenter = new RegisterPresenterImpl(this, new RegisterInteractorImpl());


        return view;
    }
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setKullaniciAdiHatasi() {
        kullaniciAdiInputLayout.setError("Kullanıcı Adını Boş Bırakmayınız");
    }

    @Override
    public void setOffKullaniciAdiHatasi() {
        kullaniciAdiInputLayout.setError(null);
    }


    @Override
    public void setAdSoyadHatasi() {
        adSoyadInputLayout.setError("Ad Soyad Boş Bırakmayınız");

    }

    @Override
    public void setOffAdSoyadHatasi() {
        adSoyadInputLayout.setError(null);
    }

    @Override
    public void setSifeHatasi() {
        sifreInputLayout.setError("Sifrenizi Boş Bırakmayınız");
    }

    @Override
    public void setOffSifeHatasi() {
        sifreInputLayout.setError(null);
    }

    @Override
    public void setEpostaHatasi() {
        ePostaInputLayout.setError("e-Posta Boş Bırakmayınız");
    }

    @Override
    public void setOffEpostaHatasi() {
        ePostaInputLayout.setError(null);
    }

    @Override
    public void setSifreTekrarHatasi() {
        sifreTekrarInputLayout.setError("Şifre Tekrar Boş Bırakmayınız");
    }

    @Override
    public void setOffSifreTekrarHatasi() {
        sifreTekrarInputLayout.setError(null);
    }

    @Override
    public void setSifreKontrol() {
        sifreTekrarInputLayout.setError("Şifreler Eşleşmiyor");
    }

    @Override
    public void setOffSifreKontrol() {
        sifreTekrarInputLayout.setError(null);
    }

    @Override
    public void navigateToHome() {
        //Toast.makeText(getActivity(),"Kayıt İşleminiz Başarılı",Toast.LENGTH_SHORT).show();
        Log.e("df","KAYIT BAŞARILI");
        //startActivity(new Intent(getApplicationContext(),BaslarkenActivity.class));
    }

    @Override
    public void onClick(View view) {

        ePosta = editTextMail.getText().toString();
        sifre=editTextsifre.getText().toString();
        adSoyad=editTextadSoyad.getText().toString();
        kullaniciAdi=editTextkullaniciAdi.getText().toString();
        sifreTekrar=editTextsifreTekrar.getText().toString();
        switch (view.getId())
        {
            case R.id.kayitYapBtn:
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("EPosta",ePosta);
                editor.putBoolean("girisYapildi",true);
                presenter.validateCredentials(kullaniciAdi,adSoyad,sifre,sifreTekrar,ePosta);
                break;
            case R.id.girisSecenektxt:

                break;
        }

    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}
