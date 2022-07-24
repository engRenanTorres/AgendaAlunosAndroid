package com.rsouzat.agendatest.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {
  @TypeConverter
  public Long toLong (Calendar calendar) {
    if(calendar != null)  return calendar.getTimeInMillis();
    return null;
  }
  @TypeConverter
  public Calendar toCalendar(Long valor) {
    Calendar momentoAtual = Calendar.getInstance();
    if(valor != null)   momentoAtual.setTimeInMillis(valor);
    return momentoAtual;
  }
}
