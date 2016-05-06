package com.example.eksannara.daquiz;

/**
 * Created by Ek Sannara on 2/19/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapterActivity {

    static final String KEY_ROWID = "_id";
    static final String KEY_FROM1 = "from1";
    static final String KEY_TO1 = "to1";
    static final String KEY_TO2 = "to2";
    static final String KEY_TO3 = "to3";
    static final String KEY_TO4 = "to4";
    static final String KEY_TO5 = "to5";
    static final String KEY_TO6 = "to6";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "contacts";
    static final String DOTA_TABLE="dota";
    static final String d1="_id";
    static final String d2="dquestion";
    static final String d3="dimg";
    static final String d4="a1";
    static final String d5="a2";
    static final String d6="a3";
    static final String d7="a4";
    static final String CAPITAL_TABLE="capital";
    static final String c1="_id";
    static final String c2="cquestion";
    static final String c3="cimg";
    static final String c4="a1";
    static final String c5="a2";
    static final String c6="a3";
    static final String c7="a4";
    static final String POLITICS_TABLE="politics";
    static final String p1="_id";
    static final String p2="pquestion";
    static final String p3="pimg";
    static final String p4="p1";
    static final String p5="p2";
    static final String p6="p3";
    static final String p7="p4";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE =
            "create table contacts (_id integer primary key autoincrement, "
                    + "from1 text not null, to1 text not null, to2 text not null, to3 text not null, to4 text not null, to5 text not null, to6 text not null);";
    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapterActivity(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapterActivity open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---retrieves all the contacts---

    public Cursor getAllDota()
    {
        return db.query(DOTA_TABLE, new String[] {d1,
                d2, d3, d4, d5, d6, d7}, null, null, null, null, null);
    }

    public Cursor getDota(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true,DOTA_TABLE, new String[] {d1,
                                d2, d3, d4, d5, d6, d7}, d1 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }    public Cursor getPolitic(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true,POLITICS_TABLE, new String[] {p1,
                                p2, p3, p4, p5, p6, p7}, p1 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getAllPolitic()
    {
        return db.query(POLITICS_TABLE, new String[] {p1,
                p2, p3, p4, p5, p6, p7}, null, null, null, null, null);
    }
    public Cursor getCapital(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true,CAPITAL_TABLE, new String[] {c1,
                                c2, c3, c4, c5, c6, c7}, c1 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getAllCapital()
    {
        return db.query(CAPITAL_TABLE, new String[] {c1,
                c2, c3, c4, c5, c6, c7}, null, null, null, null, null);
    }
    public boolean updateDotaPoint(String point)
    {
        ContentValues args = new ContentValues();
        args.put("dpoint", point);
        return db.update("points", args, KEY_ROWID + "=" + 1, null) > 0;
    }
    public Cursor getPoint(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true,"points", new String[] {c1,
                                "dpoint", "cpoint", "ppoint"}, c1 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}


