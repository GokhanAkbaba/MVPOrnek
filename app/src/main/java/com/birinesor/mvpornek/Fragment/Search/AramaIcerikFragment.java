package com.birinesor.mvpornek.Fragment.Search;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Adapter.SearchAdapter;
import com.birinesor.mvpornek.Fragment.KullaniciIcerikFragment;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Presenter.AramaArsivKayit.AramaArsivKayitPresenterImpl;
import com.birinesor.mvpornek.Presenter.AramaGecmisSilPresenterImpl;
import com.birinesor.mvpornek.Presenter.SearchUsersPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.Response.SearchListResponse;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.Presenter.AramaArsivKayit.AramaArsivKayitView;
import com.birinesor.mvpornek.View.AramaGecmisSilView;
import com.birinesor.mvpornek.View.SearchUsersView;

import java.util.List;

public class AramaIcerikFragment extends Fragment implements View.OnClickListener, AramaGecmisSilView, SearchUsersView, AramaArsivKayitView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SearchView searchView;
    List<SearchListResponse> searchListResponseList;
    Button geriButon;
    SearchUsersPresenterImpl searchUsersPresenter;
    AramaArsivKayitPresenterImpl aramaArsivKayitPresenter;
    AramaGecmisSilPresenterImpl aramaGecmisSilPresenter;
    SearchAdapter.ItemClickListener itemClickListener;
    RecyclerView aramaRecyclerView;
    SearchAdapter searchAdapter;

    private String mParam1;
    private String mParam2;

    public static AramaIcerikFragment newInstance() {
        AramaIcerikFragment fragment = new AramaIcerikFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getActivity().findViewById(R.id.anasayfa_nav_view).setVisibility(View.GONE);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.arama_sayfasi_icerik, container, false);
        Kullanici kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        searchView=(SearchView) view.findViewById(R.id.searchView_page);
        aramaRecyclerView=view.findViewById(R.id.aramaRecyclerView);
        aramaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        aramaRecyclerView.setAdapter(searchAdapter);
        searchUsersPresenter=new SearchUsersPresenterImpl(this);
        aramaArsivKayitPresenter=new AramaArsivKayitPresenterImpl(this);
        aramaGecmisSilPresenter=new AramaGecmisSilPresenterImpl(this);
        geriButon=(Button) view.findViewById(R.id.aramaSayfasiIcerikGeriBtn);
        geriButon.setOnClickListener(this);
        searchView.onActionViewExpanded();
        EditText searchEditText = (EditText) searchView.findViewById(R.id.search_src_text);
        searchEditText.setTextSize(14);
        searchUsersPresenter.loadData(kullanici.getId(),"newText",-1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                KullaniciIcerikFragment kullaniciIcerikFragment=KullaniciIcerikFragment.newInstance(query,-1,1);
                ((HomeActivity)getActivity()).loadFragment(kullaniciIcerikFragment,"AramaAsama-4");
                aramaArsivKayitPresenter.createAramaArsivKayit(kullanici.getId(),query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText!= null ) {
                    if(newText.isEmpty()){
                        searchUsersPresenter.loadData(kullanici.getId(),newText,-1);
                    }
                    else{
                        searchUsersPresenter.loadData(kullanici.getId(),newText,1);
                    }
                }
                return false;
            }
        });

        itemClickListener =((vw,position)-> {
            String icerik=searchListResponseList.get(position).getKullanici_adi();
          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Birine Sor");
            builder.setMessage(icerik+" arama geçmişini silmek istiyor musunuz?");
            builder.setNegativeButton("Hayır", null);
            builder.setOnDismissListener(dialogInterface -> {
                searchUsersPresenter.loadData(kullanici.getId(),"",-1);
            });
            builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    aramaGecmisSilPresenter.deleteAramaGEcmis(searchListResponseList.get(position).getKullaniciId());
                }
            });
            builder.show();
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.aramaSayfasiIcerikGeriBtn:
                getActivity().getSupportFragmentManager().popBackStack();
            break;
        }
    }

    @Override
    public void onGetResult(List<SearchListResponse> data) {
        searchAdapter=new SearchAdapter(data,getActivity(),itemClickListener);
        searchAdapter.notifyDataSetChanged();
        aramaRecyclerView.setAdapter(searchAdapter);
        searchListResponseList=data;

    }

    @Override
    public void onErrorLoading(String message) {
        System.out.println("AramaIcerikFragment"+message);
    }
    @Override
    public void showAramaArsivKayitSuccesMessage() {
        System.out.println("AramaIcerikFragment AramaArsivKayitSucces");
    }

    @Override
    public void showAramaArsivKayitFailedMessage() {
        System.out.println("AramaIcerikFragment AramaArsivKayitFailed");
    }

    @Override
    public void showAramaGecmisSuccesMessage() {
        System.out.println("AramaIcerikFragment AramaKayitSilSucces");
    }

    @Override
    public void showAramaGecmisFailedMessage() {
        System.out.println("AramaIcerikFragment AramaKayitSilFailed");
    }
}
