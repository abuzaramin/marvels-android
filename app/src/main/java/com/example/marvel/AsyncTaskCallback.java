package com.example.marvel;

public interface AsyncTaskCallback <T>
{
    void handleResponse(T object);
    void handleFault(Exception e);
}
