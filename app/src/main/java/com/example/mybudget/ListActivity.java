package com.example.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mybudget.dao.Usuariodao;
import com.example.mybudget.entity.Usuario;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listviewusuario = findViewById(R.id.listviewusuario);

        Usuariodao usuariodao = new Usuariodao();
        java.util.List<Usuario> usuarios = usuariodao.listar();

        ArrayAdapter<Usuario> arrayAdapterUsuario =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarios);

        listviewusuario.setAdapter(arrayAdapterUsuario);

        listviewusuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = (Usuario) parent.getItemAtPosition(position);
                usuariodao.atualizar(usuario);
                Toast.makeText(getApplicationContext(), "Usuário atualizado com sucesso", Toast.LENGTH_SHORT).show();

                // aqui
               

               
            }
        });

        listviewusuario.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = (Usuario) parent.getItemAtPosition(position);
                usuariodao.excluir(usuario);
                Toast.makeText(getApplicationContext(), "Usuário excluído com sucesso", Toast.LENGTH_SHORT).show();

                usuarios.clear();
                usuarios.addAll(usuariodao.listar());
                arrayAdapterUsuario.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void abrirLista(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}