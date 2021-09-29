/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javafx.collections.ObservableList;

/**
 * The DAO Interface for the application. All Model Classes will be implemented using this Interface.
 * @param <T> Each Model Class that will implement this interface
 * @author KC Green
 */
public interface DAOInterface<T> {
    /**
     * Query the database for the T object with id.
     * @param id The T id.
     * @return The T object.
     */
    T get(int id);
    /**
     * Query the database for all T objects.
     * @return An ObservableList with all T objects
     */
    ObservableList<T> getAll();
    /**
     * Creates a new T object in the database.
     * @param t An instance of T.
     * @return The number of rows affected by the query.
     */
    int create(T t);
    /**
     * Updates a T object in the database.
     * @param t An instance of T.
     * @return The number of rows affected by the query.
     */
    int update(T t);
    /**
     * Delete a T object from the database.
     * @param t An instance of T.
     * @return The number of rows affected by the query.
     */
    int delete(T t);    
}
