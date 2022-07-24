package com.rsouzat.agendatest;

import android.app.Application;

import com.rsouzat.agendatest.database.dao.AlunoDAO;
import com.rsouzat.agendatest.database.AgendaDatabase;
import com.rsouzat.agendatest.model.Aluno;

public class AgendaAplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
   // Application é utilizando apenas uma vez na criação do app criaAlunosExemplo();
  }

  private void criaAlunosExemplo() {
    AgendaDatabase database = AgendaDatabase.getInstance(this);
    AlunoDAO dao = database.getRoomAlunoDAO();
    dao.salva(new Aluno("Renan", "4612347", "renan@alex"));
    dao.salva(new Aluno("Fran", "4612347", "fran@alex"));
  }

}
