package com.example.mvpornek.Baslangic;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.SelectionControlModel;
import com.example.mvpornek.Presenter.SelectionControlPresenter;
import com.example.mvpornek.Presenter.SelectionControlPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.SelectionControl;


public class SplashScreeenFragment extends Fragment implements SelectionControl {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    Fragment fragment=null;
    boolean iSecim=false;

    SelectionControlPresenter selectionControlPresenter;


    SelectionControlModel durum;




    public SplashScreeenFragment()
    {

    }


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baslangic, container, false);
        Kullanici kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.uygulamaMavisiTwo));

        selectionControlPresenter= new SelectionControlPresenterImpl(this);
        selectionControlPresenter.loadData(kullanici.getId());


        final boolean isLoged= SharedPrefManager.getInstance(getActivity()).isLoggedIn();

        Thread thread= new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if(isLoged == false){
                        fragment=new StartFragment();
                        loadFragment(fragment,"Start1");
                    }else{
                        iSecim=durum.getSecim();
                        if(iSecim == false){
                            fragment=new BeginingFragment();
                            loadFragment(fragment,"BeginingFragment");
                        }else{
                            startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
                        }

                    }
                }
                super.run();
            }
        };
        thread.start();
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
    public void showSuccesMessage(SelectionControlModel message) {
        this.durum=message;
    }
}


