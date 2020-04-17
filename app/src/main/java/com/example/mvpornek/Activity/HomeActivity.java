package com.example.mvpornek.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.mvpornek.Fragment.DenemeFragment;
import com.example.mvpornek.Fragment.NavBarFragment.BildirimlerFragment;
import com.example.mvpornek.Fragment.NavBarFragment.HomeFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SearchFragment;
import com.example.mvpornek.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {



    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);

        anaSayfaGetir();

        bottomNavigationView=findViewById(R.id.anasayfa_nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.anaSayfaItem:
                        anaSayfaGetir();
                        break;
                    case R.id.profilItem:
                        startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                        break;
                    case R.id.bildirimItem:
                        BildirimlerFragment bildirimlerFragment = new BildirimlerFragment();
                        FragmentTransaction fragmentTransactionBildirim=getSupportFragmentManager().beginTransaction();
                        fragmentTransactionBildirim.replace(R.id.anaSayfaFrameLayout,bildirimlerFragment);
                        fragmentTransactionBildirim.commit();
                        break;
                    case R.id.aramaItem:
                        SearchFragment searchFragment = new SearchFragment();
                        FragmentTransaction fragmentTransactionBildirim2=getSupportFragmentManager().beginTransaction();
                        fragmentTransactionBildirim2.replace(R.id.anaSayfaFrameLayout,searchFragment);
                        fragmentTransactionBildirim2.commit();

                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {


    }
    public void anaSayfaGetir(){
        HomeFragment homeFragment=new HomeFragment();
        FragmentTransaction fragmentTransactionHome=getSupportFragmentManager().beginTransaction();
        fragmentTransactionHome.replace(R.id.anaSayfaFrameLayout,homeFragment);
        fragmentTransactionHome.commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Birine Sor");
    }
}
