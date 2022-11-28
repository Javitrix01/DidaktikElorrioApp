package com.elorrieta.didaktikelorrioapp.appdatabase;

import androidx.room.*;
import androidx.room.RoomDatabase;

import com.elorrieta.didaktikelorrioapp.dao.UserDao;
import com.elorrieta.didaktikelorrioapp.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}