package com.example.todolist.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolist.dao.TarefaDao;
import com.example.todolist.models.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TarefaDao tarefaDao();
}
