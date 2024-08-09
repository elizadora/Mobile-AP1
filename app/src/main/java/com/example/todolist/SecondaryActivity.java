package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.todolist.dao.TarefaDao;
import com.example.todolist.database.AppDatabase;
import com.example.todolist.models.Tarefa;

public class SecondaryActivity extends AppCompatActivity {

    TarefaDao tarefaDao;
    Button btnSalvar;
    EditText titulo;
    EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cadastrar_tarefa);

        btnSalvar = findViewById(R.id.btn_salvar);
        titulo = findViewById(R.id.txt_titulo);
        descricao = findViewById(R.id.txt_descricao);


        // create info about database use
        AppDatabase appDatabase = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "db_tarefass")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        tarefaDao = appDatabase.tarefaDao();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloS = titulo.getText().toString().trim();
                String descricaoS = descricao.getText().toString().trim();

                if(tituloS.isEmpty() || descricaoS.isEmpty()){
                    Toast.makeText(v.getContext(), "Preencha todos os campos para inserir uma tarefa", Toast.LENGTH_SHORT).show();

                }else{
                    Tarefa tarefa = new Tarefa(tituloS, descricaoS);

                    tarefaDao.insertAll(tarefa);

                    Intent call = new Intent(SecondaryActivity.this, MainActivity.class);
                    startActivity(call);
                }
            }
        });
    }
}
