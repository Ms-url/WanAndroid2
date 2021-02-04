package com.example.wanandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    HomePageFragment homePageFragment = new HomePageFragment();
    KnowledgeHierarchyFragment knowledgeHierarchyFragment = new KnowledgeHierarchyFragment();
    PublicSquareFragment publicSquareFragment = new PublicSquareFragment();
    private ImageButton imageButton_home_home;
    private ImageButton imageButton_knowledge_hierarchy;
    private ImageButton imageButton_public_square;
    private ImageButton imageButton_myself_space;
    private TextView home;
    private TextView system;
    private TextView place;
    private TextView my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        imageButton_home_home = findViewById(R.id.home_home);
        imageButton_knowledge_hierarchy = findViewById(R.id.knowledge_hierarchy);
        imageButton_public_square = findViewById(R.id.public_square);
        imageButton_myself_space = findViewById(R.id.myself_space);
        my=findViewById(R.id.my_bt);
        home=findViewById(R.id.home_bt);
        system=findViewById(R.id.system_bt);
        place=findViewById(R.id.place_bt);

        imageButton_home_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }

                imageButton_home_home.setImageResource(R.mipmap.homepage1_p);
                imageButton_knowledge_hierarchy.setImageResource(R.mipmap.system);
                imageButton_public_square.setImageResource(R.mipmap.square2);
                imageButton_myself_space.setImageResource(R.mipmap.myself2);
                home.setTextColor(Color.parseColor("#5bdeaa"));
                system.setTextColor(Color.parseColor("#7A7A7A"));
                my.setTextColor(Color.parseColor("#7A7A7A"));
                place.setTextColor(Color.parseColor("#7A7A7A"));

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

                imageButton_home_home.setImageResource(R.mipmap.homepage1);
                imageButton_knowledge_hierarchy.setImageResource(R.mipmap.system_p);
                imageButton_public_square.setImageResource(R.mipmap.square2);
                imageButton_myself_space.setImageResource(R.mipmap.myself2);
                home.setTextColor(Color.parseColor("#7A7A7A"));
                system.setTextColor(Color.parseColor("#5bdeaa"));
                my.setTextColor(Color.parseColor("#7A7A7A"));
                place.setTextColor(Color.parseColor("#7A7A7A"));

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

                imageButton_home_home.setImageResource(R.mipmap.homepage1);
                imageButton_knowledge_hierarchy.setImageResource(R.mipmap.system);
                imageButton_public_square.setImageResource(R.mipmap.square2_p);
                imageButton_myself_space.setImageResource(R.mipmap.myself2);
                home.setTextColor(Color.parseColor("#7A7A7A"));
                system.setTextColor(Color.parseColor("#7A7A7A"));
                my.setTextColor(Color.parseColor("#7A7A7A"));
                place.setTextColor(Color.parseColor("#5bdeaa"));

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.home_page_v, publicSquareFragment);
                transaction.commit();
            }
        });
        imageButton_myself_space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }

                imageButton_home_home.setImageResource(R.mipmap.homepage1);
                imageButton_knowledge_hierarchy.setImageResource(R.mipmap.system);
                imageButton_public_square.setImageResource(R.mipmap.square2);
                imageButton_myself_space.setImageResource(R.mipmap.myself2_p);
                home.setTextColor(Color.parseColor("#7A7A7A"));
                system.setTextColor(Color.parseColor("#7A7A7A"));
                my.setTextColor(Color.parseColor("#5bdeaa"));
                place.setTextColor(Color.parseColor("#7A7A7A"));

            }
        });


        imageButton_home_home.callOnClick();

    }
}