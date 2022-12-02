package com.elorrieta.didaktikelorrioapp.appdatabase;

import androidx.room.*;
import androidx.room.RoomDatabase;

import com.elorrieta.didaktikelorrioapp.dao.ActivityDao;
import com.elorrieta.didaktikelorrioapp.dao.UserDao;
import com.elorrieta.didaktikelorrioapp.entities.Activity;
import com.elorrieta.didaktikelorrioapp.entities.User;

@Database(entities = {User.class, Activity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    public abstract UserDao userDao();
    public abstract ActivityDao activityDao();
}