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
    public static final String STUDENT_CLASSES = "STUDENT_CLASSES";
    public static final String CLASS_ID = "CLASS_ID";
    public static final String REGISTRATION_COURSES = "REGISTRATION_COURSES";
    public static final String REGISTRATION_ID = "REGISTRATION_ID";

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

        String createTable_studentClasses = "CREATE TABLE " + STUDENT_CLASSES + " (" + CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_ID + " TEXT, " + COLUMN_SESSION + " TEXT, " + COLUMN_WEEK_DAY + " TEXT )";
        db.execSQL(createTable_studentClasses);

        String createTable_registrationCourses = "CREATE TABLE " + REGISTRATION_COURSES + " (" + REGISTRATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_ID + " TEXT, " + COLUMN_COURSE_ID + " TEXT )";
        db.execSQL(createTable_registrationCourses);

        //adiciona usuario admin se não existir
        if (!isAdminUserExists(db)) {
            ContentValues adminValues = new ContentValues();
            adminValues.put(USER_NAME, "admin");
            adminValues.put(PASSWORD, "admin");
            adminValues.put(IS_ADMIN, 1);  // 1 representa true, 0 representa false
            db.insert(USER_TABLE, null, adminValues);
        }

        for (int i = 1; i <= 5; i++) {
            String studentName = "aluno" + i;
            String password = "senha" + i;
            int isAdmin = 0;  // Defina como 0 para não administradores

            // Adiciona o estudante à USER_TABLE
            addStudent(db, studentName, password, isAdmin);

            // Associa o estudante a uma classe na STUDENT_CLASSES pelo menos 3 vezes na semana
            for (int j = 0; j < 3; j++) {
                // As strings abaixo são apenas exemplos, ajuste conforme necessário
                String session = (j == 0) ? "manhã" : (j == 1) ? "tarde" : "noite";
                String weekDay = getWeekDay(j);

                // Associa o estudante à classe
                associateStudentClass(db, i, session, weekDay);
            }
        }

        // Fechar o banco de dados uma vez no final
       // db.close();
    }



    private boolean isAdminUserExists(SQLiteDatabase db) {
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_NAME + " = 'admin' AND " + IS_ADMIN + " = 1";
        Cursor cursor = db.rawQuery(query, null);

        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }

    private boolean isStudentExists(SQLiteDatabase db, String userName) {
        // Usar o banco de dados fornecido para evitar chamadas recursivas desnecessárias
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userName});
        boolean studentExists = cursor.getCount() > 0;
        cursor.close();
        return studentExists;
    }


    public int getUserType(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + IS_ADMIN + " FROM " + USER_TABLE +
                " WHERE " + USER_NAME + " = ? AND " +
                PASSWORD + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        int userType = -1; // Valor padrão para indicar que o usuário não foi encontrado

        if (cursor.moveToFirst()) {
            userType = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return userType;
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
       // db.close();
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
           // db.close();
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
      // db.close();
        return isAdminUser;
    }

    public boolean addStudent(SQLiteDatabase db, String userName, String password, int isAdmin) {
        // Verifica se o estudante já existe na base de dados
        if (isStudentExists(db, userName)) {
            return false; // Retorna false se o estudante já existir
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, userName);
        contentValues.put(PASSWORD, password);
        contentValues.put(IS_ADMIN, isAdmin);

        long result = db.insert(USER_TABLE, null, contentValues);

        return result != -1; // Retorna true se a inserção foi bem-sucedida, false caso contrário
    }





    // Método para associar um estudante a uma classe na tabela STUDENT_CLASSES
    public boolean associateStudentClass(SQLiteDatabase db, int userId, String session, String weekDay) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_ID, userId);
        contentValues.put(COLUMN_SESSION, session);
        contentValues.put(COLUMN_WEEK_DAY, weekDay);

        long result = db.insert(STUDENT_CLASSES, null, contentValues);

        return result != -1; // Retorna true se a inserção foi bem-sucedida, false caso contrário
    }



    private String getWeekDay(int dayIndex) {
        // Função auxiliar para obter o nome do dia da semana com base no índice
        String[] weekDays = {"segunda", "terça", "quarta", "quinta", "sexta"};
        return (dayIndex >= 0 && dayIndex < weekDays.length) ? weekDays[dayIndex] : "";
    }


}
