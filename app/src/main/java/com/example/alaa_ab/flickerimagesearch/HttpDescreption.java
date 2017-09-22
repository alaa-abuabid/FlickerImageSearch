package com.example.alaa_ab.flickerimagesearch;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;


public class HttpDescreption extends IntentService
{
    public static final String ACTION ="com.example.alaa_ab.flickerimagesearch.HttpDescription";

    public HttpDescreption()
    {
        super("HttpDescreption");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        String id = intent.getStringExtra("id");
        FlickrAPI api = FlickrAPI.getInstance();
        String descJson = null;
        try {
            descJson = api.getJson(id,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String desc = JsonParser.parseDescription(descJson);
        Intent intentValue = new Intent(ACTION);
        intentValue.putExtra("desc",desc);
        LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intentValue);
    }


}
