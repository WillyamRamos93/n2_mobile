package com.ritter.cursoextensao.ui.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ritter.cursoextensao.R;

public class HomeAdminFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);
        // Inicialize elementos de UI e lógica específica para o ALUNO aqui

        //if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
        //    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Título para Aluno");
        //}

        return view;
    }
}