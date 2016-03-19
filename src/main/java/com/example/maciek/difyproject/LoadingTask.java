package com.example.maciek.difyproject;


import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class LoadingTask extends AsyncTask<String, Integer, Boolean>
{
    LinearLayout layout;
    ListView listView;
    ArrayAdapter<String> adapter;

    LoadingTask(LinearLayout layout, ListView listView, ArrayAdapter<String> adapter)
    {
        this.layout = layout;
        this.adapter = adapter;
        this.listView = listView;
    }


    @Override
    protected void onPreExecute()
    {
        layout.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        layout.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        super.onPostExecute(result);
    }

    @Override
    protected Boolean doInBackground(String... params)
    {
        try
        {
            Thread.sleep(2000);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
