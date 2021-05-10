package com.example.marvel.interfaces;

public interface AsyncTaskCallback <T>
{
    void handleResponse(T object);
    void handleFault(Exception e);
}
