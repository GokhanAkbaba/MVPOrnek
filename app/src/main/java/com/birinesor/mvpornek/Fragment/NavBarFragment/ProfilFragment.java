package com.birinesor.mvpornek.Fragment.NavBarFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.bumptech.glide.request.RequestOptions;
import com.birinesor.mvpornek.Activity.Ayarlar.ProfilDuzenleActivity;
import com.birinesor.mvpornek.Fragment.ProfilTabFragment.BegendiklerimFragment;
import com.birinesor.mvpornek.Fragment.ProfilTabFragment.CevaplarimFragment;
import com.birinesor.mvpornek.Fragment.ProfilTabFragment.SorularimFragment;
import com.birinesor.mvpornek.Fragment.PagerAdapter.ViewPagerAdapter;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.KullaniciGetir;
import com.birinesor.mvpornek.Presenter.KullaniciGetir.UsersGetPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.UsersGetView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment implements UsersGetView,View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Kullanici kullanici;
    ImageView profilRoundedImage,ayarlarButon;
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

    TextView profilKullaniciAdSoyadTxt,profilKullaniciAdiTxt,profilCevapSayi,toolbar,profilSoruSayisi;

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
        CommentBottomDialogFragment test = (CommentBottomDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag("CommentBottomDialogFragment");
        if (test != null && test.isVisible()) {
            System.out.println("KUTU DURUM-- "+CommentBottomDialogFragment.kutuDurum);
            if(CommentBottomDialogFragment.kutuDurum){
                CommentBottomDialogFragment.instance.dialogCancel();
                CommentBottomDialogFragment.kutuDurum=false;
            }
        }

        HomeActivity.getInstance().startAds();

        getActivity().findViewById(R.id.anasayfa_nav_view).setVisibility(View.VISIBLE);
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
        profilSoruSayisi=view.findViewById(R.id.profilSoruSayisi);
        profilNestedView=view.findViewById(R.id.profilNestedView);
        ayarlarButon=view.findViewById(R.id.search_content_search_settings);
        ayarlarButon.setOnClickListener(this);
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
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setText(getTitle());
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    isShow = true;
                } else if(isShow) {
                    toolbar.setText(" ");
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
            usersGetPresenter.loadUsersData(kullanici.getId());
            sorularimFragment = SorularimFragment.newInstance(kullanici.getId());
            cevaplarimFragment= CevaplarimFragment.newInstance(kullanici.getId());
            begendiklerimFragment=BegendiklerimFragment.newInstance(kullanici.getId());
            setTabLayout(sorularimFragment,begendiklerimFragment,cevaplarimFragment);
            setTitle(kullanici.getAdSoyad());
            GlideApp.with(getActivity()).load(kullanici.getProfilFoto()).apply(new RequestOptions().centerCrop()).into(profilRoundedImage);
        }


        return view;
    }

    @Override
    public void onGetResult(KullaniciGetir kullaniciGetir) {
        this.kullaniciGetir=kullaniciGetir;
        if(kullanici.getId() ==kullaniciGetir.getId()){
            ayarlarButon.setVisibility(View.VISIBLE);
        }else{
            ayarlarButon.setVisibility(View.INVISIBLE);
        }
        setTitle(kullaniciGetir.getAdSoyad());
        profilKullaniciAdSoyadTxt.setText(kullaniciGetir.getAdSoyad());
        profilKullaniciAdiTxt.setText("@"+kullaniciGetir.getKullaniciAdi());
        profilCevapSayi.setText(String.valueOf(kullaniciGetir.getCevapSayisi()));
        profilSoruSayisi.setText(String.valueOf(kullaniciGetir.getSoruSayisi()));
        GlideApp.with(getActivity()).load(kullaniciGetir.getProfilFoto()).apply(new RequestOptions().centerCrop()).into(profilRoundedImage);
    }
    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getActivity(),"Profil Görüntülenme Başarısız",Toast.LENGTH_SHORT);
    }
    public void setTabLayout(Fragment sorularim, Fragment begendiklerim,Fragment cevaplarim){
        viewPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.AddFragment(sorularim,"Sorularım");
        viewPagerAdapter.AddFragment(begendiklerim,"Beğenilerim");
        viewPagerAdapter.AddFragment(cevaplarim,"Cevaplarım");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_content_search_settings:
                startActivity(new Intent(getActivity().getApplicationContext(), ProfilDuzenleActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
        }
    }
}
