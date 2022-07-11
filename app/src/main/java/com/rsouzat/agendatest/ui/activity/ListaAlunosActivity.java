package com.rsouzat.agendatest.ui.activity;

import static com.rsouzat.agendatest.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsouzat.agendatest.DAO.AlunoDAO;
import com.rsouzat.agendatest.R;
import com.rsouzat.agendatest.model.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

  public static final String TITULO_APPBAR = "Lista de alunos";
  private ArrayAdapter<Aluno> adapter;
  AlunoDAO dao = new AlunoDAO();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_alunos);

    setTitle(TITULO_APPBAR);
    dao.salva(new Aluno("Renan", "4612347", "renan@alex"));
    dao.salva(new Aluno("Fran", "4612347", "fran@alex"));

/*        List<String> alunos = new ArrayList<String>(
                Arrays.asList("Renan","Jessica","José","Maria", "Ana"));
        TextView primeiroAluno = findViewById(R.id.textView);
        TextView segundoAluno = findViewById(R.id.textView2);
        TextView terceiroAluno = findViewById(R.id.textView3);
        primeiroAluno.setText(alunos.get(0));
        segundoAluno.setText(alunos.get(1));
        terceiroAluno.setText(alunos.get(2));*/


    configuraFABNovoAluno();
    configuraLista();

  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    getMenuInflater().inflate(R.menu.activity_lista_alunos, menu);
  }

  @Override
  public boolean onContextItemSelected(@NonNull MenuItem item) {

    int itemId = item.getItemId();
    if (itemId == R.id.activity_lista_alunos_menu_remover) {
      AdapterView.AdapterContextMenuInfo menuInfo =
              (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
      Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
      remove(alunoEscolhido);
    }
    return super.onContextItemSelected(item);
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
    adapter.clear();
    adapter.addAll(dao.todos());
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
    adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1);
    listaDeAlunos.setAdapter(adapter);
  }
}
