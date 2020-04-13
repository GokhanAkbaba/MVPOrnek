package com.example.mvpornek.Fragment.NavBarFragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Activity.Adapter.NotificationAdapter;
import com.example.mvpornek.Fragment.ProfilTabFragment.BegendiklerimFragment;
import com.example.mvpornek.R;

public class BildirimlerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerViewBildirimler;

    String kullanici_isimleri[], bildirim_aciklama[], bildirim_neden[], bildirim_zaman[];
    int kullaniciResmi[] ={R.drawable.man,R.drawable.man1,R.drawable.ceo};








    private String mParam1;
    private String mParam2;

    public BildirimlerFragment() {

    }


    public static BegendiklerimFragment newInstance(String param1, String param2) {
        BegendiklerimFragment fragment = new BegendiklerimFragment();
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

        kullanici_isimleri=getResources().getStringArray(R.array.kullanici_isimleri);
        bildirim_aciklama=getResources().getStringArray(R.array.bildirim_aciklama);
        bildirim_neden=getResources().getStringArray(R.array.bildirim_neden);
        bildirim_zaman=getResources().getStringArray(R.array.bildirim_zaman);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bildirimler_sayfasi, container, false);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.bildirimlerTxt));

        recyclerViewBildirimler=(RecyclerView) view.findViewById(R.id.bildirimleRecyclerView);
        NotificationAdapter notificationAdapter=new NotificationAdapter(kullanici_isimleri,bildirim_aciklama,bildirim_neden,bildirim_zaman,kullaniciResmi,getActivity());
        recyclerViewBildirimler.setAdapter(notificationAdapter);
        recyclerViewBildirimler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
