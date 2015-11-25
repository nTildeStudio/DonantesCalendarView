package com.ntilde.donantescalendarview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
                if(calendar.isSelectedDate()) {
                    Date selectedDate = calendar.getSelectedDate();
                    DonantesCalendarRange evento = new DonantesCalendarRange(selectedDate, 1, DonantesCalendarRange.UNITS.DAYS, Color.rgb(0, 128, 0));
                    DonantesCalendarRange rangoRojo = new DonantesCalendarRange(14, DonantesCalendarRange.UNITS.DAYS, Color.rgb(212, 0, 0), "No puedes donar nada");
                    DonantesCalendarRange rangoNaranja = new DonantesCalendarRange(1, DonantesCalendarRange.UNITS.MONTHS, Color.rgb(255, 221, 85), "No puedes donar sangre");
                    calendar.addEvent(new DonantesCalendarEvent("Sangre", evento, rangoNaranja, rangoRojo));
                }
                else if(calendar.isSelectedRange()){
                    Date[] selectedRange = calendar.getSelectedRange();
                    int days = (int)(Math.abs(selectedRange[0].getTime()-selectedRange[1].getTime())/(24*60*60*1000))+1;
                    Log.e("XXX", "Dias: "+days);
                    DonantesCalendarRange evento = new DonantesCalendarRange(selectedRange[0], days, DonantesCalendarRange.UNITS.DAYS, Color.rgb(0, 128, 0));
                    calendar.addEvent(new DonantesCalendarEvent("Sangre", evento));
                }
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
                List<DonantesCalendarEvent> events = calendar.getSelectedEvent();
                if(events==null||events.size()==0){
                    Log.e("XXX", "No hay evento!!");
                }
                else{
                    for(DonantesCalendarEvent event:events) {
                        Log.e("XXX", "Evento: " + event.getEventInfo());
                    }
                }
            }
        });
        calendar.setOnSelectedDateChangeListener(new DonantesCalendarView.OnSelectedDateChangeListener() {
            @Override
            public void OnSelectedDateChange(Date selectedDateStart, Date selectedDateEnd, List<DonantesCalendarEvent> events, List<DonantesCalendarRange> ranges) {
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                if(selectedDateStart==null){
                    Log.e("XXX", "Nada seleccionado");
                }
                else if(selectedDateStart.getTime()==selectedDateEnd.getTime()){
                    Log.e("XXX", "Seleccionado un dia: "+format1.format(selectedDateStart));
                }
                else{
                    Log.e("XXX", "Seleccionado un rango: "+format1.format(selectedDateStart)+" - "+format1.format(selectedDateEnd));
                }
                if(events!=null){
                    for(DonantesCalendarEvent event:events) {
                        if(event!=null) {
                            Log.e("XXX", "Evento: " + event.getEventInfo());
                        }
                    }
                }
                if(ranges!=null){
                    for(DonantesCalendarRange range:ranges) {
                        if(range!=null) {
                            Log.e("XXX", "Rango: " + range.getMessage());
                        }
                    }
                }
            }
        });
        findViewById(R.id.multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setMultitouch(!calendar.isMultitouch());
            }
        });
        findViewById(R.id.weekends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setHighlightWeekend(!calendar.isHighlightWeekend());
            }
        });

        ((Spinner)findViewById(R.id.firstDay)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(view!=null) {
                    switch (((TextView) view).getText().toString()) {
                        case "Monday":calendar.setFirstDayOfWeek(Calendar.MONDAY);break;
                        case "Tuesday":calendar.setFirstDayOfWeek(Calendar.TUESDAY);break;
                        case "Wednesday":calendar.setFirstDayOfWeek(Calendar.WEDNESDAY);break;
                        case "Thursday":calendar.setFirstDayOfWeek(Calendar.THURSDAY);break;
                        case "Friday":calendar.setFirstDayOfWeek(Calendar.FRIDAY);break;
                        case "Saturday":calendar.setFirstDayOfWeek(Calendar.SATURDAY);break;
                        case "Sunday":calendar.setFirstDayOfWeek(Calendar.SUNDAY);break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ((Spinner)findViewById(R.id.theme)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(view!=null){
                    switch (((TextView) view).getText().toString()) {
                        case "White":
                            calendar.setBackgroundColor(Color.WHITE);
                            calendar.setMonthTextColor(Color.BLACK);
                            calendar.setMonthSelectedTextColor(Color.GRAY);
                            calendar.setDayNameTextColor(Color.GRAY);
                            calendar.setDayBoxBackgroundColor(Color.rgb(230,230,230));
                            calendar.setDaySelectedBoxBackgroundColor(Color.rgb(230,230,230));
                            calendar.setDayTextColor(Color.BLACK);
                            calendar.setDayWeekendTextColor(Color.GRAY);
                            calendar.setDayBoxBorderColor(Color.WHITE);
                            calendar.setDaySelectedCircleBackgroundColor(Color.WHITE);
                            calendar.setDayPreselectedCircleBackgroundColor(Color.rgb(245, 245, 245));
                            calendar.setDaySelectedTextColor(Color.BLACK);
                            break;
                        case "Black":
                            calendar.setBackgroundColor(Color.rgb(66,66,66));
                            calendar.setMonthTextColor(Color.WHITE);
                            calendar.setMonthSelectedTextColor(Color.LTGRAY);
                            calendar.setDayNameTextColor(Color.LTGRAY);
                            calendar.setDayBoxBackgroundColor(Color.rgb(44,44,44));
                            calendar.setDaySelectedBoxBackgroundColor(Color.rgb(44,44,44));
                            calendar.setDayTextColor(Color.WHITE);
                            calendar.setDayWeekendTextColor(Color.GRAY);
                            calendar.setDayBoxBorderColor(Color.LTGRAY);
                            calendar.setDaySelectedCircleBackgroundColor(Color.rgb(225, 225, 225));
                            calendar.setDayPreselectedCircleBackgroundColor(Color.rgb(205, 205, 205));
                            calendar.setDaySelectedTextColor(Color.BLACK);
                            break;
                        case "Red":
                            calendar.setBackgroundColor(Color.WHITE);
                            calendar.setMonthTextColor(Color.rgb(200,0,0));
                            calendar.setMonthSelectedTextColor(Color.rgb(250,50,50));
                            calendar.setDayNameTextColor(Color.rgb(200,0,0));
                            calendar.setDayBoxBackgroundColor(Color.rgb(255,0,0));
                            calendar.setDaySelectedBoxBackgroundColor(Color.rgb(255,0,0));
                            calendar.setDayTextColor(Color.WHITE);
                            calendar.setDayWeekendTextColor(Color.LTGRAY);
                            calendar.setDayBoxBorderColor(Color.WHITE);
                            calendar.setDaySelectedCircleBackgroundColor(Color.WHITE);
                            calendar.setDayPreselectedCircleBackgroundColor(Color.rgb(245, 245, 245));
                            calendar.setDaySelectedTextColor(Color.RED);
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
