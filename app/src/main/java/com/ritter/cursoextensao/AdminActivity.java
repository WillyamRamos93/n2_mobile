package com.ritter.cursoextensao;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ritter.cursoextensao.data.CourseModel;
import com.ritter.cursoextensao.data.DataBaseHelper;

public class AdminActivity extends AppCompatActivity {

    //references to buttons

    Button btn_list;
    Button btn_save;
    EditText et_courseName, et_courseDesc;
    Spinner sp_weekDay, sp_classSession;
    ListView lv_testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btn_save = findViewById(R.id.saveCourse);
        btn_list = findViewById(R.id.btn_list);
        et_courseName = findViewById(R.id.nmCourse);
        et_courseDesc = findViewById(R.id.descClass);
        sp_weekDay = findViewById(R.id.classDay);
        sp_classSession = findViewById(R.id.classSession);
        //lv_testList = findViewById(R.id.viewTest);

        // button listeners
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CourseModel courseModel;
                try {
                    courseModel = new CourseModel(-1, et_courseName.getText().toString(), sp_classSession.getSelectedItem().toString(), sp_weekDay.getSelectedItem().toString(), et_courseDesc.getText().toString() );
                    Toast.makeText(AdminActivity.this, courseModel.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(AdminActivity.this, "Error creating Course", Toast.LENGTH_SHORT).show();
                    courseModel = new CourseModel(-1, "error", "error", "error", "error");

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(AdminActivity.this);
                boolean success = dataBaseHelper.addCourse(courseModel);
                Toast.makeText(AdminActivity.this, "SUCCESS" + success, Toast.LENGTH_SHORT).show();


            }


        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar um Intent para abrir a ActivityListCourse
                Intent intent = new Intent(AdminActivity.this, ListCourse.class);

                // Adicionar dados extras, se necessário
                // intent.putExtra("chave", valor);

                // Iniciar a nova Activity
                startActivity(intent);
            }
        });




    }
}