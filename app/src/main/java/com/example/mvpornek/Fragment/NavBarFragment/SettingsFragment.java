package com.example.mvpornek.Fragment.NavBarFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;

import com.example.mvpornek.Activity.Ayarlar.BildirimlerDuzenleActivity;
import com.example.mvpornek.Activity.Ayarlar.ProfilDuzenleActivity;
import com.example.mvpornek.Activity.Ayarlar.SifreDuzenleActivity;
import com.example.mvpornek.Baslangic.IntroActivity;
import com.example.mvpornek.BirineSorHelper.BirineSorUtil;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Presenter.TokenSil.TokenDeletePresenter;
import com.example.mvpornek.Presenter.TokenSil.TokenDeletePresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.TokenDeleteView;


public class SettingsFragment extends Fragment implements View.OnClickListener, TokenDeleteView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    TokenDeletePresenter tokenDeletePresenter;

    Toolbar toolbar;

    private Kullanici kullanici;

    public SettingsFragment() {

    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view=inflater.inflate(R.layout.ayarlar, container, false);
        kullanici=SharedPrefManager.getInstance(getActivity()).getKullanici();
        toolbar=(Toolbar) view.findViewById(R.id.ayarlarToolbar);
        RelativeLayout profilRelativeLayout=(RelativeLayout) view.findViewById(R.id.profilDuzenleLayout);
        profilRelativeLayout.setOnClickListener(this);
        RelativeLayout cikisRelativeLayout=(RelativeLayout) view.findViewById(R.id.cikisYapLayout);
        cikisRelativeLayout.setOnClickListener(this);
        RelativeLayout sifreRelativeDegistirLayout=(RelativeLayout) view.findViewById(R.id.sifreDegistirLayout);
        sifreRelativeDegistirLayout.setOnClickListener(this);
        RelativeLayout bildirimlerLayout=(RelativeLayout) view.findViewById(R.id.bildirimlerLayout);
        bildirimlerLayout.setOnClickListener(this);

        tokenDeletePresenter=new TokenDeletePresenterImpl(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profilDuzenleLayout:
                startActivity(new Intent(getActivity().getApplicationContext(), ProfilDuzenleActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
            case R.id.cikisYapLayout:
                SharedPrefManager.getInstance(getActivity()).clear();
                startActivity(new Intent(getActivity().getApplicationContext(), IntroActivity.class));
                tokenDeletePresenter.deleteToken(kullanici.getId());
                BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(getActivity(),null,"Çıkış İşlemi Gerçekleştiriliyor");
                break;
            case R.id.sifreDegistirLayout:
                startActivity(new Intent(getActivity().getApplicationContext(), SifreDuzenleActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
            case R.id.bildirimlerLayout:
                startActivity(new Intent(getActivity().getApplicationContext(), BildirimlerDuzenleActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
        }
    }

    @Override
    public void showTokenDeleteSuccesMessage() {
        Log.d("Token Delete Success","Token Başarı ile Silindi");
    }

    @Override
    public void showTokenDeleteFailedMessage(String failedMessage) {
        Log.d("Token Delete Failed",failedMessage);
    }
}
