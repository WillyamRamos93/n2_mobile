package com.ritter.cursoextensao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText userName = findViewById(R.id.user_name);
        EditText userPass = findViewById(R.id.user_pass);
        Button loginBt = findViewById(R.id.btn_log);

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticate(userName.getText().toString(), userPass.getText().toString());
            }
        });


    }
    private void authenticate(String username, String password){
        if(checkAdminUser(username, password)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Usuário ou senha incorreta", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkAdminUser(String username, String password) {
        DataBaseHelper dbHelper = new DataBaseHelper(LoginActivity.this);
        return dbHelper.checkAdminUser(username, password);
    }
}