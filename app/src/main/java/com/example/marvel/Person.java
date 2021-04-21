package com.example.marvel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity (tableName = "Person")
public class Person implements Serializable {


    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id")
    private long id;

    @SerializedName("name")
    @ColumnInfo (name = "name")
    private String name;

    @SerializedName("realname")
    @ColumnInfo (name = "realName")
    private String realName;

    @SerializedName("team")
    @ColumnInfo (name = "team")
    private String team;

    @SerializedName("firstappearance")
    @ColumnInfo (name = "firstAppearance")
    private String firstAppearance;

    @SerializedName("createdby")
    @ColumnInfo (name = "createdBy")
    private String createdBy;

    @SerializedName("publisher")
    @ColumnInfo (name = "publisher")
    private String publisher;

    @SerializedName("imageurl")
    @ColumnInfo (name = "imageURL")
    private String imageURL;

    @SerializedName("bio")
    @ColumnInfo (name = "bio")
    private String bio;

    @SerializedName("IMDB")
    @ColumnInfo (name = "imdb")
    private String imdb;

    @SerializedName("rottenTomatoes")
    @ColumnInfo (name = "rottenTomatoes")
    private String rottenTomatto;

    @SerializedName("youtubeURL")
    @ColumnInfo (name = "youtubeURL")
    private String youtubeURL;


    public Person(String name, String team) {
        this.name = name;
        this.team = team;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getRottenTomatto() {
        return rottenTomatto;
    }

    public void setRottenTomatto(String rottenTomatto) {
        this.rottenTomatto = rottenTomatto;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }

}
