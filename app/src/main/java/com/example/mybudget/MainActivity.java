package com.example.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mybudget.dao.Usuariodao;
import com.example.mybudget.entity.Usuario;
import com.example.mybudget.sqlite.Conexao;

import org.jetbrains.annotations.Contract;

public class MainActivity extends AppCompatActivity {

    public Button segundatela;

    public void salvar(View view){
        EditText txtnome = findViewById(R.id.txtnome);
        EditText txtemail = findViewById(R.id.txtemail);
        EditText txtlogin = findViewById(R.id.txtlogin);
        EditText senha = findViewById(R.id.senha);

        if (txtnome.getText().toString().isEmpty() || 
            txtemail.getText().toString().isEmpty() || 
            txtlogin.getText().toString().isEmpty() || 
            senha.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;

        }
        if (senha.getText().toString().length() < 6) {
            Toast.makeText(this, "A senha deve ter no mínimo 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!senha.getText().toString().equals(senha.getText().toString())) {
            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            return;
        }
        if (senha.getText().toString().contains(" ")) {
            Toast.makeText(this, "A senha não pode conter espaços", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!senha.getText().toString().matches(".*[A-Z].*")) {
            Toast.makeText(this, "A senha deve conter pelo menos uma letra maiúscula", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!senha.getText().toString().matches(".*[a-z].*")) {
            Toast.makeText(this, "A senha deve conter pelo menos uma letra minúscula", Toast.LENGTH_SHORT).show();
            return;
        }


        Usuario usuario = new Usuario();
        usuario.setNome(txtnome.getText().toString());
        usuario.setEmail(txtemail.getText().toString());
        usuario.setLogin(txtlogin.getText().toString());
        usuario.setSenha(senha.getText().toString());

        Usuariodao dao = new Usuariodao();
        dao.salvar(usuario);

        Toast.makeText(getApplicationContext(), "Usuário salvo com sucesso", Toast.LENGTH_SHORT).show();

        txtnome.setText("");
        txtemail.setText("");
        txtlogin.setText("");
        senha.setText("");
        txtnome.requestFocus();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            new Conexao(getApplicationContext(), "mybudget.db", null, 1);
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao inicializar o banco de dados: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        segundatela = findViewById(R.id.segundatela);

        segundatela.setOnClickListener(view -> {
            startActivity(new Intent(this, ListActivity.class));
        });

    }



    }

