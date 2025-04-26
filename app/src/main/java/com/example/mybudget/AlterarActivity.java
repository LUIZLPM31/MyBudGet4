package com.example.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mybudget.dao.Usuariodao;
import com.example.mybudget.entity.Usuario;

public class AlterarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alterar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referências aos campos de entrada
        EditText txtNome = findViewById(R.id.txtnome);
        EditText txtEmail = findViewById(R.id.txtemail);
        EditText txtLogin = findViewById(R.id.txtlogin);
        EditText txtSenha = findViewById(R.id.senha);

        // Recebe os dados do Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String nome = intent.getStringExtra("nome");
        String email = intent.getStringExtra("email");
        String login = intent.getStringExtra("login");
        String senha = intent.getStringExtra("senha");

        // Preenche os campos com os dados recebidos
        txtNome.setText(nome);
        txtEmail.setText(email);
        txtLogin.setText(login);
        txtSenha.setText(senha);

        // Configura o botão de atualizar
        Button btnAlterar = findViewById(R.id.btn_alterar);
        btnAlterar.setOnClickListener(view -> {
            // Atualiza os dados do usuário
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(txtNome.getText().toString());
            usuario.setEmail(txtEmail.getText().toString());
            usuario.setLogin(txtLogin.getText().toString());
            usuario.setSenha(txtSenha.getText().toString());

            Usuariodao usuariodao = new Usuariodao();
            usuariodao.atualizar(usuario);

            Toast.makeText(getApplicationContext(), "Usuário atualizado com sucesso!", Toast.LENGTH_SHORT).show();

            // Retorna para a ListActivity
            finish();
        });
    }

    public void Alterar(View view) {
    }
}