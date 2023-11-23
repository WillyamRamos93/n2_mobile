package com.ritter.cursoextensao.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ritter.cursoextensao.AvailableCourses;
import com.ritter.cursoextensao.ListCourse;
import com.ritter.cursoextensao.R;
import com.ritter.cursoextensao.RegisteredCoursesAdapter;
import com.ritter.cursoextensao.StudentActivity;
import com.ritter.cursoextensao.data.CourseModel;
import com.ritter.cursoextensao.data.DataBaseHelper;
import com.ritter.cursoextensao.data.UserInfo;
import com.ritter.cursoextensao.data.UserModel;

import java.util.List;


public class HomeAlunoFragment extends Fragment {

    Button btn_list;

    private RegisteredCoursesAdapter registeredCoursesAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_aluno, container, false);

        // Inicialize elementos de UI e lógica específica para o ALUNO aqui
        btn_list = view.findViewById(R.id.buttonAvailableCourses);
        recyclerView = view.findViewById(R.id.RecyclerViewRegistredCourses);
        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        UserModel user = UserInfo.getUserModel();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        int userID = user.getId();
        List<CourseModel> registeredCourses = dbHelper.getUserCourses(userID);
        registeredCoursesAdapter = new RegisteredCoursesAdapter(getActivity(), registeredCourses, new RegisteredCoursesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Implemente a lógica para lidar com o clique em um item da lista, se necessário
                CourseModel selectedCourse = registeredCourses.get(position);
                Toast.makeText(getActivity(), "Clicou em: " + selectedCourse.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRegisterButtonClick(int position) {
                // Implemente a lógica para lidar com o clique no botão de registro, se necessário
                CourseModel selectedCourse = registeredCourses.get(position);
                // Faça algo com o curso selecionado
            }
        });

        recyclerView.setAdapter(registeredCoursesAdapter);

        // Movido o bloco abaixo para fora do onCreateView
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar um Intent para abrir a ActivityListCourse
                Intent intent = new Intent(getActivity(), AvailableCourses.class);

                // Adicionar dados extras, se necessário
                // intent.putExtra("chave", valor);

                // Iniciar a nova Activity
                startActivity(intent);
            }
        });

        // Adicionando uma verificação para evitar o NullPointerException
        if (btn_list != null) {
            btn_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Criar um Intent para abrir a ActivityListCourse
                    Intent intent = new Intent(getActivity(), AvailableCourses.class);

                    // Adicionar dados extras, se necessário
                    // intent.putExtra("chave", valor);

                    // Iniciar a nova Activity
                    startActivity(intent);
                }
            });
        }


        return view;
    }
}
