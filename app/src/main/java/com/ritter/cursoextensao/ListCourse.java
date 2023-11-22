package com.ritter.cursoextensao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ritter.cursoextensao.data.CourseModel;
import com.ritter.cursoextensao.data.DataBaseHelper;

import java.util.List;

public class ListCourse extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);

        RecyclerView recyclerView = findViewById(R.id.admin_listCourses);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DataBaseHelper dbHelper = new DataBaseHelper(this);

        List<CourseModel> courseModelList = dbHelper.getAllCourses();

        admin_Adapter adapter = new admin_Adapter(this, courseModelList);
        recyclerView.setAdapter(adapter);
    }
}