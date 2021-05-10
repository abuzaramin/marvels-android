package com.example.marvel.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.marvel.models.Person;
import com.example.marvel.interfaces.PersonDAO;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {

        public abstract PersonDAO getPersonDAO ();
}
