package com.rsouzat.agendatest.ui.activity;

import static com.rsouzat.agendatest.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsouzat.agendatest.R;
import com.rsouzat.agendatest.model.Aluno;
import com.rsouzat.agendatest.ui.ListaAlunosView;

public class ListaAlunosActivity extends AppCompatActivity {

  public static final String TITULO_APPBAR = "Lista de alunos";
  private ListaAlunosView listaAlunosView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_alunos);
    listaAlunosView = new ListaAlunosView(this);

    setTitle(TITULO_APPBAR);

    configuraFABNovoAluno();
    configuraLista();

  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    getMenuInflater().inflate(R.menu.activity_lista_alunos, menu);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {

    int itemId = item.getItemId();
    if (itemId == R.id.activity_lista_alunos_menu_remover) {
      listaAlunosView.confirmaRemocao(item);
    }
    return super.onContextItemSelected(item);
  }

  private void configuraFABNovoAluno() {
    FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fAB_novo_aluno);
    botaoNovoAluno.setOnClickListener(view -> {
      /*tipo um react navegation*/
      startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    listaAlunosView.atualizaAlunos();
  }

  private void configuraLista() {
    ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
    listaAlunosView.configuraAdapter(listaDeAlunos);
    configuraListenerDeCliquePorItem(listaDeAlunos);
    registerForContextMenu(listaDeAlunos);
  }

  private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
    listaDeAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
      Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
      Log.i("aluno", "" + alunoEscolhido);
      abreFormularioModoEdit(alunoEscolhido);
    });
  }

  private void abreFormularioModoEdit(Aluno alunoEscolhido) {
    Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this,
            FormularioAlunoActivity.class);
    startActivity(vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido));
  }

}
