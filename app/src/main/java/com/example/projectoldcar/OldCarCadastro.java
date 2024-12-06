package com.example.projectoldcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;

public class OldCarCadastro extends AppCompatActivity {

    private static final HashSet<String> usuariosCadastrados = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_car_cadastro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editUsuario = findViewById(R.id.editUsuario);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenha = findViewById(R.id.editSenha);
        TextView mensagem = findViewById(R.id.mensagem);
        Button criar = findViewById(R.id.Criar);
        TextView agreeBox = findViewById(R.id.agreeBox);
        TextView voltar = findViewById(R.id.voltar);
        TextView dadosPessoais = findViewById(R.id.Dadospessoais);
        TextView entrardecadastro = findViewById(R.id.EntrarDeCadastro);

        // Configuração do botão "Voltar"
        voltar.setOnClickListener(v -> finish());

        // Configuração do botão "EntrarDeCadastro" - leva de volta à tela de login
        entrardecadastro.setOnClickListener(v -> {
            Intent intent = new Intent(OldCarCadastro.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finaliza a atividade atual (OldCarCadastro)
        });

        // Redireciona para a página de termos e condições
        dadosPessoais.setOnClickListener(v -> {
            String url = "https://www.gov.br/receitafederal/pt-br/acesso-a-informacao/lgpd/termo-de-uso";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(android.net.Uri.parse(url));
            startActivity(intent);
        });

        // Lógica de seleção do checkbox
        agreeBox.setOnClickListener(v -> {
            boolean isSelected = agreeBox.isSelected();
            agreeBox.setSelected(!isSelected);
            agreeBox.invalidate();
        });

        // Ação do botão "Criar"
        criar.setOnClickListener(v -> {
            String usuario = editUsuario.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString();

            // Verifica se o usuário concordou com os termos
            if (!agreeBox.isSelected()) {
                mensagem.setText("Você deve concordar com os termos para continuar.");
                mensagem.setVisibility(View.VISIBLE);
                return;
            }

            // Verifica se todos os campos foram preenchidos
            if (usuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                mensagem.setText("Todos os campos são obrigatórios.");
                mensagem.setVisibility(View.VISIBLE);
                return;
            }

            // Verifica se a senha tem pelo menos 8 caracteres
            if (senha.length() < 8) {
                mensagem.setText("A senha deve ter pelo menos 8 caracteres.");
                mensagem.setVisibility(View.VISIBLE);
                return;
            }

            // Verifica se o email já está cadastrado
            if (usuariosCadastrados.contains(email)) {
                mensagem.setText("A conta já existe. Tente outro email.");
                mensagem.setVisibility(View.VISIBLE);
                return;
            }

            // Exibe o ProgressBar enquanto simula o processo de criação da conta
            ProgressBar circulo = findViewById(R.id.Circulo);
            circulo.setVisibility(View.VISIBLE);

            // Simula um processo de criação de conta (em uma thread separada para evitar travar a UI)
            new Thread(() -> {
                try {
                    // Simula o tempo de processamento
                    Thread.sleep(2000); // 2 segundos

                    runOnUiThread(() -> {
                        // Adiciona o usuário à lista de cadastrados
                        usuariosCadastrados.add(email);

                        // Esconde o ProgressBar
                        circulo.setVisibility(View.INVISIBLE);

                        // Exibe uma mensagem de sucesso
                        Toast.makeText(this, "Conta criada com sucesso!", Toast.LENGTH_LONG).show();

                        // Redireciona para a tela principal após a criação da conta
                        Intent intent = new Intent(OldCarCadastro.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Finaliza a atividade atual
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> {
                        circulo.setVisibility(View.INVISIBLE);
                        mensagem.setText("Ocorreu um erro ao criar a conta. Tente novamente.");
                        mensagem.setVisibility(View.VISIBLE);
                    });
                }
            }).start();
        });
    }
}
