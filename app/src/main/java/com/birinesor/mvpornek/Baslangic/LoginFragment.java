package com.birinesor.mvpornek.Baslangic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.BirineSorHelper.BirineSorUtil;
import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.birinesor.mvpornek.Model.Giris.LoginInteractorImpl;
import com.birinesor.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenter;
import com.birinesor.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.birinesor.mvpornek.Presenter.Login.LoginPresenter;
import com.birinesor.mvpornek.Presenter.Login.LoginPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.View.InternetConnectionView;
import com.birinesor.mvpornek.View.LoginView;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment implements LoginView, InternetConnectionView, View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LoginPresenter loginPresenter;
    private InternetConnectionPresenter internetConnectionPresenter;
    TextView kayitOlSecenek,girisKullaniciTxt,girisSifreTxt,sifreUnuttumTxt,kayitOlSecenekBtn;
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
        if (Build.VERSION.SDK_INT >= 27) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        kayitOlSecenek=view.findViewById(R.id.kayitOlSecenekTxt);
        kayitOlSecenek.setOnClickListener(this);

        kayitOlSecenekBtn=view.findViewById(R.id.kayitOlSecenekBtn);
        kayitOlSecenekBtn.setOnClickListener(this);

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
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        return view;
    }

    @Override
    public void onClick(View view) {

        girisKullanici=girisKullaniciTxt.getText().toString();
        girisSifre=girisSifreTxt.getText().toString();
        Fragment fragment=null;
        switch (view.getId())
        {
            case R.id.kayitOlSecenekTxt:
                fragment=new RegisterFragment();
                loadFragment(fragment,"LoginFragment");
                internetConnectionPresenter.internetBaglantiKontrolu();
                break;
            case R.id.girisYapBtn:
                loginPresenter.loginValideCredentals(girisKullanici,girisSifre);
                break;
            case R.id.sifreUnuttumText:
                fragment=new ForgotPasswordFragment();
                loadFragment(fragment,"ForgotPasswordFragment");
                break;
            case R.id.kayitOlSecenekBtn:
                fragment=new RegisterFragment();
                loadFragment(fragment,"LoginFragment");
                break;
        }

    }

    @Override
    public void showProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(getActivity(),null,"Giriş İşlemi Gerçekleştiriliyor");
    }

    @Override
    public void hideProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBitir();
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
    public void setGirisKontrol(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToGiris() {
        epostaInputLayout.setError(null);
        sifreTextInputLayout.setError(null);
        Toast.makeText(getActivity(),"Giriş İşleminiz Başarılı",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
        hideProgress();

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




    private boolean loadFragment(Fragment fragment,String fragmentTag)
    {

        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragmentTag)
                    .replace(R.id.startActivityLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void internetBaglantiHatasi() {
        Toast.makeText(getActivity(),"İnternet Bağlantı Hatası",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void internetBaglantisi() {

    }
}
