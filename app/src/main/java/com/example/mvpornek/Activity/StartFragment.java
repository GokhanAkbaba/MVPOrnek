package com.example.mvpornek.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvpornek.R;


public class StartFragment extends Fragment implements View.OnClickListener {
    Button hesapOlustur;
    TextView girisYapSecenek;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartFragment() {

    }


    public static StartFragment newInstance(String param1, String param2) {
        StartFragment fragment = new StartFragment();
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
        View view = inflater.inflate(R.layout.fragment_baslangic1, container, false);

        hesapOlustur=view.findViewById(R.id.hesapOlusturBtn);
        hesapOlustur.setOnClickListener(this);

        girisYapSecenek=view.findViewById(R.id.baslangicGirisSecenek);
        girisYapSecenek.setOnClickListener(this);

        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.hesapOlusturBtn:
                getRegisterFragment();
                break;
            case R.id.baslangicGirisSecenek:

                break;
        }

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
}
