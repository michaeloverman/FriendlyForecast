package com.teamtreehouse.friendlyforecast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.teamtreehouse.friendlyforecast.services.Forecast;

import java.sql.SQLException;

/**
 * Created by overm on 3/8/2016.
 */
public class ForecastDatasource {
    private final String TAG = ForecastDatasource.class.getSimpleName();
    private Context mContext;
    private ForecastHelper mForecastHelper;
    private SQLiteDatabase mDatabase;

    public ForecastDatasource(Context context) {
        mContext = context;
        mForecastHelper = new ForecastHelper(mContext);
    }

    // open
    public void open() throws SQLException{
        Log.d(TAG, "opening database!!");
        mDatabase = mForecastHelper.getWritableDatabase();
    }

    // close
    public void close() {
        mDatabase.close();
    }


    // insert
    public void insertForecast(Forecast forecast) {
        Log.d(TAG, "In insertForecast()");
        ContentValues values = new ContentValues();
        values.put(ForecastHelper.COLUMN_TEMPERATURE, 75.0);
        mDatabase.insert(ForecastHelper.TABLE_TEMPERATURES, null, values);
    }

    // select


    // update


    // delete


}
