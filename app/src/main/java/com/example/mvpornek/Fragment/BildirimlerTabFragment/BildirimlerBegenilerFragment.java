package com.example.mvpornek.Fragment.BildirimlerTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvpornek.Adapter.BildirimlerBegenilerAdapter;
import com.example.mvpornek.Adapter.BildirimlerCevaplarAdapter;
import com.example.mvpornek.Models.BildirimlerBegenilerModel;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Presenter.BildirimlerBegeniler.BildirimlerBegenilerPresenter;
import com.example.mvpornek.Presenter.BildirimlerBegeniler.BildirimlerBegenilerPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.BildirimlerBegenilerView;

import java.util.List;


public class BildirimlerBegenilerFragment extends Fragment implements BildirimlerBegenilerView {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private List<BildirimlerBegenilerModel> bildirimlerBegenilerModelList;
    private RecyclerView bildirimlerBegenilerRecycView;
    private TextView bildirimlerBegenilerTxt;
    private SwipeRefreshLayout bildirimlerBegenilerSwipeRefreshLayout;
    private BildirimlerBegenilerAdapter bildirimlerBegenilerAdapter;
    private BildirimlerBegenilerAdapter.ItemClickListener itemClickListener;

    private BildirimlerBegenilerPresenter bildirimlerBegenilerPresenter;

    Kullanici kullanici;

    public BildirimlerBegenilerFragment() {
        // Required empty public constructor
    }

    public static BildirimlerBegenilerFragment newInstance(String param1, String param2) {
        BildirimlerBegenilerFragment fragment = new BildirimlerBegenilerFragment();
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
        View view=inflater.inflate(R.layout.fragment_bildirimler_begeniler, container, false);
        bildirimlerBegenilerRecycView=view.findViewById(R.id.bildirimlerBegenilerRecycView);
        bildirimlerBegenilerTxt=view.findViewById(R.id.bildirimlerBegenilerTxt);
        bildirimlerBegenilerSwipeRefreshLayout=view.findViewById(R.id.bildirimlerBegenilerSwipeRefreshLayout);

        bildirimlerBegenilerPresenter=new BildirimlerBegenilerPresenterImpl(this);

        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();

        bildirimlerBegenilerPresenter.loadData(kullanici.getId());

        bildirimlerBegenilerRecycView.setAdapter(bildirimlerBegenilerAdapter);
        bildirimlerBegenilerRecycView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bildirimlerBegenilerSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bildirimlerBegenilerPresenter.loadData(kullanici.getId());
            }
        });

        itemClickListener =((vw,position)-> {

        });
        return view;
    }

    @Override
    public void onBildirimBegenilerGetResult(List<BildirimlerBegenilerModel> data) {
        bildirimlerBegenilerAdapter=new BildirimlerBegenilerAdapter(data,getActivity(),itemClickListener);
        bildirimlerBegenilerAdapter.notifyDataSetChanged();
        bildirimlerBegenilerRecycView.setAdapter(bildirimlerBegenilerAdapter);
        bildirimlerBegenilerModelList=data;
        bildirimlerBegenilerTxt.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBildirimBegenilerErrorLoading(String message) {
        Log.d("Bildirim Begeniler",message);
    }

    @Override
    public void onGetBildirimBegenilerKontrol() {
        bildirimlerBegenilerTxt.setVisibility(View.VISIBLE);
    }

    @Override
    public void bildirimBegenilerShowLoading() {
        if (bildirimlerBegenilerSwipeRefreshLayout != null) {
            bildirimlerBegenilerSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void bildirimBegenilerHideLoading() {
        bildirimlerBegenilerSwipeRefreshLayout.setRefreshing(false);
    }
}