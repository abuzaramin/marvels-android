package com.example.marvel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView bio;
    PersonDAO personDAO;
    TextView teamName ;
    TextView realName;
    TextView firstAppearance;
    TextView createdBy;
    TextView publisher;
    TextView imdb;
    TextView rottenTomato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView = findViewById(R.id.detailImage);
        bio = findViewById(R.id.detailTextView);
        teamName = findViewById(R.id.teamName);
        realName = findViewById(R.id.realName);
        firstAppearance = findViewById(R.id.firstAppearance);
        createdBy = findViewById(R.id.createdBy);
        publisher = findViewById(R.id.publisher);
        imdb = findViewById(R.id.imdbRating);
        rottenTomato = findViewById(R.id.rottenTomatoes);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        personDAO = Connections.getInstance(getApplicationContext()).getDatabase().getPersonDAO();
        long id =  getIntent().getLongExtra("id", 0);
        Person marvel = personDAO.getPersonById(id);
        toolBarLayout.setTitle(marvel.getName());
        setViews(marvel);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marvel.getYoutubeURL()));
                startActivity(intent);
            }
        });

    }

    void setViews (Person marvel) {
        this.setTitle(marvel.getName());
        teamName.setText(marvel.getTeam());
        Picasso.with(this)
                .load(marvel.getImageURL()).into(imageView);
        bio.              setText(Html.fromHtml(marvel.getBio()));
        realName.         setText(Html.fromHtml("<b>Real Name</b>\t\t\t\t\t\t\t\t\t" + marvel.getRealName()));
        teamName.         setText(Html.fromHtml("<b>Team</b>\t\t\t\t\t\t\t\t\t\t\t\t\t" + marvel.getTeam()));
        firstAppearance.  setText(Html.fromHtml("<b>First Appearance</b>\t\t\t" + marvel.getFirstAppearance()));
        createdBy.        setText(Html.fromHtml("<b>Created By</b>\t\t\t\t\t\t\t\t\t" + marvel.getCreatedBy()));
        publisher.        setText(Html.fromHtml("<b>Publisher</b>\t\t\t\t\t\t\t\t\t\t" + marvel.getPublisher()));
        imdb.             setText(Html.fromHtml("<b>IMDB Ratings</b>\t\t\t\t\t\t" + marvel.getImdb()));
        rottenTomato.     setText(Html.fromHtml("<b>Rotten Tomatoes</b>\t\t\t" + marvel.getRottenTomatto()));
    }

}