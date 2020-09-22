package com.example.mvpornek.Fragment.SearchTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvpornek.Adapter.AdapterSearchUsers;
import com.example.mvpornek.Presenter.SearchUser.SearchUserPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.UserSearchListResponse;
import com.example.mvpornek.View.SearchUser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchUsersFragment extends Fragment implements SearchUser,View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private RecyclerView searchUserFragmentRecyclerView;
    List<UserSearchListResponse> userSearchListResponseList;
    AdapterSearchUsers adapterSearchUsers;
    AdapterSearchUsers.ItemClickListener itemClickListener;
    SearchUserPresenterImpl searchUserPresenter;
    TextView controlText;

    public SearchUsersFragment() {
        // Required empty public constructor
    }

    public static SearchUsersFragment newInstance(String param1) {
        SearchUsersFragment fragment = new SearchUsersFragment();
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
        View view=inflater.inflate(R.layout.fragment_search_users, container, false);
        controlText=view.findViewById(R.id.searchUsersControlTxt);
        searchUserFragmentRecyclerView=view.findViewById(R.id.searchUserFragmentRecyclerView);
        searchUserPresenter= new SearchUserPresenterImpl(this);
        searchUserPresenter.loadData(mParam1);
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
