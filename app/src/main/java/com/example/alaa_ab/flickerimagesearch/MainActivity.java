package com.example.alaa_ab.flickerimagesearch;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        openSearchFragment();

    }


    private void openSearchFragment()
    {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        SearchFragment searchFragment = new SearchFragment();
        transaction.replace(R.id.fragment_container, searchFragment,"SEARCH_FRAGMENT");
        transaction.commit();
    }
}
