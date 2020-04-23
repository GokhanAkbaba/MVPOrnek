package com.example.mvpornek.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.mvpornek.Fragment.NavBarFragment.SearchFragment;
import com.example.mvpornek.R;

public class IntroActivity extends FragmentActivity {
    Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        getSplashScreeenFragment();
    }



    public void getSplashScreeenFragment()
    {
        SplashScreeenFragment splashScreeenFragment=new SplashScreeenFragment();
        callFragment(splashScreeenFragment);
    }

    public void callFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.startActivityLayout,fragment);
        fragmentTransaction.commit();
    }
}
