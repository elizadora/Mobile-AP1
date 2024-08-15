package com.example.todolist;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todolist.dao.TarefaDao;
import com.example.todolist.database.AppDatabase;
import com.example.todolist.models.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvTarefas;
    Button btnCriar;
    ImageButton ibDelete;


    List<Tarefa> tarefasDoBd;
    TarefaDao tarefaDao;
    ArrayList<Item> itemList = new ArrayList<Item>();
    ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnCriar = (Button) findViewById(R.id.btn_criar);
        ibDelete = (ImageButton) findViewById(R.id.ib_delete);

        rvTarefas = findViewById(R.id.rv_tarefas);
        itemArrayAdapter = new ItemArrayAdapter(R.layout.item, itemList);

        rvTarefas = (RecyclerView) findViewById(R.id.rv_tarefas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTarefas.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());

//        rvTarefas.addItemDecoration(dividerItemDecoration);

        rvTarefas.setAdapter(itemArrayAdapter);

        // create info about database use
        AppDatabase appDatabase = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "db_tarefass")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();


        tarefaDao = appDatabase.tarefaDao();
        tarefasDoBd = tarefaDao.getallTarefas();
        for(Tarefa p : tarefasDoBd){
            itemList.add(new Item(p.titulo, p.descricao));
        }

        swipeToDelete();

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(MainActivity.this, SecondaryActivity.class);
                startActivity(call);
            }
        });


        ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarefaDao.deleteAll();

                tarefasDoBd.clear();
                itemList.clear();

                itemArrayAdapter.notifyDataSetChanged();

            }
        });
    }


    private void swipeToDelete(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // get position item in recyclerview
                int position = viewHolder.getAdapterPosition();

                Tarefa pessoa = tarefasDoBd.get(position);

                tarefaDao.delete(pessoa);

                tarefasDoBd.remove(position);

                itemList.remove(position);


                itemArrayAdapter.notifyItemRemoved(position);
                itemArrayAdapter.notifyItemRangeChanged(position, itemList.size());
            }
        }).attachToRecyclerView(rvTarefas);
    }

}