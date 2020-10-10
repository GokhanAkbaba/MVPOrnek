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

import com.example.mvpornek.Adapter.BildirimlerCevaplarAdapter;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Presenter.BildirimlerCevaplar.BildirimlerCevaplarPresenter;
import com.example.mvpornek.Presenter.BildirimlerCevaplar.BildirimlerCevaplarPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.BildirimlerCevaplarModel;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.BildirimlerCevaplarView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BildirimlerCevaplarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BildirimlerCevaplarFragment extends Fragment implements BildirimlerCevaplarView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView bildirimlerSorularRecycView;
    private TextView bildirimlerSorularTxt;
    private SwipeRefreshLayout bildirimlerSorularSwipeRefreshLayout;

    private BildirimlerCevaplarPresenter bildirimlerCevaplarPresenter;
    private List<BildirimlerCevaplarModel> bildirimlerCevaplarModel;
    private BildirimlerCevaplarAdapter bildirimlerCevaplarAdapter;
    private BildirimlerCevaplarAdapter.ItemClickListener itemClickListener;

    Kullanici kullanici;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BildirimlerCevaplarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BildirimlerCevaplarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BildirimlerCevaplarFragment newInstance(String param1, String param2) {
        BildirimlerCevaplarFragment fragment = new BildirimlerCevaplarFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bildirimler_cevaplar, container, false);
        bildirimlerSorularRecycView=view.findViewById(R.id.bildirimlerSorularRecycView);
        bildirimlerSorularTxt=view.findViewById(R.id.bildirimlerSorularTxt);
        bildirimlerSorularSwipeRefreshLayout=view.findViewById(R.id.bildirimlerSorularSwipeRefreshLayout);

        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();

        bildirimlerCevaplarPresenter=new BildirimlerCevaplarPresenterImpl(this);
        bildirimlerCevaplarPresenter.loadData(kullanici.getId());

        bildirimlerSorularRecycView.setAdapter(bildirimlerCevaplarAdapter);
        bildirimlerSorularRecycView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bildirimlerSorularSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bildirimlerCevaplarPresenter.loadData(kullanici.getId());
            }
        });

        itemClickListener =((vw,position)-> {

        });


        return view;
    }

    @Override
    public void onGetResult(List<BildirimlerCevaplarModel> data) {
    bildirimlerCevaplarAdapter=new BildirimlerCevaplarAdapter(data,getActivity(),itemClickListener);
    bildirimlerCevaplarAdapter.notifyDataSetChanged();
    bildirimlerSorularRecycView.setAdapter(bildirimlerCevaplarAdapter);
    bildirimlerCevaplarModel=data;
    bildirimlerSorularTxt.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onErrorLoading(String message) {
        Log.d("Bildirim Cevaplar",message);
    }

    @Override
    public void onGetBildirimCevaplarKontrol() {
        bildirimlerSorularTxt.setVisibility(View.VISIBLE);
    }

    @Override
    public void bildirimCevaplarShowLoading() {
        if (bildirimlerSorularSwipeRefreshLayout != null) {
            bildirimlerSorularSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void bildirimCevaplarHideLoading() {
        bildirimlerSorularSwipeRefreshLayout.setRefreshing(false);
    }
}