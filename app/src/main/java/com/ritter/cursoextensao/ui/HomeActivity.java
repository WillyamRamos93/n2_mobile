package com.ritter.cursoextensao.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ritter.cursoextensao.R;
import com.ritter.cursoextensao.ui.fragments.HomeAdminFragment;
import com.ritter.cursoextensao.ui.fragments.HomeAlunoFragment;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Obter o tipo de usuário do intent
        boolean ehAdmin = getIntent().getBooleanExtra("EH-ADMIN", true);

        // Decide qual fragmento exibir com base no tipo de usuário
        if (ehAdmin) {
            showAdminUI();
        }
        else{
            showAlunoUI();
        }
    }

    private void showAdminUI() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_container, new HomeAdminFragment())
                .commit();

        // Adiciona o Fragment do menu lateral para ADMIN
        //getSupportFragmentManager().beginTransaction()
        //        .replace(R.id.menu_container, new MenuAdminFragment())
        //        .commit();

        //getSupportActionBar().setTitle("Admin");
    }
    private void showAlunoUI() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_container, new HomeAlunoFragment())
                .commit();

        // Adiciona o Fragment do menu lateral para ALUNO
        //getSupportFragmentManager().beginTransaction()
        //       .replace(R.id.menu_container, new MenuAlunoFragment())
        //        .commit();

        //getSupportActionBar().setTitle("Aluno");
    }
}
