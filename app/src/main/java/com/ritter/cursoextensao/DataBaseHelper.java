package com.ritter.cursoextensao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String COURSE_TABLE = "COURSE_TABLE";
    public static final String COLUMN_NM_COURSE = "nm_course";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String COLUMN_SESSION = "session";
    public static final String COLUMN_WEEK_DAY = "weekDay";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String IS_ADMIN = "IS_ADMIN";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "course.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable_course = "CREATE TABLE " + COURSE_TABLE + " (" + COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NM_COURSE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_SESSION + " TEXT, " + COLUMN_WEEK_DAY + " TEXT) ";
        db.execSQL(createTable_course);

        String createTable_user = "CREATE TABLE " + USER_TABLE + "( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT, " + PASSWORD + " TEXT, " + IS_ADMIN + " INTEGER )";
        db.execSQL(createTable_user);
        Log.d("DataBaseHelper", "Tabela USER_TABLE criada com sucesso");
        //adiciona usuario admin se não existir
        if (!isAdminUserExists(db)) {
            ContentValues adminValues = new ContentValues();
            adminValues.put(USER_NAME, "admin");
            adminValues.put(PASSWORD, "admin");
            adminValues.put(IS_ADMIN, 1);  // 1 representa true, 0 representa false
            db.insert(USER_TABLE, null, adminValues);
        }
    }

    private boolean isAdminUserExists(SQLiteDatabase db) {
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_NAME + " = 'admin' AND " + IS_ADMIN + " = 1";
        Cursor cursor = db.rawQuery(query, null);

        boolean userExists = cursor.getCount() > 0;

        cursor.close();
        return userExists;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addCourse(CourseModel courseModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv_course = new ContentValues();
        db.beginTransaction();

        try {
            // Inserir na tabela COURSE_TABLE
            cv_course.put(COLUMN_NM_COURSE, courseModel.getName());
            cv_course.put(COLUMN_DESCRIPTION, courseModel.getDescription());
            cv_course.put(COLUMN_SESSION, courseModel.getSession());
            cv_course.put(COLUMN_WEEK_DAY, courseModel.getWeekDay());
            long courseInsert = db.insert(COURSE_TABLE, null, cv_course);

            if (courseInsert != -1) {
                db.setTransactionSuccessful(); // Commit a transação se a inserção foi bem-sucedida
                return true;
            } else {
                return false;
            }
        } finally {
            db.endTransaction();
        }
    }


    public List<CourseModel> getAllCourses(){
        List<CourseModel> returnList = new ArrayList<>();
        String queryCourse = "SELECT "  + COLUMN_COURSE_ID + ", " + COLUMN_NM_COURSE + ", " + COLUMN_SESSION + ", " + COLUMN_WEEK_DAY + ", " + COLUMN_DESCRIPTION +
                             " FROM "   + COURSE_TABLE ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryCourse, null);

        if(cursor.moveToFirst()){
            //loop pela query e cria um objeto do curso
            do{
                int courseId = cursor.getInt(0);
                String courseName = cursor.getString(1);
                String courseSession = cursor.getString(2);
                String courseDay = cursor.getString(3);
                String courseDesc = cursor.getString(4);

                CourseModel newCourse = new CourseModel(courseId, courseName, courseSession, courseDay, courseDesc);
                returnList.add(newCourse);
            }while(cursor.moveToNext());
        }else{

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteCourse(int courseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try{

            //Em seguida, excluir da tb COURSE
            int rowsAffected = db.delete(COURSE_TABLE, COLUMN_COURSE_ID + " =?", new String[]{String.valueOf(courseId)});

            if (rowsAffected > 0){
                db.setTransactionSuccessful();
                return true;
            } else {
                return false;
            }
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public boolean checkAdminUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE +
                " WHERE " + USER_NAME + " = ? AND " +
                PASSWORD + " = ? AND " +
                IS_ADMIN + " = 1";

        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        boolean isAdminUser = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isAdminUser;
    }
}
