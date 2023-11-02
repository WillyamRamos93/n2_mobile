package com.ritter.cursoextensao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //references to buttons

    Button btn_save;
    EditText et_courseName, et_courseDesc;
    Spinner sp_weekDay, sp_classSession;
    RecyclerView lv_testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_save = findViewById(R.id.saveCourse);
        et_courseName = findViewById(R.id.nmCourse);
        et_courseDesc = findViewById(R.id.descClass);
        sp_weekDay = findViewById(R.id.classDay);
        sp_classSession = findViewById(R.id.classSession);
        lv_testList = findViewById(R.id.testView);

        // button listeners
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CourseModel courseModel;
                try {
                    courseModel = new CourseModel(-1, et_courseName.getText().toString(), sp_classSession.getSelectedItem().toString(), sp_weekDay.getSelectedItem().toString(), et_courseDesc.getText().toString() );
                    Toast.makeText(MainActivity.this, courseModel.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error creating Course", Toast.LENGTH_SHORT).show();
                    courseModel = new CourseModel(-1, "error", "error", "error", "error");

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addCourse(courseModel);
                Toast.makeText(MainActivity.this, "SUCCESS" + success, Toast.LENGTH_SHORT).show();


            }


        });


    }
}