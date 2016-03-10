package com.example.maciek.difyproject;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewBuilder
{
    // < FIELDS
    private ListView listView;

    private List<String> listString;

    private ArrayAdapter<String> arrayAdapter;

    private int listViewLayout;

    public void setListView(ListView listView)
    {
        this.listView = listView;
    }

    public ListView getListView()
    {
        return listView;
    }

    public void setListString(List<String> listString)
    {
        this.listString = listString;
    }

    public List<String> getListString()
    {
        return listString;
    }

    public void setArrayAdapter(ArrayAdapter<String> arrayAdapter)
    {
        this.arrayAdapter = arrayAdapter;
    }

    public ArrayAdapter<String> getArrayAdapter()
    {
        return arrayAdapter;
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
}
