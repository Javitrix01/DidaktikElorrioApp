package com.elorrieta.didaktikelorrioapp.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.elorrieta.didaktikelorrioapp.entities.Activity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ActivityDao_Impl implements ActivityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Activity> __insertionAdapterOfActivity;

  private final EntityDeletionOrUpdateAdapter<Activity> __deletionAdapterOfActivity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateActivityById;

  private final SharedSQLiteStatement __preparedStmtOfUpdateActivityVisitedById;

  public ActivityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActivity = new EntityInsertionAdapter<Activity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Activity` (`id`,`activity_name`,`activity_description`,`activity_coords_long`,`activity_coords_lat`,`activity_visited`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Activity value) {
        stmt.bindLong(1, value.id);
        if (value.activity_name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.activity_name);
        }
        if (value.activity_description == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.activity_description);
        }
        if (value.activity_coords_long == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.activity_coords_long);
        }
        if (value.activity_coords_lat == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.activity_coords_lat);
        }
        final int _tmp = value.activity_visited ? 1 : 0;
        stmt.bindLong(6, _tmp);
      }
    };
    this.__deletionAdapterOfActivity = new EntityDeletionOrUpdateAdapter<Activity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Activity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Activity value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__preparedStmtOfUpdateActivityById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE activity SET activity_name = ?, activity_description = ?, activity_coords_long = ?, activity_coords_lat = ?, activity_visited = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateActivityVisitedById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE activity SET activity_visited = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertActivity(final Activity activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfActivity.insert(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertActivities(final Activity... activities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfActivity.insert(activities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteActivity(final Activity activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfActivity.handle(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateActivityById(final int id, final String activity_name,
      final String activity_description, final String activity_coords_long,
      final String activity_coords_lat, final boolean activity_visited) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateActivityById.acquire();
    int _argIndex = 1;
    if (activity_name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, activity_name);
    }
    _argIndex = 2;
    if (activity_description == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, activity_description);
    }
    _argIndex = 3;
    if (activity_coords_long == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, activity_coords_long);
    }
    _argIndex = 4;
    if (activity_coords_lat == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, activity_coords_lat);
    }
    _argIndex = 5;
    final int _tmp = activity_visited ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 6;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateActivityById.release(_stmt);
    }
  }

  @Override
  public void updateActivityVisitedById(final int id, final boolean activity_visited) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateActivityVisitedById.acquire();
    int _argIndex = 1;
    final int _tmp = activity_visited ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateActivityVisitedById.release(_stmt);
    }
  }

  @Override
  public List<Activity> getAll() {
    final String _sql = "SELECT * FROM activity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfActivityName = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_name");
      final int _cursorIndexOfActivityDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_description");
      final int _cursorIndexOfActivityCoordsLong = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_coords_long");
      final int _cursorIndexOfActivityCoordsLat = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_coords_lat");
      final int _cursorIndexOfActivityVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_visited");
      final List<Activity> _result = new ArrayList<Activity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Activity _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpActivity_name;
        if (_cursor.isNull(_cursorIndexOfActivityName)) {
          _tmpActivity_name = null;
        } else {
          _tmpActivity_name = _cursor.getString(_cursorIndexOfActivityName);
        }
        final String _tmpActivity_description;
        if (_cursor.isNull(_cursorIndexOfActivityDescription)) {
          _tmpActivity_description = null;
        } else {
          _tmpActivity_description = _cursor.getString(_cursorIndexOfActivityDescription);
        }
        final String _tmpActivity_coords_long;
        if (_cursor.isNull(_cursorIndexOfActivityCoordsLong)) {
          _tmpActivity_coords_long = null;
        } else {
          _tmpActivity_coords_long = _cursor.getString(_cursorIndexOfActivityCoordsLong);
        }
        final String _tmpActivity_coords_lat;
        if (_cursor.isNull(_cursorIndexOfActivityCoordsLat)) {
          _tmpActivity_coords_lat = null;
        } else {
          _tmpActivity_coords_lat = _cursor.getString(_cursorIndexOfActivityCoordsLat);
        }
        final boolean _tmpActivity_visited;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfActivityVisited);
        _tmpActivity_visited = _tmp != 0;
        _item = new Activity(_tmpId,_tmpActivity_name,_tmpActivity_description,_tmpActivity_coords_long,_tmpActivity_coords_lat,_tmpActivity_visited);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Activity findById(final int id) {
    final String _sql = "SELECT * FROM activity WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfActivityName = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_name");
      final int _cursorIndexOfActivityDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_description");
      final int _cursorIndexOfActivityCoordsLong = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_coords_long");
      final int _cursorIndexOfActivityCoordsLat = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_coords_lat");
      final int _cursorIndexOfActivityVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "activity_visited");
      final Activity _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpActivity_name;
        if (_cursor.isNull(_cursorIndexOfActivityName)) {
          _tmpActivity_name = null;
        } else {
          _tmpActivity_name = _cursor.getString(_cursorIndexOfActivityName);
        }
        final String _tmpActivity_description;
        if (_cursor.isNull(_cursorIndexOfActivityDescription)) {
          _tmpActivity_description = null;
        } else {
          _tmpActivity_description = _cursor.getString(_cursorIndexOfActivityDescription);
        }
        final String _tmpActivity_coords_long;
        if (_cursor.isNull(_cursorIndexOfActivityCoordsLong)) {
          _tmpActivity_coords_long = null;
        } else {
          _tmpActivity_coords_long = _cursor.getString(_cursorIndexOfActivityCoordsLong);
        }
        final String _tmpActivity_coords_lat;
        if (_cursor.isNull(_cursorIndexOfActivityCoordsLat)) {
          _tmpActivity_coords_lat = null;
        } else {
          _tmpActivity_coords_lat = _cursor.getString(_cursorIndexOfActivityCoordsLat);
        }
        final boolean _tmpActivity_visited;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfActivityVisited);
        _tmpActivity_visited = _tmp != 0;
        _result = new Activity(_tmpId,_tmpActivity_name,_tmpActivity_description,_tmpActivity_coords_long,_tmpActivity_coords_lat,_tmpActivity_visited);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public boolean isActivityVisited(final int id) {
    final String _sql = "SELECT activity_visited FROM activity WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
