package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked{

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Person> marvels;
    ProgressDialog progressDoalog;
    PersonDAO personDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        marvels = new ArrayList<Person>();
        personDAO = Connections.getInstance(getApplicationContext()).getDatabase().getPersonDAO();

        myAdapter = new PersonAdapter(this, marvels);
        recyclerView.setAdapter(myAdapter);
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = NetworkClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<Person>> call = service.getAllData();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
               generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS", MODE_PRIVATE).edit();
            editor.putBoolean("isLogin", false);
            editor.apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setMenuItems () {

    }

    @Override
    public void onItemClicked(int index) {

        Intent detailIntent = new Intent(MainActivity.this, com.example.marvel.DetailsActivity.class);
        detailIntent.putExtra("id", marvels.get(index).getId());
        startActivity(detailIntent);
    }

    @Override
    public void onPlayIconClicked(int index) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marvels.get(index).getYoutubeURL()));
        startActivity(intent);
    }


    private void generateDataList(List<Person> data) {

        marvels.clear();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new InsertPersonAsync(data, getApplicationContext(),this, new AsyncTaskCallback() {
                @Override
                public void handleResponse(Object object) {
                    progressDoalog.dismiss();
                    List <Person> list = personDAO.getAllPersons();
                    marvels.addAll(list);
                    myAdapter.notifyDataSetChanged();
                }
                @Override
                public void handleFault(Exception e) {

                    List <Person> list  = personDAO.getAllPersons();
                    if (list != null &&  list.size() > 0) {

                        marvels.addAll(list);
                        myAdapter.notifyDataSetChanged();
                    }
                }
        }));
    }
}