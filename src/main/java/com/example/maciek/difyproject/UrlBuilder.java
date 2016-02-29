package com.example.maciek.difyproject;

import android.util.Log;

public class UrlBuilder
{
    private String HTTP = "http://";
    private String HTTPS = "https://";
    private String ECHONEST = "developer.echonest.com/api/v4/";
    private String ARTIST = "artist/";
    private String SEARCH = "search?";
    private String API_KEY_VALUE = "SXZNUZ73HVWJ7T5V1";
    private String API_KEY = "api_key=";
    private String BUCKET = "bucket=";
    private String AND = "&";
    private String FORMAT_JSON = "format=json";
    private String RESULT = "results=";
    private String GENRE = "genre=";
    private String ARTIST_LOCATION = "artist_location=";
    private String COUNTRY = "country:";
    private String CITY = "city:";
    private String URL;

    private int RESULT_1 = 1;
    private int RESULT_15 = 15;
    private int RESULT_30 = 30;
    private int RESULT_50 = 50;
    private int RESULT_100 = 100;



    public int getResult1()
    {
        return RESULT_1;
    }

    public int getResult15()
    {
        return RESULT_15;
    }

    public int getResult30()
    {
        return RESULT_30;
    }

    public int getResult50()
    {
        return RESULT_50;
    }

    public int getResult100()
    {
        return RESULT_100;
    }

    public String getUrl()
    {
        return URL;
    }

    public void setUrl(String url)
    {
        this.URL = url;
    }

    public String getHttpString()
    {
        return HTTP;
    }

    public String getEchonestString()
    {
        return ECHONEST;
    }

    public String getArtistString()
    {
        return ARTIST;
    }

    public String getApiKeyValueString()
    {
        return API_KEY_VALUE;
    }

    public String getAPiKeyString()
    {
        return API_KEY;
    }

    public String getSearchString()
    {
        return SEARCH;
    }

    public String getAndString()
    {
        return AND;
    }

    public String getBucketString()
    {
        return BUCKET;
    }

    public String getFormatJsonString()
    {
        return FORMAT_JSON;
    }

    public String getResultString()
    {
        return RESULT;
    }

    public String getGenreString()
    {
        return GENRE;
    }

    public String getArtistLocationString()
    {
        return ARTIST_LOCATION;
    }

    public String getCountryString()
    {
        return COUNTRY;
    }

    public String getCityString()
    {
        return CITY;
    }

    public void createSearchAnArtistUrl()
    {
        String url;
        url = getHttpString();
        url = url + getEchonestString();
        url = url + getArtistString();
        url = url + getSearchString();
        url = url + getAPiKeyString();
        url = url + getApiKeyValueString();
        url = url + getAndString();
        url = url + getFormatJsonString();
        url = url + getAndString();
        url = url + getResultString();
        url = url + getResult100();

        LogBuilder logBuilder = new LogBuilder();
        UrlBuilder urlBuilder = new UrlBuilder();
        logBuilder.createDebugLog(191, urlBuilder, "createArtistSearchUrl", "URL: " + url);
        logBuilder = null;
        urlBuilder = null;

        setUrl(url);
    }

    public void addGenreToUrl(String genre)
    {
        if (getUrl() != null)
        {
            String url;
            url = getUrl();
            url = url + getAndString();
            url = url + getGenreString();
            url = url + genre;

            LogBuilder logBuilder = new LogBuilder();
            UrlBuilder urlBuilder = new UrlBuilder();
            logBuilder.createDebugLog(191, urlBuilder, "addGenreToUrl", "URL: " + url);
            logBuilder = null;
            urlBuilder = null;

            setUrl(url);
        }
    }

    public void addCountryToUrl(String country)
    {
        if (getUrl() != null)
        {
            String url;
            url = getUrl();
            url = url + getAndString();
            url = url + getArtistLocationString();
            url = url + getCountryString();
            url = url + country;

            LogBuilder logBuilder = new LogBuilder();
            UrlBuilder urlBuilder = new UrlBuilder();
            logBuilder.createDebugLog(191, urlBuilder, "addCountryToUrl", "URL: " + url);
            logBuilder = null;
            urlBuilder = null;

            setUrl(url);
        }
    }

    public void addCityToUrl(String city)
    {
        if (getUrl() != null)
        {
            String url;
            url = getUrl();
            url = url + getAndString();
            url = url + getArtistLocationString();
            url = url + getCityString();
            url = url + city;

            LogBuilder logBuilder = new LogBuilder();
            UrlBuilder urlBuilder = new UrlBuilder();
            logBuilder.createDebugLog(191, urlBuilder, "addCityToUrl", "URL: " + url);
            logBuilder = null;
            urlBuilder = null;

            setUrl(url);
        }
    }



}
