package com.example.todolist.dao;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todolist.models.Tarefa;

@Dao
public interface TarefaDao {
    @Insert
    void insertAll(Tarefa... tarefa);

    @Delete
    void delete(Tarefa tarefa);

    @Query("SELECT * FROM tarefa")
    List<Tarefa> getallTarefas();

    @Query("DELETE FROM tarefa")
    void deleteAll();
}
