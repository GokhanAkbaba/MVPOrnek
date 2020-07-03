package com.example.mvpornek.Fragment.NavBarFragment;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.mvpornek.Fragment.ProfilTabFragment.BegendiklerimFragment;
import com.example.mvpornek.Fragment.ProfilTabFragment.CevaplarimFragment;
import com.example.mvpornek.Fragment.ProfilTabFragment.SorularimFragment;
import com.example.mvpornek.Fragment.ViewPagerAdapter;
import com.example.mvpornek.GlideApp;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.KullaniciGetir;
import com.example.mvpornek.Presenter.KullaniciGetir.UsersGetPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.UsersGetView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment implements UsersGetView {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Kullanici kullanici;
    ImageView profilRoundedImage;
    private ViewPagerAdapter viewPagerAdapter;
    SorularimFragment sorularimFragment;
    CevaplarimFragment cevaplarimFragment;
    KullaniciGetir kullaniciGetir;
    private static final String ARG_PARAM1 = "param1";
    UsersGetPresenterImpl usersGetPresenter;
    NestedScrollView profilNestedView;

    TextView profilKullaniciAdSoyadTxt,profilKullaniciAdiTxt,profilCevapSayi;

    // TODO: Rename and change types of parameters
    private int mParam1;


    public ProfilFragment() {
        // Required empty public constructor
    }

    public static ProfilFragment newInstance(int param1) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profil, container, false);
                viewPager= (ViewPager) view.findViewById(R.id.profilViewPager);
        tabLayout=(TabLayout) view.findViewById(R.id.profilTablayout);
        usersGetPresenter=new UsersGetPresenterImpl(this);
        profilKullaniciAdSoyadTxt=view.findViewById(R.id.profilKullaniciAdSoyadTxt);
        profilRoundedImage=view.findViewById(R.id.profilRoundedImageView);
        profilKullaniciAdiTxt=view.findViewById(R.id.profilKullaniciAdiTxt);
        profilCevapSayi=view.findViewById(R.id.profilCevapSayi);
        profilNestedView=view.findViewById(R.id.profilNestedView);

        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();

        if (getArguments() != null) {
            usersGetPresenter.loadUsersData(mParam1);
            sorularimFragment = SorularimFragment.newInstance(mParam1);
            cevaplarimFragment= CevaplarimFragment.newInstance(mParam1);
            setTabLayout(sorularimFragment,new BegendiklerimFragment(),cevaplarimFragment);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    profilKullaniciAdSoyadTxt.setText(kullaniciGetir.getAdSoyad());
                    profilKullaniciAdiTxt.setText(kullaniciGetir.getKullaniciAdi());
                    profilCevapSayi.setText(String.valueOf(kullaniciGetir.getCevapSayisi()));
                    GlideApp.with(getActivity()).load(kullaniciGetir.getProfilFoto()).apply(new RequestOptions().centerCrop()).into(profilRoundedImage);
                }
            }, 400);
        }else{
            sorularimFragment = SorularimFragment.newInstance(kullanici.getId());
            cevaplarimFragment= CevaplarimFragment.newInstance(kullanici.getId());
            setTabLayout(sorularimFragment,new BegendiklerimFragment(),cevaplarimFragment);
            profilKullaniciAdSoyadTxt.setText(kullanici.getAdSoyad());
            profilKullaniciAdiTxt.setText(kullanici.getKullaniciAdi());
            GlideApp.with(getActivity()).load(kullanici.getProfilFoto()).apply(new RequestOptions().centerCrop()).into(profilRoundedImage);
        }
        return view;
    }

    @Override
    public void onGetResult(KullaniciGetir kullaniciGetir) {
       this.kullaniciGetir=kullaniciGetir;
    }
    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getActivity(),"Profil Görüntülenme Başarısız",Toast.LENGTH_SHORT);
    }
    public void setTabLayout(Fragment sorularim, Fragment begendiklerim,Fragment cevaplarim){
        viewPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.AddFragment(sorularim,"Sorularım");
        viewPagerAdapter.AddFragment(begendiklerim,"Beğendiklerim");
        viewPagerAdapter.AddFragment(cevaplarim,"Cevaplarim");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
