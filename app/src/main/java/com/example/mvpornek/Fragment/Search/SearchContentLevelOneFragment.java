package com.example.mvpornek.Fragment.Search;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Fragment.SearchTabFragment.SearchQuestionFragment;
import com.example.mvpornek.Fragment.SearchTabFragment.SearchUsersFragment;
import com.example.mvpornek.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchContentLevelOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchContentLevelOneFragment extends Fragment implements View.OnClickListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerSearchContentLevelOneAdapter viewPagerSearchContentLevelOneAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SearchView searchViewOneFragment = null;

    public SearchContentLevelOneFragment() {
        // Required empty public constructor
    }


    public static SearchContentLevelOneFragment newInstance(String param1, String param2) {
        SearchContentLevelOneFragment fragment = new SearchContentLevelOneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onClick(View view)
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_content_level_one, container, false);
        tabLayout=(TabLayout) view.findViewById(R.id.searchContentLevelOneTabLayout);
        viewPager=(ViewPager) view.findViewById(R.id.searchContentLevelOneViewPager);
        viewPagerSearchContentLevelOneAdapter=new ViewPagerSearchContentLevelOneAdapter(getActivity().getSupportFragmentManager());

        viewPagerSearchContentLevelOneAdapter.AddFragment(new SearchQuestionFragment(),"Sorularım");
        viewPagerSearchContentLevelOneAdapter.AddFragment(new SearchUsersFragment(),"Kullanıcılar");

        viewPager.setAdapter(viewPagerSearchContentLevelOneAdapter);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }


}
