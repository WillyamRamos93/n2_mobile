package com.ritter.cursoextensao.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ritter.cursoextensao.R;
import com.ritter.cursoextensao.ui.fragments.AdminFragment;
import com.ritter.cursoextensao.ui.fragments.AlunoFragment;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Obter o tipo de usuário do intent
        boolean tipoUsuario = getIntent().getBooleanExtra("EH-ADMIN", true);

        // Decide qual fragmento exibir com base no tipo de usuário
        if (tipoUsuario) {
            exibirFragmento(new AdminFragment());
            finish();
        }
        exibirFragmento(new AlunoFragment());
    }

    private void exibirFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
