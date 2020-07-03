package com.example.mvpornek.Fragment.ProfilTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Adapter.AdapterProfilAnswers;
import com.example.mvpornek.Adapter.AdapterProfilQuestion;
import com.example.mvpornek.Models.AnswersModel;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Presenter.ProfilCevaplarim.ProfilAnswersPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.ProfilAnswersView;

import java.util.List;


public class CevaplarimFragment extends Fragment implements ProfilAnswersView {

    private static final String ARG_PARAM1 = "param1";


    private int mParam1;
    private RecyclerView profilCevaplarimRecyclerView;
    List<AnswersModel> answersModels;
    AdapterProfilAnswers adapterProfilAnswers;
    AdapterProfilAnswers.ItemClickListener itemClickListener;
    ProfilAnswersPresenterImpl profilAnswersPresenter;
    Kullanici kullanici;

    private RecyclerView cevaplarimRecyclerView;



    public CevaplarimFragment() {

    }

    public static CevaplarimFragment newInstance(int param1) {
        CevaplarimFragment fragment = new CevaplarimFragment();
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
        View view = inflater.inflate(R.layout.fragment_cevaplarim, container, false);
        profilAnswersPresenter=new ProfilAnswersPresenterImpl(this);
        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        profilAnswersPresenter.loadData(mParam1);
        cevaplarimRecyclerView=view.findViewById(R.id.profilCevaplarimRecyclerView);
        cevaplarimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cevaplarimRecyclerView.setAdapter(adapterProfilAnswers);
        return view;
    }

    @Override
    public void onGetResult(List<AnswersModel> data) {
        adapterProfilAnswers= new AdapterProfilAnswers(data,getActivity(),itemClickListener);
        adapterProfilAnswers.notifyDataSetChanged();
        cevaplarimRecyclerView.setAdapter(adapterProfilAnswers);
        answersModels=data;
    }

    @Override
    public void onErrorLoading() {
        System.out.println("Bağlantı Hatası(Sorularım Fragment)");
    }
}
