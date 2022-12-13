package com.elorrieta.didaktikelorrioapp.appdatabase;

import android.content.Context;

import com.elorrieta.didaktikelorrioapp.dao.UserDao;
import com.elorrieta.didaktikelorrioapp.dao.ActivityDao;
import com.elorrieta.didaktikelorrioapp.entities.Activity;
import com.elorrieta.didaktikelorrioapp.entities.User;

import androidx.room.*;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Activity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ActivityDao activityDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "DidaktikAppDB")
                            .createFromAsset("DidaktikAppDB.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}