package com.rsouzat.agendatest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rsouzat.agendatest.R;
import com.rsouzat.agendatest.model.Aluno;

import java.util.ArrayList;
import java.util.List;


public class ListaAlunosAdapter extends BaseAdapter {
  private final List<Aluno> alunos = new ArrayList<>();
  private final Context context;

  public ListaAlunosAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return alunos.size();
  }

  @Override
  public Aluno getItem(int posicao) {
    return alunos.get(posicao);
  }

  @Override
  public long getItemId(int posicao) {
    return alunos.get(posicao).getId();
  }

  @Override
  public View getView(int posicao, View view, ViewGroup viewGroup) {


    View viewCriada = criaView(viewGroup);
    Aluno aluno = alunos.get(posicao);
    vincula(viewCriada, aluno);
    return viewCriada;
  }

  private void vincula(View viewCriada, Aluno aluno) {
    TextView nomeAluno = viewCriada.findViewById(R.id.item_nome_aluno);
    nomeAluno.setText(aluno.getNome());
    TextView telefoneAluno = viewCriada.findViewById(R.id.item_telefone_aluno);
    telefoneAluno.setText(aluno.getTelefone());
  }

  private View criaView(ViewGroup viewGroup) {
    return LayoutInflater
        .from(context)
        .inflate(R.layout.item_aluno, viewGroup, false);
  }

  public void atualiza(List<Aluno> alunos) {
    this.alunos.clear();
    this.alunos.addAll(alunos);
    notifyDataSetChanged();
  }

  public void remove(Aluno aluno) {
    alunos.remove(aluno);
    notifyDataSetChanged();
  }
}
