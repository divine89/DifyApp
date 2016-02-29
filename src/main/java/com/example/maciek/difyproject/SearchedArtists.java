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

public class SearchedArtists extends AppCompatActivity
{
    private String genreFromIntent;
    private String countryFromIntent;
    private String cityFromIntent;

    List<String> lstring = new ArrayList<>();
    SearchedArtists activity;
    ListView modeList;
    ArrayAdapter<String> modeAdapter;
    List<String> tablica;
    ArrayAdapter<String> adapter;
    ListView listView;
    SearchedArtists searchedArtists;
    UrlBuilder urlBuilder;

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
        return getCountryFromIntent();
    }

    public void setCountryFromIntent(String countryFromIntent)
    {
        this.countryFromIntent = countryFromIntent;
    }

    public String getCityFromIntent()
    {
        return getCityFromIntent();
    }

    public void setCityFromIntent(String cityFromIntent)
    {
        this.cityFromIntent = cityFromIntent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_artists);

        searchedArtists = SearchedArtists.this;
        urlBuilder = new UrlBuilder();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        setGenreFromIntent(extras.getString("GENRE"));

        try
        {
            String genre = getGenreFromIntent();
            genre = URLEncoder.encode(genre, "UTF-8");
            setGenreFromIntent(genre);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        setCountryFromIntent(extras.getString("COUNTRY"));

        setCityFromIntent(extras.getString("CITY"));

        urlBuilder.createSearchAnArtistUrl();

        if(getGenreFromIntent() != null)
        {
            urlBuilder.addGenreToUrl(getGenreFromIntent());
            setGenreFromIntent(null);
        }

        if(getCountryFromIntent() != null)
        {
            urlBuilder.addCountryToUrl(getCountryFromIntent());
            setCountryFromIntent(null);
        }

        if(getCityFromIntent() != null)
        {
            urlBuilder.addCityToUrl(getCityFromIntent());
            setCityFromIntent(null);
        }

        listView = (ListView) searchedArtists.findViewById(R.id.listView2);

        if(tablica == null)
        {
            tablica = new ArrayList<String>();
        }
        else
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
