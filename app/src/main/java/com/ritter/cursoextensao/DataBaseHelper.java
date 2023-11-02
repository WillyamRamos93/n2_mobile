package com.ritter.cursoextensao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String COURSE_TABLE = "COURSE_TABLE";
    public static final String COLUMN_NM_COURSE = "nm_course";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String SCHEDULES_TABLE = "SCHEDULES_TABLE";
    public static final String COLUMN_SCHEDULES_ID = "schedules_id";
    public static final String COLUMN_ID_COURSE = "id_course";
    public static final String COLUMN_SESSION = "session";
    public static final String COLUMN_WEEK_DAY = "weekDay";

    public DataBaseHelper(@Nullable Context context ) {
        super(context, "course.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable_course = "CREATE TABLE " + COURSE_TABLE + " (" + COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NM_COURSE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT) ";
        db.execSQL(createTable_course);

        String createTable_schedules = "CREATE TABLE " + SCHEDULES_TABLE + " ( " + COLUMN_SCHEDULES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ID_COURSE + " INTEGER, " + COLUMN_SESSION + " TEXT, " + COLUMN_WEEK_DAY + " TEXT, FOREIGN KEY ( " + COLUMN_ID_COURSE + " ) REFERENCES " + COURSE_TABLE + " ( " + COLUMN_COURSE_ID + " )) ";
        db.execSQL(createTable_schedules);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addCourse (CourseModel courseModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_NM_COURSE, courseModel.getName());
        cv.put(COLUMN_DESCRIPTION, courseModel.getDescription());
        cv.put(COLUMN_SESSION, courseModel.getSession());
        cv.put(COLUMN_WEEK_DAY, courseModel.getWeekDay());

        long insert = db.insert(COURSE_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }

    }
}
