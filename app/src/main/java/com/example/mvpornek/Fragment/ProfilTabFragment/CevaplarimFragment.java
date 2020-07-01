package com.example.mvpornek.Fragment.ProfilTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.R;

import java.util.ArrayList;
import java.util.List;


public class CevaplarimFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";


    private int mParam1;

    QuestionAdapterActivity questionAdapterActivity;

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
        cevaplarimRecyclerView=(RecyclerView) view.findViewById(R.id.profilCevaplarimRecyclerView);
        cevaplarimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cevaplarimRecyclerView.setAdapter(questionAdapterActivity);
        return view;
    }
}
