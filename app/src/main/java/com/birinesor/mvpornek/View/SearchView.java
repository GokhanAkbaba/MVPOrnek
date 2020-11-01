package com.birinesor.mvpornek.View;

import java.util.List;

public interface SearchView {

    void close();
    void clearSuggestions();
    void showSuggestions(final List<String> sugges);
}
