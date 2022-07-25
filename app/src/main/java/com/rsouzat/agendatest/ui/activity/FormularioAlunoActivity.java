package com.rsouzat.agendatest.ui.activity;

import static com.rsouzat.agendatest.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rsouzat.agendatest.R;
import com.rsouzat.agendatest.database.dao.AlunoDAO;
import com.rsouzat.agendatest.database.AgendaDatabase;
import com.rsouzat.agendatest.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

  private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
  private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
  private EditText campoNome;
  private EditText campoTelefone;
  private EditText campoCelular;
  private EditText campoEmail;
  private Aluno aluno;
  private AlunoDAO dao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_formulario_aluno);
    AgendaDatabase database = AgendaDatabase.getInstance(this);
    dao = database.getRoomAlunoDAO();


    inicializacaoDosCampos();

    carregaAluno();

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu,menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int itemId = item.getItemId();
    if(itemId == R.id.activity_formulario_aluno_menu_salvar){
      finalizaFormulario();
    }
    return super.onOptionsItemSelected(item);
  }

  private void carregaAluno() {
    Intent dados = getIntent();
    if (dados.hasExtra(CHAVE_ALUNO)) {
      setTitle(TITULO_APPBAR_EDITA_ALUNO);
      aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
      preencheCampos();
    } else {
      setTitle(TITULO_APPBAR_NOVO_ALUNO);
      aluno = new Aluno();
    }
  }

  private void preencheCampos() {
    campoNome.setText(aluno.getNome());
/*    campoTelefone.setText(aluno.getTelefone());
    campoCelular.setText(aluno.getCelular());*/
    campoEmail.setText(aluno.getEmail());
  }

  private void finalizaFormulario() {
    preencheAluno();
    if (aluno.hasId()) dao.edita(aluno);
    else dao.salva(aluno);
    finish();
  }

  private void inicializacaoDosCampos() {
    campoNome = findViewById(R.id.activity_formulario_aluno_nome);
    campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
    campoCelular = findViewById(R.id.activity_formulario_aluno_celular);
    campoEmail = findViewById(R.id.activity_formulario_aluno_email);
  }


  private void preencheAluno() {
    String nome = campoNome.getText().toString();
    String telefone = campoTelefone.getText().toString();
    String celular = campoCelular.getText().toString();
    String email = campoEmail.getText().toString();

    aluno.setNome(nome);
//    aluno.setTelefone(telefone);
//    aluno.setCelular(celular);
    aluno.setEmail(email);

  }
}