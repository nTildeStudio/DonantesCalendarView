package com.ntilde.donantescalendarview;

import java.util.Calendar;

public class DonantesCalendarRange {

    public enum UNITS{
        DAYS, MONTHS
    }

    private int mRange;
    private int mUnit;
    private int mColor;
    private String mMessage;

    public DonantesCalendarRange(int range, UNITS unit, int color, String message) {
        mRange = range;
        switch (unit){
            case DAYS:
                mUnit = Calendar.DAY_OF_YEAR;
                break;
            case MONTHS:
                mUnit = Calendar.MONTH;
                break;
        }
        mColor = color;
        mMessage = message;
    }

    public int getRange() {
        return mRange;
    }

    public int getUnit() {
        return mUnit;
    }

    public int getColor() {
        return mColor;
    }

    public String getMessage() {
        return mMessage;
    }
}
