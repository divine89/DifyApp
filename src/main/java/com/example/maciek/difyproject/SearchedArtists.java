package com.example.maciek.difyproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

//TODO pozmieniać gdzie trzeba zmienne na final

public class SearchedArtists extends AppCompatActivity
{

    //TODO odzielić składniki klasy od siebie komentarzami i ustawić na private
    List<String> tablica;
    ArrayAdapter<String> adapter;
    ListView listView;
    SearchedArtists searchedArtists;
    private String genreFromIntent;
    private String countryFromIntent;
    private String cityFromIntent;
    private Intent intent;
    private Bundle extrasBundle;
    private UrlBuilder urlBuilder;
    private String GENRE_KEY = "GENRE";
    private String COUNTRY_KEY = "COUNTRY";
    private String CITY_KEY = "CITY";

    //TODO odzielic rodzaje metod od siebie komentarzami. Wydzielic miejsce na konstruktor.

    public String getGenreKey()
    {
        return GENRE_KEY;
    }

    public void setGenreKey(String GENRE_KEY)
    {
        this.GENRE_KEY = GENRE_KEY;
    }

    public String getCountryKey()
    {
        return COUNTRY_KEY;
    }

    public void setCountryKey(String COUNTRY_KEY)
    {
        this.COUNTRY_KEY = COUNTRY_KEY;
    }

    public String getCityKey()
    {
        return CITY_KEY;
    }

    public void setCityKey(String CITY_KEY)
    {
        this.CITY_KEY = CITY_KEY;
    }

    public SearchedArtists()
    {
        //TODO czy w konstruktorze ma być budowany object SearchedArtists?
        setUrlBuilder(new UrlBuilder());
    }

    public Intent getMyIntent()
    {
        return intent;
    }

    public void setMyIntent(Intent intent)
    {
        this.intent = intent;
    }

    public Bundle getExtrasBundle()
    {
        return extrasBundle;
    }

    public void setExtrasBundle(Bundle bundle)
    {
        this.extrasBundle = bundle;
    }

    public String getGenreFromIntent()
    {
        return genreFromIntent;
    }

    public void setGenreFromIntent(String genreFromIntent)
    {
        this.genreFromIntent = genreFromIntent;
    }

    public String getCountryFromIntent()
    {
        return countryFromIntent;
    }

    public void setCountryFromIntent(String countryFromIntent)
    {
        this.countryFromIntent = countryFromIntent;
    }

    public String getCityFromIntent()
    {
        return cityFromIntent;
    }

    public void setCityFromIntent(String cityFromIntent)
    {
        this.cityFromIntent = cityFromIntent;
    }

    public UrlBuilder getUrlBuilder()
    {
        return urlBuilder;
    }

    public void setUrlBuilder(UrlBuilder urlBuilder)
    {
        this.urlBuilder = urlBuilder;
    }

    public String replaceWhiteSpaceFromString(String string)
    {
        try
        {
            string = URLEncoder.encode(string, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return string;
    }

    public String getExtrasFromIntent(Bundle bundle, String stringKey)
    {
        String stringFromIntent;

        stringFromIntent = bundle.getString(stringKey);

        return stringFromIntent;
    }

    public Boolean checkWhiteSpaces(final String string)
    {
        Boolean isWhiteSpace = false;

        if(string != null)
        {
            for(int i = 0; i < string.length(); i++)
            {
                if(string.contains(" "))
                {
                    isWhiteSpace = true;
                    return isWhiteSpace;
                }
                else
                {
                    isWhiteSpace = false;
                    return isWhiteSpace;
                }
            }
        }
        else
        {
            //TODO log that string is null
        }
        return isWhiteSpace;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_artists);

        searchedArtists = SearchedArtists.this;

        SearchedArtists searchedArtists2 = new SearchedArtists();

        //TODO zastanowić się nad konwencją. Czy robić zmienne lokalne czy używać tylko metod get();4
        //TODO dodac logi do metod

        Intent intent = getIntent();

        setMyIntent(intent);

        intent = getMyIntent();

        Bundle extras = intent.getExtras();

        setExtrasBundle(extras);

        extras = getExtrasBundle();

        setCityFromIntent(getExtrasFromIntent(extras, getCityKey()));

        setGenreFromIntent(getExtrasFromIntent(extras, getGenreKey()));

        setCountryFromIntent(getExtrasFromIntent(extras, getCountryKey()));

        String genreFromIntent = getGenreFromIntent();

        if(checkWhiteSpaces(genreFromIntent))
        {
            String genreWithoutSpace = replaceWhiteSpaceFromString(genreFromIntent);
            setGenreFromIntent(genreWithoutSpace);
        }

        String countryFromIntent = getCountryFromIntent();

        if(checkWhiteSpaces(countryFromIntent))
        {
            String countryWithoutSpace = replaceWhiteSpaceFromString(countryFromIntent);
            setCountryFromIntent(countryWithoutSpace);
        }

        String cityFromIntent = getCityFromIntent();

        if(checkWhiteSpaces(cityFromIntent))
        {
            String cityWithoutSpace = replaceWhiteSpaceFromString(cityFromIntent);
            setGenreFromIntent(cityWithoutSpace);
        }

        getUrlBuilder().createSearchAnArtistUrl();

        if (getGenreFromIntent() != null)
        {
            getUrlBuilder().addGenreToUrl(getGenreFromIntent());
            setGenreFromIntent(null);
        }

        if (getCountryFromIntent() != null)
        {
            getUrlBuilder().addCountryToUrl(getCountryFromIntent());
            setCountryFromIntent(null);
        }

        if (getCityFromIntent() != null)
        {
            getUrlBuilder().addCityToUrl(getCityFromIntent());
            setCityFromIntent(null);
        }

        listView = (ListView) searchedArtists.findViewById(R.id.listView2);

        if (tablica == null)
        {
            tablica = new ArrayList<String>();
        } else
        {
            tablica.clear();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_view_custom, tablica);
        listView.setAdapter(adapter);

        RequestQueue rQ = Volley.newRequestQueue(searchedArtists);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, urlBuilder.getUrl(), null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            //Log.d("DEBUG", "JsonObjectRequest in try");
                            JSONObject j = response.getJSONObject("response");
                            JSONArray jsonArray = j.getJSONArray("artists");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String artist = object.getString("name");
                                //Log.d("DEBUG", "artist"+artist);
                                tablica.add(artist);
                            }
                        } catch (JSONException e)
                        {

                        }
                    }
                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("DEBUG", "JSON onErrorRespone");

                    }
                });

        rQ.add(jsObjRequest);

    }


}
