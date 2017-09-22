package com.example.alaa_ab.flickerimagesearch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class DescriptionFragment extends Fragment
{
    ImageView image ;
    TextView title , description ;
    Images imageObject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        imageObject = (Images) getArguments().getSerializable("image");
        Intent intent = new Intent(getContext(),HttpDescreption.class);
        intent.putExtra("id",imageObject.getId());
        getActivity().startService(intent);

    }

    @Override
    public void onStart()
    {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(HttpDescreption.ACTION);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver,intentFilter);

    }

    private BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String desc = intent.getStringExtra("desc");
            imageObject.setInfo(desc);
        }
    };
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.descreption,container,false);
        image = (ImageView) view.findViewById(R.id.image_desc);
        title = (TextView) view.findViewById(R.id.title_desc);
        description = (TextView) view.findViewById(R.id.desc_desc);
        image.setImageBitmap(imageObject.getBitmap());
        title.setText(imageObject.getTitle());
        description.setText(imageObject.getInfo());
        return view;
    }
}
