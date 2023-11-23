package com.ritter.cursoextensao.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.ritter.cursoextensao.AdminActivity;
import com.ritter.cursoextensao.ListCourse;
import com.ritter.cursoextensao.R;
import com.ritter.cursoextensao.data.DataBaseHelper;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class HomeAdminFragment extends Fragment {

    Button btn_save;
    Button btn_list;

    private DataBaseHelper dbHelper;
    private TextView counterAlunos;
    private TextView counterCursos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);
        // Inicialize elementos de UI e lógica específica para o ALUNO aqui
        btn_save = view.findViewById(R.id.btn_save_curso);
        btn_list = view.findViewById(R.id.btn_list_curso);
        dbHelper = new DataBaseHelper(getActivity());

        counterAlunos = view.findViewById(R.id.counterAlunos);
        counterCursos = view.findViewById(R.id.counterCursos);

        updateCounters();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListCourse.class);
                startActivity(intent);
            }
        });



        //if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
        //    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Título para Aluno");
        //}

        return view;
    }
    private void updateCounters(){
        int alunosCount = dbHelper.countAllStudents();
        int cursosCount = dbHelper.countAllCourses();

        counterAlunos.setText(String.valueOf(alunosCount));
        counterCursos.setText(String.valueOf(cursosCount));
    }
}