package com.rsouzat.agendatest.database;

import static com.rsouzat.agendatest.model.TipoContato.TELEFONE;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.rsouzat.agendatest.model.TipoContato;

public class AgendaMigrations {
  private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
    }
  };
  private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      //SQLite não tem a funcao drop exclusivamente para colunas
      //Portanto precisa criar uma nova tabela sem a coluna que deseja excluir
      database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
              "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
              "`nome` TEXT, " +
              "`telefone` TEXT, " +
              "`email` TEXT)");
      //depois copiar os dados da tabela antiga pra a nova
      database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
              "SELECT id, nome, telefone, email FROM Aluno");
      //remover tabela antiga
      database.execSQL("DROP TABLE Aluno");
      //Renomear a tabela nova com o nome da tabela antiga
      database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
    }
  };
  private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
    }
  };
  private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE Aluno ADD COLUMN celular TEXT");
    }
  };
  private static final Migration MIGRATION_5_6 = new Migration(5,6) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
              "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
              "`nome` TEXT, " +
              "`email` TEXT, " +
              "`momentoDeCadastro` INTEGER)");
      database.execSQL("INSERT INTO Aluno_novo (id, nome, email,momentoDeCadastro)" +
              "SELECT id, nome, email, momentoDeCadastro FROM Aluno");

      database.execSQL("CREATE TABLE IF NOT EXISTS `Contato` " +
              "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
              "`numero` TEXT, " +
              "`tipo` TEXT, " +
              "`alunoId` INTEGER NOT NULL)");
      database.execSQL("INSERT INTO Contato ( numero, alunoId)" +
              "SELECT telefone, id FROM Aluno");
      database.execSQL("UPDATE Contato SET tipo = ?",new TipoContato[] {TELEFONE});

      database.execSQL("DROP TABLE Aluno");

      database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
    }
  }
          ;
  public static final Migration[] MIGRATIONS_TABLE_ALUNOS = {MIGRATION_1_2,
          MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6};
}
