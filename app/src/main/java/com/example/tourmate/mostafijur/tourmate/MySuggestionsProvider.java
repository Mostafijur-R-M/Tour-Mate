package com.example.tourmate.mostafijur.tourmate;

import android.content.SearchRecentSuggestionsProvider;

import static android.content.SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES;

public class MySuggestionsProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.example.tourmate.mostafijur.tourmate.MySuggestionsProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public MySuggestionsProvider(){
        setupSuggestions(AUTHORITY, MODE);
    }
}
