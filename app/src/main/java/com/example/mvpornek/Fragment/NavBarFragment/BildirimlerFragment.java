package com.example.mvpornek.Fragment.NavBarFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Adapter.NotificationAdapter;
import com.example.mvpornek.Fragment.ProfilTabFragment.BegendiklerimFragment;
import com.example.mvpornek.Models.NotificationModel;
import com.example.mvpornek.R;

import java.util.ArrayList;
import java.util.List;

public class BildirimlerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerViewBildirimler;
    List<NotificationModel> notificationModelList =new ArrayList<>();
    NotificationAdapter notificationAdapter;
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

        notificationModelList.add(new NotificationModel("Gökhan Akbaba","3 haftalık izine çıktım. Akdeniz tarafında fiyat performans bakımından güzel oteller hangi illerde.","size bir soru sordu.","5dk",R.drawable.man));
        notificationModelList.add(new NotificationModel("Aykut Erdal","Şahinbey Belediyesinin arkasındaki lokanta güzel.","sorunuza cevap verdi","3dk",R.drawable.man1));
        notificationAdapter=new NotificationAdapter(notificationModelList);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bildirimler_sayfasi, container, false);

        recyclerViewBildirimler=(RecyclerView) view.findViewById(R.id.bildirimleRecyclerView);
        recyclerViewBildirimler.setAdapter(notificationAdapter);
        recyclerViewBildirimler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


}
