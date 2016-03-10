package com.example.maciek.difyproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
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

//jakis komenatzr

public class MainActivity extends AppCompatActivity
{
    List<String> lstring;
    AlertDialog.Builder builder;
    ArrayAdapter<String> modeAdapter;
    ListView modeList;
    EditText inputSearch;
    String tempo;
    private String genre;
    private String city;
    private String country;

    LayoutInflater layoutInflater;

    MainActivity activity;

    private Context mainActivityContext;

    private MainActivity mainActivity;

    private android.support.v7.app.ActionBar myActionBar;

    private final String myActionBarTitle = "Dify";

    private Activity myCurrentActivity;

    private Dialog myDialog;

    Dialog dial;
    Dialog dialog;

    private String buttonTitle;

    // < BUTTONS > //
    private Button genreButton;
    public Button countryButton;
    public Button cityButton;
    private Button searchButton;
    // </ BUTTONS > //

    // < EDITTEXT >
    private EditText myEditText;
    // < CONSTRUCTOR > //
    public MainActivity()
    {
    }
    // </ CONSTRUCTOR > //

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public void setButtonTitle(String buttonTitle)
    {
        this.buttonTitle = buttonTitle;
    }

    public String getButtonTitle()
    {
        return buttonTitle;
    }

    public void setMyEditText(EditText myEditText)
    {
        this.myEditText = myEditText;
    }

    public EditText getMyEditText()
    {
        return myEditText;
    }

    public void setGenreButton(Button genreButton)
    {
        this.genreButton = genreButton;
    }

    public Button getGenreButton()
    {
        return genreButton;
    }

    public void setCountryButton(Button countryButton)
    {
        this.countryButton = countryButton;
    }

    public Button getCountryButton()
    {
        return countryButton;
    }

    public void setCityButton(Button cityButton)
    {
        this.cityButton = cityButton;
    }

    public Button getCityButton()
    {
        return cityButton;
    }

    public void setSearchButton(Button searchButton)
    {
        this.searchButton = searchButton;
    }

    public Button getSearchButton()
    {
        return searchButton;
    }

    public Dialog getMyDialog()
    {
        return myDialog;
    }

    public void setMyDialog(Dialog myDialogialog)
    {
        this.myDialog = myDialog;
    }

    public void setMyCurrentActivity(Activity currentActivity)
    {
        this.myCurrentActivity = currentActivity;
    }

    public Activity getMyCurrentActivity()
    {
        return myCurrentActivity;
    }

    public void setMainActivityActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity()
    {
        return mainActivity;
    }

    public Context getMainActivityContext()
    {
        return mainActivityContext;
    }

    public void setMainActivityContext()
    {
        this.mainActivityContext = getApplicationContext();
    }

    public void setMyActionBar()
    {
        this.myActionBar = getSupportActionBar();
    }

    public android.support.v7.app.ActionBar getMyActionBar()
    {
        return myActionBar;
    }

    public void configureMyActionBar(android.support.v7.app.ActionBar myActionBar)
    {
        myActionBar.setTitle(getMyActionBarTitle());

    }

    public String getMyActionBarTitle()
    {
        return myActionBarTitle;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMyActionBar();

        android.support.v7.app.ActionBar myActionBar = getMyActionBar();

        configureMyActionBar(myActionBar);

        setMyActionBar();

        setMainActivityContext();

        Context mainActivityContext = getMainActivityContext();

        activity = MainActivity.this;

        final Dialog dialog = new Dialog(mainActivityContext);

        setMyDialog(dialog);

        Button genreButton = (Button) findViewById(R.id.genreButton);
        setGenreButton(genreButton);

        final Button countryButton = (Button) this.findViewById(R.id.countryButton);
        setCountryButton(countryButton);

        final Button cityButton = (Button) findViewById(R.id.cityButton);
        setCityButton(cityButton);

        Button searchButton = (Button) findViewById(R.id.searchButton);
        setSearchButton(searchButton);

        EditText editText = (EditText) findViewById(R.id.editTextDialog);
        setMyEditText(editText);

        genreButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //getGenres();
               // prepareDialog();
                //showDialog(getGenreButton());
                DialogBuilder dialogBuilder = new DialogBuilder();

                Dialog dialog = dialogBuilder.createDialogWithListView(MainActivity.this);

                dialogBuilder.setDialog(dialog);

                dialogBuilder.showMyDialog();
            }
        });

        countryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogBuilder dialogBuilder = new DialogBuilder();

                Activity activity = getMyCurrentActivity();

                Button countryButton = getCountryButton();

                Dialog dialog = dialogBuilder.createDialogWithEditText(MainActivity.this, activity, v, countryButton); //TODO poprawić ustawianie tytułu button po nacisnieciu OK w dialogu

                dialogBuilder.setDialog(dialog);

                dialogBuilder.showMyDialog();

                String title = countryButton.getText().toString();

                setCountry(title);
            }
        });

        cityButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogBuilder dialogBuilder = new DialogBuilder();

                Activity activity = getMyCurrentActivity();

                Button cityButton = getCityButton();

                Dialog dialog = dialogBuilder.createDialogWithEditText(MainActivity.this, activity, v, cityButton); //TODO poprawić ustawianie tytułu button po nacisnieciu OK w dialogu

                dialogBuilder.setDialog(dialog);

                dialogBuilder.showMyDialog();

                String title = cityButton.getText().toString();

                setCity(title);
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
                intent.putExtra("COUNTRY", getCountry());
                intent.putExtra("CITY", getCity());
                intent.putExtra("GENRE", getGenre());
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }

    /*public void setButtonsTitle(View v)
    {
        String title = getButtonTitle();

        switch (v.getId())
        {
            case R.id.countryButton:

                Button countryButton = getCountryButton();

                countryButton.setText(title);

                break;

            case R.id.cityButton:

                Button cityButton = getCityButton();

                cityButton.setText(title);

                break;
        }
    }*/

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
                setGenre(temp.getText().toString());
                myButton.setText(getGenre());
                dialog.dismiss();
            }
        });
    }

    public void getGenres(final ListViewBuilder listViewBuilder)
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
                                List<String> listString = listViewBuilder.getListString();
                                listString.add(genre);
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
