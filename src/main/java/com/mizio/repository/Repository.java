package com.mizio.repository;

public interface Repository {

    <T> void saveOrUpdateObject(T object);
    <T> T getObject(Class<T> tClass, int ID);
    <T> boolean deleteObject(Class<T> tClass, int ID);
}
