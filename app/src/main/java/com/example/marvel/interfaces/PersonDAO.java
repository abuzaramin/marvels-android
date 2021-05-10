package com.example.marvel.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.marvel.models.Person;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    void insert (Person person);

    @Delete
    void delete (Person person);

    @Query("Delete from Person")
    void deleteAll();

    @Insert
    void insertAll(List<Person> persons);

    @Query("DELETE FROM Person WHERE id = :id")
    void delete (long id);

    @Query ("Select * from Person")
    List<Person> getAllPersons();

    @Query ("Select * from Person where id = :id")
    Person getPersonById (long id);

}
