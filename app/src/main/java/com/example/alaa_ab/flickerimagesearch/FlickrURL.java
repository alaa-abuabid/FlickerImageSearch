package com.example.alaa_ab.flickerimagesearch;



public class FlickrURL
{
    private static String KEY = "7963380d6a041bc51f0997c3fc5740c4";
    private static String SECRET = "0e55b47895b65f6f";
    private static String PHOTO_ID = "photo_id=";
    private static String API_KEY = "api_key=";
    private static String PER_PAGE = "per_page=";
    private static String TAGS = "tags=";
    private  static String GET_IMAGES_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=&tags=&per_page=&format=json&nojsoncallback=1";
    private  static  String GET_IMAGE_INFO_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=&photo_id=&format=json&nojsoncallback=1";

    public static String getImageUrl(int perPage , String tag)
    {
        String tempUrl = GET_IMAGES_URL;
        tempUrl = tempUrl.replace(PER_PAGE , PER_PAGE + String.valueOf(perPage));
        tempUrl = tempUrl.replace(API_KEY , API_KEY + KEY);
        tempUrl = tempUrl.replace(TAGS,TAGS + tag);
        return tempUrl ;
    }

    public static  String getImageInfoUrl(String photoId)
    {
        String tempUrl = GET_IMAGE_INFO_URL;
        tempUrl = tempUrl.replace(PHOTO_ID , PHOTO_ID+photoId);
        tempUrl = tempUrl.replace(API_KEY , API_KEY + KEY);
        return tempUrl;
    }
}
