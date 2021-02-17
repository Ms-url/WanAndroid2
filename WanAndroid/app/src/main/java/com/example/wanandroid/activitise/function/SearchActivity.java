package com.example.wanandroid.activitise.function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wanandroid.R;
import com.example.wanandroid.fragment.search.SearchForHotWordFragment;
import com.example.wanandroid.fragment.search.SearchResultFragment1;
import com.example.wanandroid.fragment.search.SearchResultFragment2;

public class SearchActivity extends AppCompatActivity {

    SearchForHotWordFragment searchForHotWordFragment = new SearchForHotWordFragment();
    SearchResultFragment1 searchResultFragment = new SearchResultFragment1();
    SearchResultFragment2 searchResultFragment2 = new SearchResultFragment2();
    private Button button;
    private EditText editText;
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        button = findViewById(R.id.search_bt);
        editText = findViewById(R.id.search_s);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.search_f, searchForHotWordFragment);
        transaction.commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("k", String.valueOf(editText.getText()));
                searchResultFragment.setArguments(bundle);
                searchResultFragment2.setArguments(bundle);

                if (aBoolean) {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.search_f, searchResultFragment);
                    transaction.commit();
                    aBoolean = false;
                } else {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.search_f, searchResultFragment2);
                    transaction.commit();
                    aBoolean = true;
                }
            }
        });

    }

    public void Transaction (String name){
        Bundle bundle = new Bundle();
        bundle.putString("k", name);
        searchResultFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.search_f, searchResultFragment);
        transaction.commit();
    }
}