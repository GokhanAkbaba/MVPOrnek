package com.birinesor.mvpornek.Fragment.SearchTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.birinesor.mvpornek.Adapter.AdapterSearchUsers;
import com.birinesor.mvpornek.Presenter.SearchUser.SearchUserPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.Response.UserSearchListResponse;
import com.birinesor.mvpornek.View.SearchUser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchUsersFragment extends Fragment implements SearchUser,View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private String mParam1;
    private int mParam2;
    private int mParam3;
    private RecyclerView searchUserFragmentRecyclerView;
    List<UserSearchListResponse> userSearchListResponseList;
    AdapterSearchUsers adapterSearchUsers;
    AdapterSearchUsers.ItemClickListener itemClickListener;
    SearchUserPresenterImpl searchUserPresenter;
    TextView controlText;

    public SearchUsersFragment() {
        // Required empty public constructor
    }

    public static SearchUsersFragment newInstance(String param1, int param2, int param3) {
        SearchUsersFragment fragment = new SearchUsersFragment();
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
        View view=inflater.inflate(R.layout.fragment_search_users, container, false);
        controlText=view.findViewById(R.id.searchUsersControlTxt);
        searchUserFragmentRecyclerView=view.findViewById(R.id.searchUserFragmentRecyclerView);
        searchUserPresenter= new SearchUserPresenterImpl(this);
        if(mParam3 == -1){
            searchUserPresenter.loadData(mParam1,mParam2,-1);
        }else{
            searchUserPresenter.loadData(mParam1,mParam2,1);
        }

        searchUserFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onGetResult(List<UserSearchListResponse> data) {
        adapterSearchUsers=new AdapterSearchUsers(data,getActivity(),itemClickListener);
        adapterSearchUsers.notifyDataSetChanged();
        searchUserFragmentRecyclerView.setAdapter(adapterSearchUsers);
        userSearchListResponseList=data;
        controlText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onErrorLoading(String message) {
        System.out.println("Bağlantı Hatası(SearchUsersFragment) "+message);
    }

    @Override
    public void onGetResultControl() {
        controlText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {

    }
}
