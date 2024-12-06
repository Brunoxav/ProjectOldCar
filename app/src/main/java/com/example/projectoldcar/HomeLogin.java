package com.example.projectoldcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_login);

        TextView btnEntrar = findViewById(R.id.btnEntrar);
        TextView btnCadastrar = findViewById(R.id.btnCadastrar);

        Intent intent = new Intent(HomeLogin.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeLogin.this, OldCarCadastro.class);
                startActivity(intent);
            }
        });
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeLogin.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
