package com.birinesor.mvpornek.Fragment.ProfilTabFragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.Adapter.AdapterProfilQuestion;
import com.birinesor.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.QuestionModel;
import com.birinesor.mvpornek.Presenter.ProfilQuestion.ProfilQuestionPresenterImpl;
import com.birinesor.mvpornek.Presenter.QuestionsDeletePresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.ProfilQuestionView;
import com.birinesor.mvpornek.View.QuestionsDeleteView;

import java.util.List;


public class SorularimFragment extends Fragment implements ProfilQuestionView,View.OnClickListener, QuestionsDeleteView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param1";


    private RecyclerView profilSorularimRecyclerView;
    List<QuestionModel> questionModels;
    AdapterProfilQuestion adapterProfilQuestion;
    AdapterProfilQuestion.ItemClickListener itemClickListener;
    AdapterProfilQuestion.ItemClickListener itemLongClickListener;
    ProfilQuestionPresenterImpl profilQuestionPresenter;
    QuestionsDeletePresenterImpl questionsDeletePresenter;
    Boolean checkSoruAlani=false;
    Kullanici kullanici;

    private int mParam1;

    TextView recyclerViewProfilSorularimText;


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
        questionsDeletePresenter=new QuestionsDeletePresenterImpl(this);
        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        profilQuestionPresenter.loadData(mParam1);
        recyclerViewProfilSorularimText=view.findViewById(R.id.recyclerViewProfilSorularimText);

        profilSorularimRecyclerView.setAdapter(adapterProfilQuestion);
        profilSorularimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profilSorularimRecyclerView.setOnClickListener(this);

        itemClickListener =((vw,position)-> {
            int soruId=questionModels.get(position).getId();
            int soruSoranKullaniciID=questionModels.get(position).getKullaniciId();
            showBottomSheet(soruId,soruSoranKullaniciID);
        });

        itemLongClickListener =((vw,position)-> {
            ConstraintLayout sorularIcerik=vw.findViewById(R.id.sorularIcerikLayout);
            if(kullanici.getId() == questionModels.get(position).getKullaniciId()) {
                if(checkSoruAlani == false) {
                    sorularIcerik.setBackgroundColor(getResources().getColor(R.color.colorGrayPrimay));
                    checkSoruAlani = true;
                }
                final CharSequence[] items = {"Sil"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        questionsDeletePresenter.deleteOptions(questionModels.get(position).getId());
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(checkSoruAlani == true) {
                            sorularIcerik.setBackgroundColor(getResources().getColor(R.color.white));
                            checkSoruAlani=false;
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return view;
    }
    public void showBottomSheet(int soruId,int soruSoranKullaniciID) {
        CommentBottomDialogFragment commentBottomDialogFragment =
                CommentBottomDialogFragment.newInstance(soruId,soruSoranKullaniciID);
        commentBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                CommentBottomDialogFragment.TAG);
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
    public void onGetResultControl() {
        recyclerViewProfilSorularimText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccesMessage() {
        Toast.makeText(getActivity(),"Sorunuz Silinidi.",Toast.LENGTH_LONG).show();
        profilQuestionPresenter.loadData(mParam1);
    }

    @Override
    public void showFailedMessage() {
        Toast.makeText(getActivity(),"Sorunuz Silinirken Bir Hata Oluştu",Toast.LENGTH_LONG).show();
        profilQuestionPresenter.loadData(mParam1);

    }
}
