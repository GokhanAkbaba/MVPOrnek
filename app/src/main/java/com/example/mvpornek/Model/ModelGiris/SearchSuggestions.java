package com.example.mvpornek.Model.ModelGiris;

import java.util.List;

public interface SearchSuggestions {
    List<String> getSuggestions(final String search, int limit);
}
