package com.rsouzat.agendatest.model;

import static androidx.room.ForeignKey.*;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Contato {
  @PrimaryKey(autoGenerate = true)
  private int id;
  private String numero;
  private TipoContato tipo;

  public int getAlunoId() {
    return alunoId;
  }

  public void setAlunoId(int alunoId) {
    this.alunoId = alunoId;
  }

  @ForeignKey(entity = Aluno.class,
    parentColumns = "id",
    childColumns = "alunoId",
    onUpdate = CASCADE,
    onDelete = CASCADE)
  private int alunoId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public TipoContato getTipo() {
    return tipo;
  }

  public void setTipo(TipoContato tipo) {
    this.tipo = tipo;
  }


}
