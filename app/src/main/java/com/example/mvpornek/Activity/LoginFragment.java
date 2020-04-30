package com.example.mvpornek.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpornek.Model.ModelGiris.InternetConnectionInteractorImpl;
import com.example.mvpornek.Model.ModelGiris.LoginInteractorImpl;
import com.example.mvpornek.Presenter.InternetConnectionPresenter;
import com.example.mvpornek.Presenter.InternetConnectionPresenterImpl;
import com.example.mvpornek.Presenter.LoginPresenter;
import com.example.mvpornek.Presenter.LoginPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.InternetConnectionView;
import com.example.mvpornek.View.LoginView;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment implements LoginView, InternetConnectionView, View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LoginPresenter loginPresenter;
    private InternetConnectionPresenter internetConnectionPresenter;
    TextView kayitOlSecenek,girisKullaniciTxt,girisSifreTxt,sifreUnuttumTxt;
    ImageButton girisYap;
    String girisKullanici,girisSifre;

    TextInputLayout sifreTextInputLayout,epostaInputLayout;



    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kullanici_giris, container, false);

        kayitOlSecenek=view.findViewById(R.id.kayitOlSecenekTxt);
        kayitOlSecenek.setOnClickListener(this);

        girisYap=view.findViewById(R.id.girisYapBtn);
        girisYap.setOnClickListener(this);

        girisKullaniciTxt=view.findViewById(R.id.girisKullaniciText);
        girisSifreTxt=view.findViewById(R.id.girisSifreText);
        sifreUnuttumTxt=view.findViewById(R.id.sifreUnuttumText);
        sifreUnuttumTxt.setOnClickListener(this);

        sifreTextInputLayout=view.findViewById(R.id.sifreTextInputLayout);
        epostaInputLayout=view.findViewById(R.id.ePostaTextInputLayout);

        loginPresenter=new LoginPresenterImpl(this,new LoginInteractorImpl(getActivity()));
        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(getActivity()));
        internetConnectionPresenter.internetBaglantiKontrolu();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.uygulamaMavisiTwo));
        return view;
    }

    @Override
    public void onClick(View view) {

        girisKullanici=girisKullaniciTxt.getText().toString();
        girisSifre=girisSifreTxt.getText().toString();

        switch (view.getId())
        {
            case R.id.kayitOlSecenekTxt:
                getRegisterFragment();
                break;
            case R.id.girisYapBtn:
                loginPresenter.loginValideCredentals(girisKullanici,girisSifre);
                break;
            case R.id.sifreUnuttumText:
                break;
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setGirisEPostaAdiHatasi() {
        epostaInputLayout.setError("Eposta Boş Bırakmayınız");
    }

    @Override
    public void setGirisSifreHatasi() {
        sifreTextInputLayout.setError("Şifre Boş Bırakmayınız");
    }

    @Override
    public void navigateToGiris() {
        epostaInputLayout.setError(null);
        sifreTextInputLayout.setError(null);
        Toast.makeText(getActivity(),"Giriş İşleminiz Başarılı",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity().getApplicationContext(),HomeActivity.class));
    }
    @Override
    public void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void getRegisterFragment()
    {
        RegisterFragment registerFragment=new RegisterFragment();
        callFragment(registerFragment);
    }

    public void callFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.startActivityLayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void internetBaglantiHatasi() {
        Toast.makeText(getActivity(),"İnternet Bağlantı Hatası",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void internetBaglantisi() {

    }
}