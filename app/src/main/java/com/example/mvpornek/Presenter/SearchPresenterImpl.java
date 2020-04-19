package com.example.mvpornek.Presenter;

import android.se.omapi.SEService;

import com.example.mvpornek.Model.ModelGiris.SearchInteractor;
import com.example.mvpornek.Model.ModelGiris.SearchSuggestions;
import com.example.mvpornek.View.SearchView;

import java.util.List;

public class SearchPresenterImpl implements SearchPresenter {
    private SearchView searchView;
    private SearchInteractor searchInteractor;
    private SearchSuggestions searchSuggestions;
    @Override
    public void updateSearch(String query) {
        if (query.isEmpty()) {
            searchView.clearSuggestions();
        } else {
            List<String> suggestions = searchSuggestions.getSuggestions(query, 25);
            searchView.showSuggestions(suggestions);
        }

    }

    @Override
    public void submitSearch(String query) {
        searchInteractor.submitSearch(query);
        searchView.close();
    }

    @Override
    public void close() {
        searchView.close();
    }
}
