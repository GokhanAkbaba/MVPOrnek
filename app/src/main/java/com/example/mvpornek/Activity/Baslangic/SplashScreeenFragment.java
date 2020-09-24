package com.example.mvpornek.Activity.Baslangic;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;


public class SplashScreeenFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    Fragment fragment=null;

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

        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.uygulamaMavisiTwo));


        final boolean isLoged= SharedPrefManager.getInstance(getActivity()).isLoggedIn();
        final boolean iSecim=SharedPrefManager.getInstance(getActivity()).secim();
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
}


