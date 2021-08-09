package com.birinesor.mvpornek.Baslangic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.BirineSorHelper.BirineSorUtil;
import com.birinesor.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.birinesor.mvpornek.Model.Kayit.RegisterInteractorImpl;
import com.birinesor.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenter;
import com.birinesor.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.birinesor.mvpornek.Presenter.Register.RegisterPresenter;
import com.birinesor.mvpornek.Presenter.Register.RegisterPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.View.InternetConnectionView;
import com.birinesor.mvpornek.View.RegisterView;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterFragment extends Fragment implements RegisterView,View.OnClickListener, InternetConnectionView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RegisterPresenter presenter;
    private InternetConnectionPresenter internetConnectionPresenter;

    TextInputLayout adSoyadInputLayout,kullaniciAdiInputLayout,ePostaInputLayout,sifreInputLayout,sifreTekrarInputLayout;
    private EditText editTextadSoyad,editTextkullaniciAdi,editTextMail,editTextsifre,editTextsifreTekrar;
    TextView girisSecenekBtn;
    String ePosta,sifre,adSoyad,kullaniciAdi,sifreTekrar;
    private String mParam1;
    private String mParam2;
    Fragment fragment=null;

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
        girisSecenekBtn.setOnClickListener(this);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        view.findViewById(R.id.kayitYapBtn).setOnClickListener(this);
        presenter = new RegisterPresenterImpl(this, new RegisterInteractorImpl(getActivity()));
        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(getActivity()));
        internetConnectionPresenter.internetBaglantiKontrolu();
        return view;
    }
    @Override
    public void showProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(getActivity(),null,"Kayıt İşlemi Gerçekleştiriliyor");
    }
    @Override
    public void hideProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBitir();
    }
    @Override
    public void setKullaniciAdiHatasi(String error) {
        kullaniciAdiInputLayout.setError(error);
    }

    @Override
    public void setKullaniciAdiOnay() {
        kullaniciAdiInputLayout.setError(null);
    }

    @Override
    public void setAdSoyadHatasi() {
       adSoyadInputLayout.setError("Ad Soyad Boş Bırakmayınız");

    }

    @Override
    public void setAdSoyadOnay() {
        adSoyadInputLayout.setError(null);
    }

    @Override
    public void setSifeHatasi() {
        sifreInputLayout.setError("Sifrenizi Boş Bırakmayınız");
    }

    @Override
    public void setSifreHatasiOnay() {
        sifreInputLayout.setError(null);
    }

    @Override
    public void setEpostaHatasi(String error) {
        ePostaInputLayout.setError(error);
    }
    @Override
    public void setSifreTekrarHatasi() {
       sifreTekrarInputLayout.setError("Şifre Tekrar Boş Bırakmayınız");
    }

    @Override
    public void setSifreTekrarOnay() {
        sifreTekrarInputLayout.setError(null);
    }

    @Override
    public void setSifreKontrol() {
        sifreTekrarInputLayout.setError("Şifreler Eşleşmiyor");
    }

    @Override
    public void setEpostaOnay() {
        ePostaInputLayout.setError(null);
    }

    @Override
    public void navigateToHome() {
        kullaniciAdiInputLayout.setError(null);
        adSoyadInputLayout.setError(null);
        sifreInputLayout.setError(null);
        ePostaInputLayout.setError(null);
        sifreTekrarInputLayout.setError(null);
        Toast.makeText(getActivity(),"Kayıt İşleminiz Başarılı",Toast.LENGTH_SHORT).show();
        fragment=new BeginingFragment();
        loadFragment(fragment,"BeginingFragment");
        hideProgress();
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

                presenter.validateCredentials(kullaniciAdi,adSoyad,sifre,sifreTekrar,ePosta);
                break;
            case R.id.girisSecenektxt:
                fragment=new LoginFragment();
                loadFragment(fragment,"LoginFragment");
                break;
        }

    }
    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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
        Toast.makeText(getActivity(),"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void internetBaglantisi() {

    }
}
