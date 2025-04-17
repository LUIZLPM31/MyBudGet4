package com.example.mybudget;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mybudget.dao.Usuariodao;
import com.example.mybudget.entity.Usuario;
import com.example.mybudget.sqlite.Conexao;

public class MainActivity extends AppCompatActivity {

    private Toast Toas;

    public void salvar(View view){
        EditText txtnome = findViewById(R.id.txtnome);
        EditText txtemail = findViewById(R.id.txtemail);
        EditText txtlogin = findViewById(R.id.txtlogin);
        EditText senha = findViewById(R.id.senha);

        if (txtnome.getText().toString().isEmpty() || txtemail.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
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

        new Conexao(getApplicationContext(), "mybudget.db", null, 1);

    }
}