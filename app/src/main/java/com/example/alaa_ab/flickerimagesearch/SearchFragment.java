package com.example.alaa_ab.flickerimagesearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SearchFragment extends Fragment
{
    Button searchButton;
    EditText searchText;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.search,container,false);
        searchButton = (Button) view.findViewById(R.id.searchButton);
        searchText = (EditText) view.findViewById(R.id.searchText);
        buttonOnclick();
        return view;
    }
    private void buttonOnclick()
    {
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String text = searchText.getText().toString();
                openImageFragment(text);
            }
        });
    }

    private void openImageFragment(String text)
    {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        ImagesFragment imagesFragment = new ImagesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text",text);
        imagesFragment.setArguments(bundle);
        transaction.replace(R.id.fragment_container, imagesFragment,"IMAGE_FRAGMENT");
        transaction.commit();
    }


}
