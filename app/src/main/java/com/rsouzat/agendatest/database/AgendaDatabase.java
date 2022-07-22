package com.rsouzat.agendatest.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rsouzat.agendatest.dao.AlunoDAO;
import com.rsouzat.agendatest.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

  private static final String NAME_DB = "agenda.db";

  public abstract AlunoDAO getRoomAlunoDAO();

  public static AgendaDatabase getInstance(Context context){
    AgendaDatabase database = Room.databaseBuilder(context, AgendaDatabase.class, NAME_DB)
            .allowMainThreadQueries()
            .build();
    return database;
  }
}
