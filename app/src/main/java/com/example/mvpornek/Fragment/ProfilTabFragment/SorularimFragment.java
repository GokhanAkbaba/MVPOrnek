package com.example.mvpornek.Fragment.ProfilTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.AdapterProfilQuestion.AdapterProfilQuestion;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.Presenter.ProfilQuestionPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.ProfilQuestionView;

import java.util.ArrayList;
import java.util.List;


public class SorularimFragment extends Fragment implements ProfilQuestionView,View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";


    private RecyclerView profilSorularimRecyclerView;
    List<QuestionModel> questionModels;
    AdapterProfilQuestion adapterProfilQuestion;
    AdapterProfilQuestion.ItemClickListener itemClickListener;
    AdapterProfilQuestion.ItemClickListener itemLongClickListener;
    ProfilQuestionPresenterImpl profilQuestionPresenter;
    Kullanici kullanici;

    private int mParam1;


    public SorularimFragment() {

    }


    public static SorularimFragment newInstance(int param1) {
        SorularimFragment fragment = new SorularimFragment();
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
        View view = inflater.inflate(R.layout.fragment_sorularim, container, false);
        profilSorularimRecyclerView=view.findViewById(R.id.profilSorularimRecyclerView);
        profilQuestionPresenter=new ProfilQuestionPresenterImpl(this);
        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        profilQuestionPresenter.loadData(mParam1);
        profilSorularimRecyclerView=view.findViewById(R.id.profilSorularimRecyclerView);
        profilSorularimRecyclerView.setAdapter(adapterProfilQuestion);
        profilSorularimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profilSorularimRecyclerView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onGetResult(List<QuestionModel> data) {
        adapterProfilQuestion= new AdapterProfilQuestion(data,getActivity(),itemClickListener,itemLongClickListener);
        adapterProfilQuestion.notifyDataSetChanged();
        profilSorularimRecyclerView.setAdapter(adapterProfilQuestion);
        questionModels=data;
    }

    @Override
    public void onErrorLoading(String message) {
        System.out.println("Bağlantı Hatası(Sorularım Fragment) "+message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
