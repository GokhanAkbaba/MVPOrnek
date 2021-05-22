package com.birinesor.mvpornek.Baslangic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.birinesor.mvpornek.BirineSorHelper.BirineSorUtil;
import com.birinesor.mvpornek.Model.SifremiUnuttum.ForgotPasswordInteractorImpl;
import com.birinesor.mvpornek.Presenter.ForgotPassword.ForgotPasswordPresenter;
import com.birinesor.mvpornek.Presenter.ForgotPassword.ForgotPasswordPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.View.ForgotPasswordView;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgotPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgotPasswordFragment extends Fragment implements View.OnClickListener, ForgotPasswordView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ForgotPasswordPresenter forgotPasswordPresenter;
    ImageButton sifremiUnuttumBtn;
    TextInputLayout ePostaTextInputLayout;
    EditText sifremiUnuttumText;
    TextView girisYapSecenekBtn;
    Fragment fragment=null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForgotPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForgotPasswordFragment newInstance(String param1, String param2) {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
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
        View view=inflater.inflate(R.layout.fragment_kullanici_sifre_unuttum, container, false);
        sifremiUnuttumBtn=view.findViewById(R.id.sifremiUnuttumYapBtn);
        sifremiUnuttumBtn.setOnClickListener(this);
        ePostaTextInputLayout=view.findViewById(R.id.ePostaTextInputLayout);
        sifremiUnuttumText=view.findViewById(R.id.sifremiUnuttumText);
        girisYapSecenekBtn=view.findViewById(R.id.girisYapSecenekBtn);
        girisYapSecenekBtn.setOnClickListener(this);
        forgotPasswordPresenter=new ForgotPasswordPresenterImpl(this,new ForgotPasswordInteractorImpl(getActivity()));
        return view;
    }

    @Override
    public void onClick(View view) {
        String ePostaTxt=sifremiUnuttumText.getText().toString();
        switch (view.getId()){
            case R.id.sifremiUnuttumYapBtn:
                forgotPasswordPresenter.validateCredentials(ePostaTxt);
                break;
            case R.id.girisYapSecenekBtn:
                fragment=new LoginFragment();
                loadFragment(fragment,"LoginFragment");
                break;
        }
    }

    @Override
    public void showProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(getActivity(),null,"Yeni Şifreniz Gönderiliyor");
    }

    @Override
    public void hideProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBitir();
    }

    @Override
    public void setEpostaHatasi(String error) {
        ePostaTextInputLayout.setError(error);
    }

    @Override
    public void setEpostaKontrolu() {
        ePostaTextInputLayout.setError("E-postanız Eşleşmiyor.");
    }

    @Override
    public void navigateToHome() {
        ePostaTextInputLayout.setError(null);
        hideProgress();

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
}