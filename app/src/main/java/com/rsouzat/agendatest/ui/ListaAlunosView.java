package com.rsouzat.agendatest.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rsouzat.agendatest.database.dao.AlunoDAO;
import com.rsouzat.agendatest.database.AgendaDatabase;
import com.rsouzat.agendatest.model.Aluno;
import com.rsouzat.agendatest.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

  private final Context context;
  private final ListaAlunosAdapter adapter;
  private final AlunoDAO dao;

  public ListaAlunosView(Context context) {
    this.context = context;
    this.adapter = new ListaAlunosAdapter(this.context);
    dao = AgendaDatabase.getInstance(context)
            .getRoomAlunoDAO();
  }

  public void confirmaRemocao(final MenuItem item) {
    new AlertDialog
        .Builder(context)
        .setTitle("Removendo aluno")
        .setMessage("Tem certeza que quer remover o aluno?")
        .setPositiveButton("Sim", (dialogInterface, i) -> {
          AdapterView.AdapterContextMenuInfo menuInfo =
              (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
          Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
          remove(alunoEscolhido);
        })
        .setNegativeButton("Não",null)
        .show();
  }

  public void atualizaAlunos() {
    adapter.atualiza(dao.todos());
  }

  private void remove(Aluno alunoEscolhido) {
    dao.remove(alunoEscolhido);
    adapter.remove(alunoEscolhido);
  }

  public void configuraAdapter(ListView listaDeAlunos) {
    listaDeAlunos.setAdapter(adapter);
  }
}
