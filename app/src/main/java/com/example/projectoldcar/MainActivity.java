package com.example.projectoldcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btEntrar;
    private TextView esqueceuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando as views
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btEntrar = findViewById(R.id.btEntrar);
        esqueceuSenha = findViewById(R.id.esqueceuasenha);

        // Botão "Entrar"
        btEntrar.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            } else {
                verificarUsuario(email, senha);
            }
        });

        // Link para "Esqueceu a senha?"
        esqueceuSenha.setOnClickListener(v -> {
            // Redirecionando para a tela de recuperação de senha
            Intent intent = new Intent(MainActivity.this, RecuperarSenha.class);
            startActivity(intent);
        });

        // Botão Voltar
        TextView voltar = findViewById(R.id.voltar);
        voltar.setOnClickListener(v -> finish());

        // Definindo ação para o botão "Cadastrar Se"
        TextView cadastraSe = findViewById(R.id.CadastrarSe);
        cadastraSe.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OldCarCadastro.class);
            startActivity(intent);
        });
    }

    // Função que verifica se o usuário existe
    private void verificarUsuario(String email, String senha) {
        // Aqui, você pode implementar a verificação de login.
        // Por exemplo, checando em um banco de dados local ou remoto.
        // Para fins de exemplo, consideraremos um usuário fictício:
        String usuarioExistente = "usuario@exemplo.com";
        String senhaCorreta = "12345";

        if (email.equals(usuarioExistente) && senha.equals(senhaCorreta)) {
            // Se o usuário for encontrado e a senha estiver correta, redireciona para a tela principal
            Intent intent = new Intent(MainActivity.this, parteprincipal.class);
            startActivity(intent);
            finish();
        } else {
            // Se o usuário não existir ou a senha estiver errada
            Toast.makeText(MainActivity.this, "Usuário não existe", Toast.LENGTH_SHORT).show();
        }
    }
}
