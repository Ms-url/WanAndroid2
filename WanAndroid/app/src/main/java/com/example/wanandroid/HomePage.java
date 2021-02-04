package com.example.wanandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {
    HomePageFragment homePageFragment = new HomePageFragment();
    KnowledgeHierarchyFragment knowledgeHierarchyFragment = new KnowledgeHierarchyFragment();
    PublicSquareFragment publicSquareFragment = new PublicSquareFragment();
    private ImageButton imageButton_home_home;
    private ImageButton imageButton_knowledge_hierarchy;
    private ImageButton imageButton_public_square;
    private ImageButton imageButton_myself_space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        imageButton_home_home = findViewById(R.id.home_home);
        imageButton_knowledge_hierarchy = findViewById(R.id.knowledge_hierarchy);
        imageButton_public_square = findViewById(R.id.public_square);
        imageButton_myself_space = findViewById(R.id.myself_space);

        imageButton_home_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }

                //TODO按钮图片切换

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.home_page_v, homePageFragment);
                transaction.commit();
            }
        });
        imageButton_knowledge_hierarchy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }

                //TODO按钮图片切换

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.home_page_v, knowledgeHierarchyFragment);
                transaction.commit();
            }
        });
        imageButton_public_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar actionBar = getSupportActionBar();
                actionBar.show();

                //TODO按钮图片切换

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.home_page_v, publicSquareFragment);
                transaction.commit();
            }
        });
        imageButton_home_home.callOnClick();

    }
}