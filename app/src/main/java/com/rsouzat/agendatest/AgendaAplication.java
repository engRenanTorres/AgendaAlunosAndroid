package com.rsouzat.agendatest;

import android.app.Application;

import com.rsouzat.agendatest.DAO.AlunoDAO;
import com.rsouzat.agendatest.model.Aluno;

public class AgendaAplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    criaAlunosExemplo();

  }

  private void criaAlunosExemplo() {
    AlunoDAO dao = new AlunoDAO();
    dao.salva(new Aluno("Renan", "4612347", "renan@alex"));
    dao.salva(new Aluno("Fran", "4612347", "fran@alex"));
  }

}
