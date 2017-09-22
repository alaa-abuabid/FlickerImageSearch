package com.example.alaa_ab.flickerimagesearch;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class HttpRequestHandler extends IntentService {

    public static final String ACTION ="com.example.alaa_ab.flickerimagesearch.HttpRequest";



    Adapter adapter ;
    String text;
    int loadCount ;
    Images [] images ;
    ArrayList<Images> fullImages ;

    public HttpRequestHandler()
    {
        super("HttpRequestHandler");
        loadCount =  0;
        fullImages = new ArrayList<>();
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        text="";
        adapter=null;
        adapter = (Adapter) intent.getExtras().getSerializable("adapter");
        text = intent.getExtras().getString("text");
        FlickrAPI api = FlickrAPI.getInstance();
        try
        {
            String json = api.getJson(text,false);
            images =   JsonParser.ParseImages(json);
            //lazy load loop
            while (loadCount<images.length)
            {
                Images[] temp = api.getBitmapImage(getbatch());
                fullImages.addAll(Arrays.asList(temp));
                adapter.setImagesArrayList(fullImages);
                Intent intentValue = new Intent(ACTION);
                intentValue.putExtra("update","update");
                intentValue.putExtra("list",temp);
                LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intentValue);

            }


        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }



    private Images [] getbatch()
    {
        int toIndex = loadCount + 10 ;
        if(toIndex > images.length )
        {
            toIndex = images.length-1;
        }
        Images[] cutArray = Arrays.copyOfRange(images , loadCount , toIndex);
        loadCount = toIndex ;
        return cutArray;
    }

}
