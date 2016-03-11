package com.example.maciek.difyproject;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewBuilder
{
    // < FIELDS
    private ListView listView;

    private List<String> listString;

    private ArrayAdapter<String> arrayAdapter;

    private String listViewItem;

    private int listViewLayout;

    public String getListViewItem()
    {
        return listViewItem;
    }

    public void setListViewItem(String listViewItem)
    {
        this.listViewItem = listViewItem;
    }

    public ListView getListView()
    {
        return listView;
    }

    public void setListView(ListView listView)
    {
        this.listView = listView;
    }

    public List<String> getListString()
    {
        return listString;
    }

    public void setListString(List<String> listString)
    {
        this.listString = listString;
    }

    public ArrayAdapter<String> getArrayAdapter()
    {
        return arrayAdapter;
    }

    public void setArrayAdapter(ArrayAdapter<String> arrayAdapter)
    {
        this.arrayAdapter = arrayAdapter;
    }

    public void configureListView(View dialogView, int idListView)
    {
        ListView listView = (ListView) dialogView.findViewById(idListView);

        setListView(listView);
    }

    public void configureListStrings()
    {
        List<String> listString = new ArrayList<>();

        setListString(listString);
    }

    public void configureArrayAdapter(ListView listView, Context context, List<String> listString)
    {
        int listViewLayout = R.layout.list_view_custom;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, listViewLayout, listString);

        setArrayAdapter(arrayAdapter);

        listView.setAdapter(arrayAdapter);
    }

    public void searchFilter(EditText textFilter)
    {
        textFilter.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3)
            {
                ListViewBuilder.this.getArrayAdapter().getFilter().filter(cs);
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
    }

    public void getClickedListViewItem(ListViewBuilder listViewBuilder, final Dialog dialog, final Button button)
    {
        ListView listView = listViewBuilder.getListView();

        Log.d("DEBUG", "ListView ref" + listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                TextView temp = (TextView) view;
                // Toast.makeText(activity, temp.getText(), Toast.LENGTH_SHORT).show();
                String string = temp.getText().toString();

                Log.d("DEBUG", "string:" + string);

                button.setText(string);

                dialog.dismiss();
            }
        });

    }
}
