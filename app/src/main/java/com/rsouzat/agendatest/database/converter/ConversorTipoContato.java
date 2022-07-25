package com.rsouzat.agendatest.database.converter;


import androidx.room.TypeConverter;

import com.rsouzat.agendatest.model.TipoContato;

public class ConversorTipoContato {
  @TypeConverter
  public String toString (TipoContato tipo) {
    return tipo.name();
  }
  @TypeConverter
  public TipoContato toTipoContato (String valor){
    if(valor != null) return TipoContato.valueOf(valor);
    return null;
  }
}
