package com.example.marvel.utils;

import android.app.Activity;
import android.content.Context;

import com.example.marvel.models.Person;
import com.example.marvel.database.Connections;
import com.example.marvel.interfaces.AsyncTaskCallback;

import java.util.List;

public class PersonInsertAsync implements Runnable {
    private Context context;
    private AsyncTaskCallback callback;
    private List<Person> persons;
    private  Activity activity;

    public PersonInsertAsync(List<Person> persons, Context context, Activity activity, AsyncTaskCallback callback)
    {
        this.context = context;
        this.callback = callback;
        this.persons = persons;
        this.activity = activity;
    }

    @Override
    public void run() {

        List<Person> list = this.persons;
        try
        {
            Connections.getInstance(context).getDatabase().getPersonDAO().deleteAll();
            Connections.getInstance(context).getDatabase().getPersonDAO().insertAll(this.persons);

            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callback.handleResponse(list);
                }
            });

        }
        catch (Exception e)
        {
            callback.handleFault(e);
        }
    }
}
