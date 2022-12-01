package com.elorrieta.didaktikelorrioapp.dao;

import androidx.room.*;

import com.elorrieta.didaktikelorrioapp.entities.Activity;
import java.util.List;

//ESTRUCTURA DEL DAO DE ACTIVIDAD
@Dao
public interface ActivityDao {
    //seleccionar toda la informacion de todas las actividades
    @Query("SELECT * FROM activity")
    List<Activity> getAll();

    //seleccionar toda la informacion de una actividad con una id concreta
    @Query("SELECT * FROM activity WHERE id = :id")
    Activity findById(int id);

    //insertar una actividad
    @Insert
    void insertActivity(Activity activity);

    //insertar varias actividades
    @Insert
    void insertActivities(Activity... activities);

    //borrar una actividad
    @Delete
    void deleteActivity(Activity activity);

    //actualizar una actividad por su id
    @Query("UPDATE activity SET activity_name = :activity_name, activity_description = :activity_description, activity_coords_long = :activity_coords_long, activity_coords_lat = :activity_coords_lat, activity_visited = :activity_visited WHERE id = :id")
    void updateActivityById(int id, String activity_name, String activity_description, String activity_coords_long, String activity_coords_lat, boolean activity_visited);

    //actualizar el estado de visita de una actividad dependiendo de su id
    @Query("UPDATE activity SET activity_visited = :activity_visited WHERE id = :id")
    void updateActivityVisitedById(int id, boolean activity_visited);
}
