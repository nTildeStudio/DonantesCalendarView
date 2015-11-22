package com.ntilde.donantescalendarview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DonantesCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = (DonantesCalendarView) findViewById(R.id.calendar);

//        Calendar enero2016= Calendar.getInstance();
//        enero2016.set(Calendar.YEAR, 2016);
//        enero2016.set(Calendar.MONTH, Calendar.JANUARY);
//        enero2016.set(Calendar.DAY_OF_MONTH, 1);
//        enero2016.set(Calendar.HOUR_OF_DAY, 0);
//        enero2016.set(Calendar.MINUTE, 0);
//        enero2016.set(Calendar.SECOND, 0);
//        enero2016.set(Calendar.MILLISECOND, 0);
//        DonantesCalendarRange eventoEnero2016=new DonantesCalendarRange(enero2016.getTime(), 1, DonantesCalendarRange.UNITS.MONTHS, Color.MAGENTA);
//        calendar.addEvent(new DonantesCalendarEvent("TEST", eventoEnero2016));

        findViewById(R.id.addEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date selectedDate = calendar.getSelectedDate();
                DonantesCalendarRange evento=new DonantesCalendarRange(selectedDate, 1, DonantesCalendarRange.UNITS.DAYS, Color.rgb(0, 128, 0));
                DonantesCalendarRange rangoRojo=new DonantesCalendarRange(1, DonantesCalendarRange.UNITS.MONTHS, Color.rgb(212, 0, 0),"No puedes donar nada");
                DonantesCalendarRange rangoNaranja=new DonantesCalendarRange(14, DonantesCalendarRange.UNITS.DAYS, Color.rgb(255, 221, 85),"No puedes donar sangre");
                calendar.addEvent(new DonantesCalendarEvent("Sangre", evento, rangoRojo, rangoNaranja));
            }
        });
        findViewById(R.id.toggleMonthName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setDisplayMonthName(!calendar.isDisplayMonthName());
            }
        });
        findViewById(R.id.toggleDaysName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setDisplayDaysName(!calendar.isDisplayDaysName());
            }
        });
        findViewById(R.id.logEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonantesCalendarEvent event = calendar.getSelectedEvent();
                if(event==null){
                    Log.e("XXX", "No hay evento!!");
                }
                else{
                    Log.e("XXX", "Evento: "+event.getEventInfo());
                }
            }
        });
        calendar.setOnSelectedDateChangeListener(new DonantesCalendarView.OnSelectedDateChangeListener() {
            @Override
            public void OnSelectedDateChange(Date selectedDate, DonantesCalendarEvent event, DonantesCalendarRange range) {
                if(event!=null){
                    Log.e("XXX", "Evento: "+event.getEventInfo());
                }
                if(range!=null){
                    Log.e("XXX", "Rango: "+range.getMessage());
                }
            }
        });
    }
}
