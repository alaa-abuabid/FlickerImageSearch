package com.example.alaa_ab.flickerimagesearch;

import android.graphics.BitmapFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class FlickrAPI
{
    private static FlickrAPI singleInstance;

    private FlickrAPI()
    {

    }
    // singleton
    public static FlickrAPI getInstance()
    {
        if(singleInstance == null)
        {
            singleInstance = new FlickrAPI();
        }
        return singleInstance;
    }


    public String getJson(String text , boolean flag ) throws IOException
    {
        String strUrl =FlickrURL.getImageUrl(200, text);
        if (flag)
        {
            strUrl = FlickrURL.getImageInfoUrl(text);
        }
        HttpsURLConnection connection = null;
        BufferedReader reader = null;
        URL url = new URL(strUrl);
        connection = (HttpsURLConnection) url.openConnection();
        connection.connect();


        InputStream stream = connection.getInputStream();

        reader = new BufferedReader(new InputStreamReader(stream));

        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = reader.readLine()) != null)
        {
            buffer.append(line + "\n");

        }
        reader.close();
        stream.close();
        connection.disconnect();


        return buffer.toString();
    }

    public Images[] getBitmapImage(Images [] photo)
    {
        for (Images i:photo)
        {
            String FlickrPhotoPath = "http://farm" + i.getFarm()+ ".static.flickr.com/" + i.getServer() + "/" + i.getId() + "_" + i.getSecret() + "_b.jpg";

            URL FlickrPhotoUrl = null;

            try {
                FlickrPhotoUrl = new URL(FlickrPhotoPath);

                HttpURLConnection httpConnection
                        = (HttpURLConnection) FlickrPhotoUrl.openConnection();
                httpConnection.setDoInput(true);
                httpConnection.connect();
                InputStream inputStream = httpConnection.getInputStream();
                i.setBitmap(BitmapFactory.decodeStream(inputStream));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return photo;
    }

}
