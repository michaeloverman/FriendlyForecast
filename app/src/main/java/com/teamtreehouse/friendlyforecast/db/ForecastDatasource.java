package com.teamtreehouse.friendlyforecast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        mDatabase.beginTransaction();

        try {
            for (Forecast.HourData hour : forecast.hourly.data) {
                ContentValues values = new ContentValues();
                values.put(ForecastHelper.COLUMN_TEMPERATURE, hour.temperature);
                mDatabase.insert(ForecastHelper.TABLE_TEMPERATURES, null, values);
            }
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    // select
    public Cursor selectAllTemperatures() {
        Cursor cursor = mDatabase.query(
                ForecastHelper.TABLE_TEMPERATURES, // table
                new String[] { ForecastHelper.COLUMN_TEMPERATURE }, // column names
                null, // where clause
                null, // where parameters
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }

    public Cursor selectTempsGreaterThan(String minTemp) {
        String whereClause = ForecastHelper.COLUMN_TEMPERATURE + " > ?";

        Cursor cursor = mDatabase.query(
                ForecastHelper.TABLE_TEMPERATURES, // table
                new String[] { ForecastHelper.COLUMN_TEMPERATURE }, // column names
                whereClause, // where clause
                new String[] { minTemp }, // where parameters
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }
    // update
    public int updateTemperature(double newTemp) {
        ContentValues values = new ContentValues();
        values.put(ForecastHelper.COLUMN_TEMPERATURE, newTemp);
        int rowsUpdated = mDatabase.update(
                ForecastHelper.TABLE_TEMPERATURES,
                values,
                null,
                null
        );
        return rowsUpdated;
    }

    // delete
    public void deleteAll() {
        mDatabase.delete(
                ForecastHelper.TABLE_TEMPERATURES,
                null,
                null
        );

    }

}
