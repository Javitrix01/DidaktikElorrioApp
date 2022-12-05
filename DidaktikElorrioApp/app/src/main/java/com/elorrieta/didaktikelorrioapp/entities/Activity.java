package com.elorrieta.didaktikelorrioapp.entities;

import androidx.room.*;


//ESTRUCTURA DE LA ENTIDAD DE CADA ACTIVIDAD
@Entity
public class Activity {
    //id de la actividad
    @PrimaryKey
    public int id;

    //nombre de la actividad
    @ColumnInfo(name = "activity_name")
    public String activity_name;

    //descripcion de la actividad
    @ColumnInfo(name = "activity_description")
    public String activity_description;

    //coordenadas de la actividad
    @ColumnInfo(name = "activity_coords_long")
    public String activity_coords_long;
    @ColumnInfo(name = "activity_coords_lat")
    public String activity_coords_lat;

    //la parada ha sido visitada o no
    @ColumnInfo(name = "activity_visited")
    public boolean activity_visited;

    //constructor
    public Activity(int id, String activity_name, String activity_description, String activity_coords_long, String activity_coords_lat, boolean activity_visited){
        this.id = id;
        this.activity_name = activity_name;
        this.activity_description = activity_description;
        this.activity_coords_long = activity_coords_long;
        this.activity_coords_lat = activity_coords_lat;
        this.activity_visited = activity_visited;
    }

    //getters
    public int getId() {
        return id;
    }
    public String getActivity_name() {
        return activity_name;
    }
    public String getActivity_description() {
        return activity_description;
    }
    public String getActivity_coords_long() {
        return activity_coords_long;
    }
    public String getActivity_coords_lat() {
        return activity_coords_lat;
    }
    public boolean isActivity_visited() {
        return activity_visited;
    }
    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }
    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
    }
    public void setActivity_coords_long(String activity_coords_long) {
        this.activity_coords_long = activity_coords_long;
    }
    public void setActivity_coords_lat(String activity_coords_lat) {
        this.activity_coords_lat = activity_coords_lat;
    }
    public void setActivity_visited(boolean activity_visited) {
        this.activity_visited = activity_visited;
    }

    //toString
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activity_name='" + activity_name + '\'' +
                ", activity_description='" + activity_description + '\'' +
                ", activity_coords_long='" + activity_coords_long + '\'' +
                ", activity_coords_lat='" + activity_coords_lat + '\'' +
                ", activity_visited=" + activity_visited +
                '}';
    }
}
