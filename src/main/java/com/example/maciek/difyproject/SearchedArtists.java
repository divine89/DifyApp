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
    String genre;
    String country;
    String city;
    List<String> lstring = new ArrayList<>();
    SearchedArtists activity;
    ListView modeList;
    ArrayAdapter<String> modeAdapter;
    List<String> tablica;
    ArrayAdapter<String> adapter;
    ListView listView;
    SearchedArtists searchedArtists;
    UrlBuilder urlBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_artists);

        searchedArtists = SearchedArtists.this;
        urlBuilder = new UrlBuilder();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        genre = extras.getString("GENRE");
        try
        {
            genre = URLEncoder.encode(genre, "UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        city = extras.getString("CITY");
        country = extras.getString("COUNTRY");



        urlBuilder.createSearchAnArtistUrl();

        if(genre != null)
        {
            Log.d("DEBUG", "In genre null. Genre:" + genre);
            urlBuilder.addGenreToUrl(genre);
            genre = null;
        }

        if(country != null)
        {
            urlBuilder.addCountryToUrl(country);
            country = null;
        }

        if(city != null)
        {
            urlBuilder.addCityToUrl(city);
            city = null;
        }

        //Log.d("DEBUG", "URL:"+URL);

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
