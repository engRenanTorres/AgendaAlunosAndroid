package com.rsouzat.agendatest.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.rsouzat.agendatest.model.Contato;
@Dao
public interface ContatoDAO {
  @Query("SELECT c.* FROM Contato c " +
          "JOIN Aluno a " +
          "ON c.alunoId = a.id " +
          "WHERE c.alunoId = :alunoId " +
          "LIMIT 1")
  Contato buscaPrimeiroTelefoneDoAluno(int alunoId);
}
