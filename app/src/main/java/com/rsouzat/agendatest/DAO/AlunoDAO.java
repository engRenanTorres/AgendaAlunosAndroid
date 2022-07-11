package com.rsouzat.agendatest.DAO;

import androidx.annotation.Nullable;

import com.rsouzat.agendatest.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

  private final static List<Aluno> alunos = new ArrayList<>();
  private static int contadorDeIds = 1;

  public void salva(Aluno aluno) {
    aluno.setId(contadorDeIds);
    alunos.add(aluno);
    atualizaId();
  }

  private void atualizaId() {
    contadorDeIds++;
  }

  public void edita(Aluno aluno) {
    Aluno alunoEncontrado = buscaALunoPeloId(aluno);
    if (alunoEncontrado != null) {
      int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
      alunos.set(posicaoDoAluno, aluno);
    }
  }

  @Nullable
  private Aluno buscaALunoPeloId(Aluno aluno) {
    for (Aluno a : alunos) {
      if (a.getId() == aluno.getId()) {
        return a;
      }
    }
    return null;
  }

  public List<Aluno> todos() {
    return new ArrayList<>(alunos);
  }

  public void remove(Aluno aluno) {
    Aluno alunoDevolvido = buscaALunoPeloId(aluno);
    if(alunoDevolvido != null){
      alunos.remove(alunoDevolvido);
    }
  }
}
