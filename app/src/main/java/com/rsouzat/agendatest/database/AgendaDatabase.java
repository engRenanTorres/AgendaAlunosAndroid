package com.rsouzat.agendatest.database;


import static com.rsouzat.agendatest.database.AgendaMigrations.MIGRATIONS_TABLE_ALUNOS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rsouzat.agendatest.database.converter.ConversorTipoContato;
import com.rsouzat.agendatest.database.dao.AlunoDAO;
import com.rsouzat.agendatest.database.converter.ConversorCalendar;
import com.rsouzat.agendatest.model.Aluno;
import com.rsouzat.agendatest.model.Contato;

@Database(entities = {Aluno.class, Contato.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoContato.class})
public abstract class AgendaDatabase extends RoomDatabase {

  private static final String NAME_DB = "agenda.db";


  public abstract AlunoDAO getRoomAlunoDAO();

  public static AgendaDatabase getInstance(Context context){
    AgendaDatabase database = Room.databaseBuilder(context, AgendaDatabase.class, NAME_DB)
            .allowMainThreadQueries()
            .addMigrations(MIGRATIONS_TABLE_ALUNOS)
            .build();
    return database;
  }

  public abstract ContatoDAO getTelefoneDAO();
}
