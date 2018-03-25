package com.example.hp.booleans;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hp.booleans.FetchData.Data;
import com.example.hp.booleans.adapter.CustomRecyclerViewAdapter;
import com.example.hp.booleans.adapter.CustomViewPagerAdapter;
import com.example.hp.booleans.service.ClipboardService;

import java.util.ArrayList;

import static com.example.hp.booleans.database.DatabaseUtil.getEntertainmentList;
import static com.example.hp.booleans.database.DatabaseUtil.getSportsList;
import static com.example.hp.booleans.database.DatabaseUtil.getTrendingList;
import static com.example.hp.booleans.database.DatabaseUtil.getWorldList;
import static com.example.hp.booleans.database.NewsDatabaseHelper.initializeDatabase;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    RecyclerView entertainmentRecyclerView;
    RecyclerView worldRecyclerView;

    RecyclerView sportsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.mainAppBar));
        ((AppCompatActivity) this).getSupportActionBar().setTitle("HOME");
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        entertainmentRecyclerView = (RecyclerView) findViewById(R.id.entertainment_recyclerview);
        worldRecyclerView = (RecyclerView) findViewById(R.id.world_recyclerview);
        sportsRecyclerView = (RecyclerView) findViewById(R.id.sports_recyclerview);

        SharedPreferences preferences = getSharedPreferences("FirstRun", 0);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", false);


        if (!isFirstRun) {
            initializeDatabase(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstRun", true);
        }
        super.onCreate(savedInstanceState);
        ArrayList<Data> trendingList = getTrendingList(this);
        ArrayList<Data> entertainmentList = getEntertainmentList(this);
        ArrayList<Data> worldList = getWorldList(this);
        ArrayList<Data> sportsList = getSportsList(this);

        viewPager.setAdapter(new CustomViewPagerAdapter(this, trendingList));
        entertainmentRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        entertainmentRecyclerView.setAdapter(new CustomRecyclerViewAdapter(this, entertainmentList));
        worldRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        worldRecyclerView.setAdapter(new CustomRecyclerViewAdapter(this, worldList));
        sportsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        sportsRecyclerView.setAdapter(new CustomRecyclerViewAdapter(this, sportsList));
        startService();

    }

    public void startService() {
        startService(new Intent(getBaseContext(), ClipboardService.class));
    }
}
