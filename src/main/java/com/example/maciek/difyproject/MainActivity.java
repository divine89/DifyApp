package com.example.maciek.difyproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    List<String> lstring;
    AlertDialog.Builder builder;
    Dialog dialog;
    ArrayAdapter<String> modeAdapter;
    ListView modeList;
    EditText inputSearch;
    Button genreButton;
    Button countryButton;
    Button cityButton;
    Button searchButton;
    EditText editText;
    String tempo;
    String genre;
    String city;
    String country;
    Dialog dial;
    MainActivity activity;
    LayoutInflater layoutInflater;
    android.support.v7.app.ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this; //TODO improve declaration

        //lstring.add(str);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Dify");

        dial = new Dialog(MainActivity.this);
        genreButton = (Button) findViewById(R.id.button2);
        countryButton = (Button) findViewById(R.id.button3);
        cityButton = (Button) findViewById(R.id.button4);
        searchButton = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editTextDialog);

        genreButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getGenres();
                prepareDialog();
                showDialog(genreButton);
                Log.d("DEBUG", "genre:" + genre);
            }
        });

        countryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("DEBUG", "String before iniliaze coutry:" + tempo);
                dialogWithEditTextOnly(countryButton, v);
                Log.d("DEBUG", "String after initialize country:" + country);
            }
        });

        cityButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("DEBUG", "String before iniliaze city:" + tempo);
                dialogWithEditTextOnly(cityButton, v);
                Log.d("DEBUG", "String after initialize city:" + city);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(activity, SearchedArtists.class);
                intent.setClass(MainActivity.this, SearchedArtists.class);
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra("COUNTRY", country);
                intent.putExtra("CITY", city);
                intent.putExtra("GENRE", genre);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }

    public void dialogWithEditTextOnly(final Button tempButton, final View v)
    {
        final AlertDialog.Builder cityDialog = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        final LayoutInflater cityLayout = activity.getLayoutInflater();
        final View dialogview = cityLayout.inflate(R.layout.dialog_edit_text, null);
        cityDialog.setView(dialogview);
        cityDialog.setTitle("Select");
        tempo = null;

        cityDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                EditText editText = (EditText) dial.findViewById(R.id.editTextDialog);

                switch (v.getId())
                {
                    case R.id.button3:
                        country = editText.getText().toString();
                        tempButton.setText(country);
                        break;

                    case R.id.button4:
                        city = editText.getText().toString();
                        tempButton.setText(city);
                        break;
                }
            }
        });

        cityDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });

        dial = cityDialog.create();
        dial.show();
    }

    public void prepareDialog()
    {
        builder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);

        layoutInflater = activity.getLayoutInflater();

        final View dialogView = layoutInflater.inflate(R.layout.list_view, null);

        builder.setView(dialogView);
        builder.setTitle("Genres");

        modeList = (ListView) dialogView.findViewById(R.id.listView);

        ColorDrawable sage = new ColorDrawable(ContextCompat.getColor(activity, R.color.colorDivider));
        modeList.setDivider(sage);
        modeList.setDividerHeight(1);

        if(lstring == null)
        {
            lstring = new ArrayList<>();
        }
        else
        {
            lstring.clear();
        }

        modeAdapter = new ArrayAdapter<String>(activity, R.layout.list_view_custom, lstring);

        modeList.setAdapter(modeAdapter);

        dialogView.setBackgroundColor(Color.parseColor("#181818"));

        dialog = builder.create();

        inputSearch = (EditText) dialogView.findViewById(R.id.editText2);

    }

    public void showDialog(final Button myButton)
    {

        dialog.show();

        inputSearch.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3)
            {
                // When user changed the Text
                //Main3Activity.this.arraylist.getFilter().filter(cs);
                activity.modeAdapter.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3)
            {
                // TODO Auto-generated method stub

            }

        });

        modeList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                TextView temp = (TextView) view;
                // Toast.makeText(activity, temp.getText(), Toast.LENGTH_SHORT).show();
                genre = temp.getText().toString();
                myButton.setText(genre);
                dialog.dismiss();
            }
        });
    }

    void getGenres()
    {
        String url = "http://developer.echonest.com/api/v4/genre/list?api_key=SXZNUZ73HVWJ7T5V1&format=json";

        RequestQueue rQ = Volley.newRequestQueue(this);

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONObject j = response.getJSONObject("response");
                            JSONArray jsonArray = j.getJSONArray("genres");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String genre = object.getString("name");
                                lstring.add(genre);
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
                        // TODO Auto-generated method stub

                    }
                });

        rQ.add(jsObjRequest);
    }
}
