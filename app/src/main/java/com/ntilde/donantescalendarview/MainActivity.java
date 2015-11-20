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
        findViewById(R.id.addEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date selectedDate = calendar.getSelectedDate();

                DonantesCalendarRange rangoRojo=new DonantesCalendarRange(1, DonantesCalendarRange.UNITS.MONTHS, Color.rgb(212, 0, 0),"No puedes donar nada");
                DonantesCalendarRange rangoNaranja=new DonantesCalendarRange(14, DonantesCalendarRange.UNITS.DAYS, Color.rgb(255, 221, 85),"No puedes donar sangre");
                calendar.addEvent(new DonantesCalendarEvent("Sangre", selectedDate, Color.rgb(0, 128, 0), rangoRojo, rangoNaranja));

                //calendar.addEvent(new DonantesCalendarEvent("Sangre", selectedDate, Color.rgb(0, 128, 0), Color.rgb(255, 221, 85), 14, Color.rgb(212, 0, 0), 7));
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
