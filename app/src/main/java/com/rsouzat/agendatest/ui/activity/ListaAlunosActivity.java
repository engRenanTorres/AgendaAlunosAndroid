package com.rsouzat.agendatest.ui.activity;

import static com.rsouzat.agendatest.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsouzat.agendatest.DAO.AlunoDAO;
import com.rsouzat.agendatest.R;
import com.rsouzat.agendatest.model.Aluno;
import com.rsouzat.agendatest.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosActivity extends AppCompatActivity {

  public static final String TITULO_APPBAR = "Lista de alunos";
  private ListaAlunosAdapter adapter;
  final AlunoDAO dao = new AlunoDAO();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_alunos);

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
      confirmaRemocao(item);
    }
    return super.onContextItemSelected(item);
  }

  private void confirmaRemocao(final MenuItem item) {
    new AlertDialog
        .Builder(this)
        .setTitle("Removendo aluno")
        .setMessage("Tem certeza que quer remover o aluno?")
        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remove(alunoEscolhido);
          }
        })
        .setNegativeButton("Não",null)
        .show();
  }

  private void configuraFABNovoAluno() {
    FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fAB_novo_aluno);
    botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        /*tipo um react navegation*/
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    atualizaAlunos();
  }

  private void atualizaAlunos() {
    adapter.atualiza(dao.todos());
  }

  private void configuraLista() {
    ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
    configuraAdapter(listaDeAlunos);
    configuraListenerDeCliquePorItem(listaDeAlunos);
    registerForContextMenu(listaDeAlunos);
  }


  private void remove(Aluno alunoEscolhido) {
    dao.remove(alunoEscolhido);
    adapter.remove(alunoEscolhido);
  }

  private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
    listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView,
                              View view,
                              int posicao,
                              long id) {
        Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
        Log.i("aluno", "" + alunoEscolhido);
        abreFormularioModoEdit(alunoEscolhido);
      }
    });
  }

  private void abreFormularioModoEdit(Aluno alunoEscolhido) {
    Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this,
            FormularioAlunoActivity.class);
    startActivity(vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido));
  }

  private void configuraAdapter(ListView listaDeAlunos) {

    adapter = new ListaAlunosAdapter(this);
    listaDeAlunos.setAdapter(adapter);
  }
}
