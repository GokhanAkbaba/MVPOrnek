package com.example.mvpornek.Fragment.SearchTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvpornek.Adapter.SearchAdapterQuestion;
import com.example.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.example.mvpornek.Models.SearchQuestionModel;
import com.example.mvpornek.Presenter.QuestionSearchFragmentPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.View.QuestionSearchFragmentView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchQuestionFragment extends Fragment  implements View.OnClickListener, QuestionSearchFragmentView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private RecyclerView searchQuestionFragmentRecyclerView;
    SearchAdapterQuestion searchAdapterQuestion;
    SearchAdapterQuestion.ItemClickListener itemClickListener;
    List<SearchQuestionModel> searchQuestionModels;

    QuestionSearchFragmentPresenterImpl questionSearchFragmentPresenter;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView controlTxt;



    private String mParam1;

    public SearchQuestionFragment() {
        // Required empty public constructor
    }

    public static SearchQuestionFragment newInstance(String param1) {
        SearchQuestionFragment fragment = new SearchQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_question, container, false);
        controlTxt=view.findViewById(R.id.searchQuestionTxt);
        searchQuestionFragmentRecyclerView=view.findViewById(R.id.searchQuestionRecycView);
        questionSearchFragmentPresenter=new QuestionSearchFragmentPresenterImpl(this);
        questionSearchFragmentPresenter.loadData(mParam1);
        searchQuestionFragmentRecyclerView.setAdapter(searchAdapterQuestion);
        searchQuestionFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout=view.findViewById(R.id.aramaSwipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.uygulamaMavisi));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                questionSearchFragmentPresenter.loadData(mParam1);
            }
        });
        itemClickListener =((vw,position)-> {
            int soruId=searchQuestionModels.get(position).getSoru_id();
            int soruSoranKullaniciID=searchQuestionModels.get(position).getKullanici_id();
            showBottomSheet(soruId,soruSoranKullaniciID);
        });

        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onGetResult(List<SearchQuestionModel> data) {
        searchAdapterQuestion=new SearchAdapterQuestion(data,getActivity(),itemClickListener);
        searchAdapterQuestion.notifyDataSetChanged();
        searchQuestionFragmentRecyclerView.setAdapter(searchAdapterQuestion);
        searchQuestionModels=data;
        controlTxt.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onErrorLoading(String message) {
        System.out.println("Bağlantı Hatası(SearchQuestionFragment) "+message);

    }

    @Override
    public void showLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }

    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResultControl() {
        controlTxt.setVisibility(View.VISIBLE);
    }

    public void showBottomSheet(int soruId,int soruSoranKullaniciID) {
        CommentBottomDialogFragment commentBottomDialogFragment =
                CommentBottomDialogFragment.newInstance(soruId,soruSoranKullaniciID);
        commentBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                CommentBottomDialogFragment.TAG);
    }
}
