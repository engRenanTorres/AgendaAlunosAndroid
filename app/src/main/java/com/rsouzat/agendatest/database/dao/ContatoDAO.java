package com.rsouzat.agendatest.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rsouzat.agendatest.model.Contato;

import java.util.List;

@Dao
public interface ContatoDAO {
  @Query("SELECT * FROM Contato c " +
          "WHERE alunoId = :alunoId " +
          "LIMIT 1")
  Contato buscaPrimeiroTelefoneDoAluno(int alunoId);
  @Insert
   void salva(Contato... telefones);

  @Query("SELECT * FROM Contato c " +
          "WHERE alunoId = :alunoId" )
  List<Contato> buscaTodosTelefonesDoAluno(int alunoId);
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void atualiza(Contato... telefones);
}
