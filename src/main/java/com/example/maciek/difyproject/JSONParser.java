package com.example.maciek.difyproject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class JSONParser
{
    private final String URL = "http://developer.echonest.com/api/v4/genre/list?api_key=SXZNUZ73HVWJ7T5V1&format=json";

    private RequestQueue requestQueue;

    private JsonObjectRequest jsonObjectRequest;

    public String getUrlStrng()
    {
        return URL;
    }

    public void setRequestQueue(RequestQueue requestQueue)
    {
        this.requestQueue = requestQueue;
    }

    public RequestQueue getRequestQueue()
    {
        return requestQueue;
    }

    public void setJsonObjectRequest(JsonObjectRequest jsonObjectRequest)
    {
        this.jsonObjectRequest = jsonObjectRequest;
    }

    public JsonObjectRequest getJsonObjectRequest()
    {
        return jsonObjectRequest;
    }

    public void getGenres(Context context, final ListViewBuilder listViewBuilder)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        setRequestQueue(requestQueue);

        String url = getUrlStrng();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest

                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            String responseString = "response";

                            JSONObject j = response.getJSONObject(responseString);

                            String genresString = "genres";

                            JSONArray jsonArray = j.getJSONArray(genresString);

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String nameString = "name";

                                String genre = object.getString(nameString);

                                List<String> listString = listViewBuilder.getListString();

                                listString.add(genre);
                            }
                        }
                        catch (JSONException e)
                        {

                        }
                    }
                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        // TODO Auto-generated method stub

                    }
                });

        setJsonObjectRequest(jsObjRequest);

        requestQueue.add(jsObjRequest);
    }

    public void getResults(Context context, final ListViewBuilder listViewBuilder, String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        setRequestQueue(requestQueue);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest

                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            String responseString = "response";

                            JSONObject j = response.getJSONObject(responseString);

                            String artistsString = "artists";

                            JSONArray jsonArray = j.getJSONArray(artistsString);

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String nameString = "name";

                                String genre = object.getString(nameString);

                                List<String> listString = listViewBuilder.getListString();

                                Log.d("DEBUG", "Names:" + genre);

                                listString.add(genre);
                            }
                        }
                        catch (JSONException e)
                        {

                        }
                    }
                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        // TODO Auto-generated method stub

                    }
                });

        setJsonObjectRequest(jsObjRequest);

        requestQueue.add(jsObjRequest);
    }
}
