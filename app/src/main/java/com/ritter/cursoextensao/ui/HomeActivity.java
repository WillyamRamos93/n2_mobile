package com.ritter.cursoextensao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;

import com.ritter.cursoextensao.R;
import com.ritter.cursoextensao.ui.fragments.HomeAdminFragment;
import com.ritter.cursoextensao.ui.fragments.HomeAlunoFragment;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Seta as informaçoes do menu lateral
        NavigationView navigationView = findViewById(R.id.home_nav_view);

        View headerView = navigationView.getHeaderView(0);

        TextView nomeTextView = headerView.findViewById(R.id.nomeNavView);
        //TextView emailTextView = headerView.findViewById(R.id.emailNavView);

        nomeTextView.setText(getIntent().getStringExtra("USERNAME"));
        //emailTextView.setText("Idade do Aluno");

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
    }
    private void showAlunoUI() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_container, new HomeAlunoFragment())
                .commit();
    }
}
