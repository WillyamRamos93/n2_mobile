package com.ritter.cursoextensao.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ritter.cursoextensao.AvailableCourses;
import com.ritter.cursoextensao.ListCourse;
import com.ritter.cursoextensao.R;
import com.ritter.cursoextensao.StudentActivity;

public class HomeAlunoFragment extends Fragment {

    Button btn_list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_aluno, container, false);

        // Inicialize elementos de UI e lógica específica para o ALUNO aqui
        btn_list = view.findViewById(R.id.buttonAvailableCourses);
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
        return view;
    }

}