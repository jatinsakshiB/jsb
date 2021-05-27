package com.jsb.jsblibrary.Activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.jsb.jsblibrary.R;

import java.util.List;

public class ListSelect extends AppCompatActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_select);
        activity = ListSelect.this;


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        setActionBar(toolbar);

        List<String> myList = (List<String>) getIntent().getSerializableExtra("list");
        String title = (String) getIntent().getSerializableExtra("title");

        toolbar.setTitle(title);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }



}