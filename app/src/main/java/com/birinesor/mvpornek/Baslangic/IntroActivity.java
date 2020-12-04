package com.birinesor.mvpornek.Baslangic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;

import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.R;

public class IntroActivity extends FragmentActivity {
    Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
       if (InitApplication.getInstance(this).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
            if (Build.VERSION.SDK_INT >= 27) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            }
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getWindow().setStatusBarColor(getResources().getColor(R.color.whiteStatus));
            if (Build.VERSION.SDK_INT >= 27) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.whiteStatus));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        getSplashScreeenFragment();
    }



    public void getSplashScreeenFragment()
    {
        SplashScreeenFragment splashScreeenFragment=new SplashScreeenFragment();
        callFragment(splashScreeenFragment,"");

    }

    private boolean callFragment(Fragment fragment,String fragmentTag)
    {
        if (fragment != null) {
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragmentTag)
                    .replace(R.id.startActivityLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
