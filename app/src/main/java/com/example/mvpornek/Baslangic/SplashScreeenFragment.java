package com.example.mvpornek.Baslangic;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.example.mvpornek.Model.SelectionControlnteractorImpl;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.SelectionControlModel;
import com.example.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenter;
import com.example.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.example.mvpornek.Presenter.SelectionControlPresenter;
import com.example.mvpornek.Presenter.SelectionControlPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.InternetConnectionView;
import com.example.mvpornek.View.SelectionControl;


public class SplashScreeenFragment extends Fragment implements SelectionControl, InternetConnectionView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    boolean iSecim;
    private InternetConnectionPresenter internetConnectionPresenter;
    Fragment fragment=null;
    boolean isLoged,deger;



    SelectionControlPresenter selectionControlPresenter;
    public SelectionControlModel durum;
    Boolean durum2;
    Kullanici kullanici;

    public static SplashScreeenFragment newInstance(String param1, String param2) {
        SplashScreeenFragment fragment = new SplashScreeenFragment();
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

        //System.out.println("Durum2"+durum);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baslangic, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.uygulamaMavisiTwo));
        isLoged= SharedPrefManager.getInstance(getActivity()).isLoggedIn();
        kullanici=SharedPrefManager.getInstance(getActivity()).getKullanici();
        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(getActivity()));
        internetConnectionPresenter.internetBaglantiKontrolu();
        selectionControlPresenter=new SelectionControlPresenterImpl(this,new SelectionControlnteractorImpl(getActivity()));
        selectionControlPresenter.validateSelectionControl(kullanici.getId());
        return view;
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
        deger=true;
    }
    @Override
    public void internetBaglantisi() {
        deger=false;
    }

    @Override
    public void succesSelection() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isLoged){
                    fragment=new StartFragment();
                    loadFragment(fragment,"Start1");
                }else{
                    if(!deger){
                            startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
                    }else{
                        Toast.makeText(getActivity(),"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
                    }
                }
            }
        },2500);
    }

    @Override
    public void failedSelection() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isLoged){
                    fragment=new StartFragment();
                    loadFragment(fragment,"Start1");
                }else{
                    if(!deger){
                            fragment=new BeginingFragment();
                            loadFragment(fragment,"BeginingFragment");
                    }else{
                        Toast.makeText(getActivity(),"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
                    }
                }
            }
        },2500);
    }
}


