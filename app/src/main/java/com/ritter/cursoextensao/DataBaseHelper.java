package com.ritter.cursoextensao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        ContentValues cv_course = new ContentValues();
        ContentValues cv_schedule = new ContentValues();
        db.beginTransaction();

        try{
            //Inserir na tabela COURSE_TABLE
            cv_course.put(COLUMN_NM_COURSE, courseModel.getName());
            cv_course.put(COLUMN_DESCRIPTION, courseModel.getDescription());
            long courseInsert = db.insert(COURSE_TABLE, null, cv_course);

            // Recuperar o ID recém-inserido na tabela COURSE_TABLE
            long courseId = -1;
            if (courseInsert != -1) {
                String query = "SELECT last_insert_rowid() AS " + COLUMN_COURSE_ID;
                Cursor cursor = db.rawQuery(query, null);
                try {
                    if (cursor.moveToFirst()) { // Move para a primeira posição, se possível
                        int columnIndex = cursor.getColumnIndex(COLUMN_COURSE_ID);
                        if (columnIndex >= 0) {
                            courseId = cursor.getLong(columnIndex);
                        } else {
                            // Lidar com o caso em que a coluna não foi encontrada
                            // Por exemplo, lançar uma exceção ou registrar um aviso
                        }
                    } else {
                        // Lidar com o caso em que a consulta não retornou nenhum resultado
                    }
                } finally {
                    cursor.close();
                }
            }

            //Inserir na tabela SCHEDULES_TABLE
            cv_schedule.put(COLUMN_SESSION, courseModel.getSession());
            cv_schedule.put(COLUMN_WEEK_DAY, courseModel.getWeekDay());
            cv_schedule.put(COLUMN_ID_COURSE, courseId);
            long scheduleInsert = db.insert(SCHEDULES_TABLE, null, cv_schedule);

            if(courseInsert != -1 && scheduleInsert != -1){
                db.setTransactionSuccessful(); // Commit a transação se ambas as inserções foram bem-sucedidas
                return true;
            }else{
                return false;
            }
        } finally {
            db.endTransaction();
        }

    }

    public List<CourseModel> getAllCourses(){
        List<CourseModel> returnList = new ArrayList<>();
        String queryCourse = "SELECT "  + COLUMN_COURSE_ID + ", " + COLUMN_NM_COURSE + ", " + COLUMN_SESSION + ", " + COLUMN_WEEK_DAY + ", " + COLUMN_DESCRIPTION +
                             " FROM "   + COURSE_TABLE + " C " +
                             " JOIN "   + SCHEDULES_TABLE + " S " +
                             " ON C."   + COLUMN_COURSE_ID + " = " + " S." + COLUMN_ID_COURSE;

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
}
