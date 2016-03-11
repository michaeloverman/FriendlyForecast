package com.teamtreehouse.friendlyforecast.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by overm on 3/8/2016.
 */
public class ForecastDatasource {
    private Context mContext;
    private ForecastHelper mForecastHelper;
    private SQLiteDatabase mDatabase;

    public ForecastDatasource(Context context) {
        mContext = context;
        mForecastHelper = new ForecastHelper(mContext);
    }

    // open
    public void open() throws SQLException{
        mDatabase = mForecastHelper.getWritableDatabase();
    }

    // close
    public void close() {
        mDatabase.close();
    }


    // insert


    // select


    // update


    // delete


}
