package com.example.mvpornek.Fragment.NavBarFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.example.mvpornek.Fragment.ProfilTabFragment.BegendiklerimFragment;
import com.example.mvpornek.Fragment.ProfilTabFragment.CevaplarimFragment;
import com.example.mvpornek.Fragment.ProfilTabFragment.SorularimFragment;
import com.example.mvpornek.Fragment.PagerAdapter.ViewPagerAdapter;
import com.example.mvpornek.GlideApp;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.KullaniciGetir;
import com.example.mvpornek.Presenter.KullaniciGetir.UsersGetPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.UsersGetView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

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
    BegendiklerimFragment begendiklerimFragment;
    KullaniciGetir kullaniciGetir;
    private static final String ARG_PARAM1 = "param1";
    UsersGetPresenterImpl usersGetPresenter;
    NestedScrollView profilNestedView;
    CollapsingToolbarLayout collapsingToolbarLayout;

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    TextView profilKullaniciAdSoyadTxt,profilKullaniciAdiTxt,profilCevapSayi,toolbar;

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
        getActivity().findViewById(R.id.anasayfa_nav_view).setVisibility(View.VISIBLE);
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
        toolbar=view.findViewById(R.id.textView31);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.profilCollapsing);
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                    System.out.println("1");
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setText(getTitle());
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    isShow = true;
                } else if(isShow) {
                    toolbar.setText(" ");//careful there should a space between double quote otherwise it wont work
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    isShow = false;
                }
            }
        });


        if (getArguments() != null) {
            usersGetPresenter.loadUsersData(mParam1);
            sorularimFragment = SorularimFragment.newInstance(mParam1);
            cevaplarimFragment= CevaplarimFragment.newInstance(mParam1);
            begendiklerimFragment=BegendiklerimFragment.newInstance(mParam1);
            setTabLayout(sorularimFragment,begendiklerimFragment,cevaplarimFragment);

        }else{
            sorularimFragment = SorularimFragment.newInstance(kullanici.getId());
            cevaplarimFragment= CevaplarimFragment.newInstance(kullanici.getId());
            begendiklerimFragment=BegendiklerimFragment.newInstance(kullanici.getId());
            setTabLayout(sorularimFragment,begendiklerimFragment,cevaplarimFragment);
            profilKullaniciAdSoyadTxt.setText(kullanici.getAdSoyad());
            profilKullaniciAdiTxt.setText(kullanici.getKullaniciAdi());
            setTitle(kullanici.getAdSoyad());
            GlideApp.with(getActivity()).load(kullanici.getProfilFoto()).apply(new RequestOptions().centerCrop()).into(profilRoundedImage);
        }


        return view;
    }

    @Override
    public void onGetResult(KullaniciGetir kullaniciGetir) {
        this.kullaniciGetir=kullaniciGetir;
        setTitle(kullaniciGetir.getAdSoyad());
        profilKullaniciAdSoyadTxt.setText(kullaniciGetir.getAdSoyad());
        profilKullaniciAdSoyadTxt.setText(kullaniciGetir.getAdSoyad());
        profilKullaniciAdiTxt.setText(kullaniciGetir.getKullaniciAdi());
        profilCevapSayi.setText(String.valueOf(kullaniciGetir.getCevapSayisi()));
        GlideApp.with(getActivity()).load(kullaniciGetir.getProfilFoto()).apply(new RequestOptions().centerCrop()).into(profilRoundedImage);
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
