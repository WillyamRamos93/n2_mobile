package com.ritter.cursoextensao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentActivity extends AppCompatActivity {

    Button btn_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        
        btn_list = findViewById(R.id.buttonAvailableCourses);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar um Intent para abrir a ActivityListCourse
                Intent intent = new Intent(StudentActivity.this, AvailableCourses.class);

                // Adicionar dados extras, se necessário
                // intent.putExtra("chave", valor);

                // Iniciar a nova Activity
                startActivity(intent);
            }
        });
    }


}