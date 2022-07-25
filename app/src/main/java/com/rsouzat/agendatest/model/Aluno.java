package com.rsouzat.agendatest.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Entity
public class Aluno implements Serializable {
  @PrimaryKey(autoGenerate = true)
  private int id = 0;
  private String nome;
  private String email;
  private Calendar momentoDeCadastro = Calendar.getInstance();


  @Ignore
  public Aluno(String nome, String email) {
    this.nome = nome;
    this.email = email;
  }

  public Aluno() {
  }

  public Calendar getMomentoDeCadastro() {
    return momentoDeCadastro;
  }

  public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
    this.momentoDeCadastro = momentoDeCadastro;
  }


  public void setNome(String nome) {
    this.nome = nome;
  }


  public void setEmail(String email) {
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  @NonNull
  @Override
  public String toString() {
    return "nome: " + nome +
            ", email: " + email;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public boolean hasId() {
    return id > 0;
  }

  public String getNomeCompleto() {
    return (nome);
  }

  public String dataFormatada() {
    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    return formatador.format(momentoDeCadastro.getTime());
  }
}
