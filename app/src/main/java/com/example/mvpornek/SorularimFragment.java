package com.example.mvpornek;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Activity.QuestionAdapterActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SorularimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SorularimFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;

    String kullanici_isimleri[], yorum_sayisi[], sorular[], etiket[];
    int kullaniciResmi[] ={R.drawable.man,R.drawable.man1,R.drawable.ceo};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SorularimFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SorularimFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SorularimFragment newInstance(String param1, String param2) {
        SorularimFragment fragment = new SorularimFragment();
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
        yorum_sayisi=getResources().getStringArray(R.array.yorum_sayisi);
        sorular=getResources().getStringArray(R.array.sorular);
        etiket=getResources().getStringArray(R.array.etiket);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sorularim, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.profilRecyclerView);
        QuestionAdapterActivity questionAdapterActivity=new QuestionAdapterActivity(getActivity(),kullanici_isimleri,yorum_sayisi,sorular,etiket,kullaniciResmi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(questionAdapterActivity);
        return view;
    }
}
