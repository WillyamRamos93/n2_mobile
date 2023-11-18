package com.ritter.cursoextensao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class AvailableCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_courses);

        RecyclerView recyclerView = findViewById(R.id.user_AvailableCourses);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DataBaseHelper dbHelper = new DataBaseHelper(this);


        int userId = 1; // Substitua pelo ID real do usuário, ou obtenha-o de alguma forma
        List<CourseModel> availableCourses = dbHelper.getUserAvailableCourses(userId);

        AvailableCoursesAdapter adapter = new AvailableCoursesAdapter(this, availableCourses, new AvailableCoursesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Implemente a lógica para lidar com o clique em um item da lista, se necessário
                CourseModel selectedCourse = availableCourses.get(position);
                Toast.makeText(AvailableCourses.this, "Clicou em: " + selectedCourse.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRegisterButtonClick(int position) {
                // Implemente a lógica para lidar com o clique no botão de registro, se necessário
                CourseModel selectedCourse = availableCourses.get(position);
                dbHelper.registerUserToCourse(userId, selectedCourse.getId());
                Toast.makeText(AvailableCourses.this, "Registrado em: " + selectedCourse.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
