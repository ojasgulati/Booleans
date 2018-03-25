package com.example.hp.booleans.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.example.hp.booleans.FetchData.Data;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP on 24-03-2018.
 */

public class DatabaseUtil {

    public static void initializeDatabase(Context context) {
        NewsDatabaseHelper myDbHelper = new NewsDatabaseHelper(context);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

       /* try {

            myDbHelper.openDataBase();


        } catch (SQLException sqle) {

            throw sqle;

        }*/
    }

    public static ArrayList<Data> getTrendingList(Context context) {
        NewsDatabaseHelper myDbHelper = new NewsDatabaseHelper(context);
        myDbHelper.openDataBase();
        ArrayList<Data> list = myDbHelper.getTrendingList(context);
        myDbHelper.getAccuracy("getAccuracy");
        return list;

    }

    public static ArrayList<Data> getEntertainmentList(Context context) {
        NewsDatabaseHelper myDbHelper = new NewsDatabaseHelper(context);
        myDbHelper.openDataBase();
        ArrayList<Data> list = myDbHelper.getEntertainmentList(context);
        return list;

    }

    public static ArrayList<Data> getWorldList(Context context) {
        NewsDatabaseHelper myDbHelper = new NewsDatabaseHelper(context);
        myDbHelper.openDataBase();
        ArrayList<Data> list = myDbHelper.getWorldList(context);
        return list;

    }
    public static ArrayList<Data> getSportsList(Context context) {
        NewsDatabaseHelper myDbHelper = new NewsDatabaseHelper(context);
        myDbHelper.openDataBase();
        ArrayList<Data> list = myDbHelper.getSportsList(context);
        return list;

    }

    public static int getAccuracy(Context context,String copiedText){
        NewsDatabaseHelper myDbHelper = new NewsDatabaseHelper(context);
        myDbHelper.openDataBase();
        return myDbHelper.getAccuracy(copiedText);
    }
}
