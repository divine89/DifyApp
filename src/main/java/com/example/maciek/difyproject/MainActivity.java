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
import android.util.Log;
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

        final Button genreButton = (Button) findViewById(R.id.genreButton);
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
                final DialogBuilder dialogBuilder = new DialogBuilder();

                JSONParser jsonParser = new JSONParser();

                ListViewBuilder listViewBuilder = new ListViewBuilder();

                jsonParser.getGenres(MainActivity.this, listViewBuilder);

                Dialog dialog = dialogBuilder.createDialogWithListView(MainActivity.this, listViewBuilder);

                dialogBuilder.setDialog(dialog);

                dialogBuilder.showMyDialog();

                EditText filter = (EditText) dialog.findViewById(R.id.editText2);

                listViewBuilder.searchFilter(filter);

                listViewBuilder.getClickedListViewItem(listViewBuilder, dialog, genreButton);
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

                //String title = cityButton.getText().toString();

                //setCity(title);
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

                String name = getTextFromSearchEditText();

                if(!name.equals(""))
                {
                    intent.putExtra("NAME", name);
                }

                putExtrasToIntent(intent, genreButton, "GENRE");
                putExtrasToIntent(intent, countryButton, "COUNTRY");
                putExtrasToIntent(intent, cityButton, "CITY");

                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }

    public void putExtrasToIntent(Intent intent, Button button, String key)
    {
        String title = button.getText().toString();

        if(!title.equals("Select"))
        {
            intent.putExtra(key, title);
        }
    }

    public String getTextFromSearchEditText()
    {
        EditText editText = (EditText) findViewById(R.id.editText);

        String text = editText.getText().toString();

        return text;
    }
}
