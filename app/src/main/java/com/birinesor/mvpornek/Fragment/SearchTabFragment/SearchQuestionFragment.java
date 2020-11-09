package com.birinesor.mvpornek.Fragment.SearchTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.birinesor.mvpornek.Adapter.SearchAdapterQuestion;
import com.birinesor.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.birinesor.mvpornek.Models.SearchQuestionModel;
import com.birinesor.mvpornek.Presenter.QuestionSearchFragmentPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.View.QuestionSearchFragmentView;

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
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private String mParam1;
    private int mParam2;
    private int mParam3;

    private RecyclerView searchQuestionFragmentRecyclerView;
    SearchAdapterQuestion searchAdapterQuestion;
    SearchAdapterQuestion.ItemClickListener itemClickListener;
    List<SearchQuestionModel> searchQuestionModels;

    QuestionSearchFragmentPresenterImpl questionSearchFragmentPresenter;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView controlTxt;




    public SearchQuestionFragment() {
        // Required empty public constructor
    }

    public static SearchQuestionFragment newInstance(String param1, int param2, int param3) {
        SearchQuestionFragment fragment = new SearchQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2,param2);
        args.putInt(ARG_PARAM3,param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_question, container, false);
        controlTxt=view.findViewById(R.id.searchQuestionTxt);
        searchQuestionFragmentRecyclerView=view.findViewById(R.id.searchQuestionRecycView);
        questionSearchFragmentPresenter=new QuestionSearchFragmentPresenterImpl(this);
        if(mParam3 == -1){
            questionSearchFragmentPresenter.loadData(mParam1,mParam2,-1);
        }else{
            questionSearchFragmentPresenter.loadData(mParam1,mParam2,1);
        }

        searchQuestionFragmentRecyclerView.setAdapter(searchAdapterQuestion);
        searchQuestionFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout=view.findViewById(R.id.aramaSwipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.uygulamaMavisi));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(mParam3 == -1){
                    questionSearchFragmentPresenter.loadData(mParam1,mParam2,-1);
                }else{
                    questionSearchFragmentPresenter.loadData(mParam1,mParam2,1);
                }
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
