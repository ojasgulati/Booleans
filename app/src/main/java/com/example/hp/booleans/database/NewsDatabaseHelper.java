package com.example.hp.booleans.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hp.booleans.FetchData.Data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 24-03-2018.
 */

public class NewsDatabaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.example.hp.booleans/databases/";

    private static String DB_NAME = "news_database.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public NewsDatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {

            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }


        //Close the streams

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void initializeDatabase(Context context) {
        NewsDatabaseHelper myDbHelper = new NewsDatabaseHelper(context);

        try {

            myDbHelper.createDataBase();
            Log.e("Database ", "initialized");

        } catch (IOException ioe) {
            Log.e("Database ", "Error not initialized");
            throw new Error("Unable to create database");

        }

    }

    public ArrayList<Data> getTrendingList(Context context) {
        ArrayList<Data> list = new ArrayList<>();
        Cursor cursor = myDataBase.query("trending", null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String fake_news = cursor.getString(cursor.getColumnIndex("fake_news"));
            String real_news = cursor.getString(cursor.getColumnIndex("real_news"));
            String img_url = cursor.getString(cursor.getColumnIndex("img_url"));
            String dop = cursor.getString(cursor.getColumnIndex("dop"));
            String person_name = cursor.getString(cursor.getColumnIndex("person_name"));
            String news_action = cursor.getString(cursor.getColumnIndex("news_action"));
            String news_place = cursor.getString(cursor.getColumnIndex("news place"));
            String others = cursor.getString(cursor.getColumnIndex("others"));
            // Log.e("ojas",title + fake_news);
            list.add(new Data(title, fake_news, real_news, img_url, dop, person_name, news_action, news_place, others));
            cursor.moveToNext();
        }
        return list;
    }

    public ArrayList<Data> getEntertainmentList(Context context) {
        ArrayList<Data> list = new ArrayList<>();
        Cursor cursor = myDataBase.query("entertainment", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String fake_news = cursor.getString(cursor.getColumnIndex("fake_news"));
            String real_news = cursor.getString(cursor.getColumnIndex("real_news"));
            String img_url = cursor.getString(cursor.getColumnIndex("img_url"));
            String dop = cursor.getString(cursor.getColumnIndex("dop"));
            String person_name = cursor.getString(cursor.getColumnIndex("person_name"));
            String news_action = cursor.getString(cursor.getColumnIndex("news_action"));
            String news_place = cursor.getString(cursor.getColumnIndex("news_place"));
            String others = cursor.getString(cursor.getColumnIndex("others"));
            // Log.e("ojas",title + fake_news);
            list.add(new Data(title, fake_news, real_news, img_url, dop, person_name, news_action, news_place, others));
            cursor.moveToNext();
        }
        return list;
    }

    public ArrayList<Data> getWorldList(Context context) {
        ArrayList<Data> list = new ArrayList<>();
        Cursor cursor = myDataBase.query("world", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String fake_news = cursor.getString(cursor.getColumnIndex("fake_news"));
            String real_news = cursor.getString(cursor.getColumnIndex("real_news"));
            String img_url = cursor.getString(cursor.getColumnIndex("img_url"));
            String dop = cursor.getString(cursor.getColumnIndex("dop"));
            String person_name = cursor.getString(cursor.getColumnIndex("person_name"));
            String news_action = cursor.getString(cursor.getColumnIndex("news_action"));
            String news_place = cursor.getString(cursor.getColumnIndex("news_place"));
            String others = cursor.getString(cursor.getColumnIndex("others"));
            // Log.e("ojas",title + fake_news);
            list.add(new Data(title, fake_news, real_news, img_url, dop, person_name, news_action, news_place, others));
            cursor.moveToNext();
        }
        return list;
    }

    public ArrayList<Data> getPoliticsList(Context context) {
        ArrayList<Data> list = new ArrayList<>();
        Cursor cursor = myDataBase.query("politics", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String fake_news = cursor.getString(cursor.getColumnIndex("fake_news"));
            String real_news = cursor.getString(cursor.getColumnIndex("real_news"));
            String img_url = cursor.getString(cursor.getColumnIndex("img_url"));
            String dop = cursor.getString(cursor.getColumnIndex("dop"));
            String person_name = cursor.getString(cursor.getColumnIndex("person_name"));
            String news_action = cursor.getString(cursor.getColumnIndex("news_action"));
            String news_place = cursor.getString(cursor.getColumnIndex("news_place"));
            String others = cursor.getString(cursor.getColumnIndex("others"));
            // Log.e("ojas",title + fake_news);
            list.add(new Data(title, fake_news, real_news, img_url, dop, person_name, news_action, news_place, others));
            cursor.moveToNext();
        }
        return list;
    }

    public ArrayList<Data> getSportsList(Context context) {
        ArrayList<Data> list = new ArrayList<>();
        Cursor cursor = myDataBase.query("sports", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String fake_news = cursor.getString(cursor.getColumnIndex("fake_news"));
            String real_news = cursor.getString(cursor.getColumnIndex("real_news"));
            String img_url = cursor.getString(cursor.getColumnIndex("img_url"));
            String dop = cursor.getString(cursor.getColumnIndex("dop"));
            String person_name = cursor.getString(cursor.getColumnIndex("person_name"));
            String news_action = cursor.getString(cursor.getColumnIndex("news_action"));
            String news_place = cursor.getString(cursor.getColumnIndex("news_place"));
            String others = cursor.getString(cursor.getColumnIndex("others"));
            // Log.e("ojas",title + fake_news);
            list.add(new Data(title, fake_news, real_news, img_url, dop, person_name, news_action, news_place, others));
            cursor.moveToNext();
        }
        return list;
    }

    public int getAccuracy(String copiedText) {
        int accuracy = 0;
        boolean containsPerson = false;
        boolean containsAction = false;
        boolean containsPlace = false;
        boolean containsOther;

        ArrayList<Data> trendingList = getTrendingList(null);
        ArrayList<Data> entertainmentList = getTrendingList(null);
        ArrayList<Data> worldList = getTrendingList(null);
        ArrayList<Data> politicsList = getTrendingList(null);
        ArrayList<Data> sportsList = getTrendingList(null);
        List<Data> combinedList = new ArrayList<Data>();
        combinedList.addAll(trendingList);
        combinedList.addAll(entertainmentList);
        combinedList.addAll(worldList);
        combinedList.addAll(politicsList);
        combinedList.addAll(sportsList);
        for (int i = 0; i < combinedList.size(); i++) {
            String person_name = combinedList.get(i).getPerson_name();
            if (person_name != null) {
                String[] splitName = person_name.split(",");
                for (int j = 0; j < splitName.length; j++) {
                    containsPerson = copiedText.contains(splitName[j]);
                    if (containsPerson) {

                        break;
                    }

                }
            }
            if (containsPerson) {
                String action = combinedList.get(i).getNews_action();
                String place = combinedList.get(i).getNews_action();
                if (action != null) {
                    String[] splitActions = action.split(",");
                    for (int j = 0; j < splitActions.length; j++) {
                        containsAction = copiedText.contains(splitActions[j]);
                        if (containsAction)
                            accuracy += 50;
                        break;
                    }
                }
                if (place != null) {
                    containsPlace = copiedText.contains(place);
                    if (containsPlace) {
                        accuracy += 25;
                    }
                }

                String others = combinedList.get(i).getOthers();
                if (others != null) {
                    String[] splitActions = action.split(",");
                    for (int j = 0; j < splitActions.length; j++) {
                        containsOther = copiedText.contains(splitActions[j]);
                        if (containsOther)
                            accuracy += 5;
                    }

                }

            }

        }
        return accuracy;
    }
}
