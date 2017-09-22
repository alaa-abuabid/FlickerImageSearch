package com.example.alaa_ab.flickerimagesearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alaa_ab on 9/20/2017.
 */

public class JsonParser
{
    public static Images[] ParseImages(String json)
    {
        Images[] photos = null;

        try {
            JSONObject JsonObject = new JSONObject(json);
            JSONObject Json_photos = JsonObject.getJSONObject("photos");
            JSONArray JsonArray_photo = Json_photos.getJSONArray("photo");

            photos = new Images[JsonArray_photo.length()];
            for (int i = 0; i < JsonArray_photo.length(); i++)
            {
                Images image = new Images();
                JSONObject flickrPhoto = JsonArray_photo.getJSONObject(i);
                image.setId(flickrPhoto.getString("id"));
                image.setTitle(flickrPhoto.getString("title"));
                image.setOwner(flickrPhoto.getString("owner"));
                image.setSecret(flickrPhoto.getString("secret"));
                image.setServer(flickrPhoto.getString("server"));
                image.setFarm(flickrPhoto.getString("farm"));
                photos[i] = image;
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return photos;
    }

    public static String parseDescription(String json)
    {
        String str = "";
        try
        {
            JSONObject JsonObject = new JSONObject(json);
            JSONObject JsonPhoto = JsonObject.getJSONObject("photo");
            JSONObject JsonDescription = JsonPhoto.getJSONObject("description");
            str= JsonDescription.getString("_content");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }
}
