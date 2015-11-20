package com.ntilde.donantescalendarview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DonantesCalendarEvent implements Serializable{

    private Object mEventInfo;
    private Date mDate;
    private int mColor;
    private ArrayList<DonantesCalendarRange> mRange;

//    public static final Parcelable.Creator<DonantesCalendarEvent> CREATOR = new Parcelable.Creator<DonantesCalendarEvent>()
//    {
//        @Override
//        public DonantesCalendarEvent createFromParcel(Parcel source)
//        {
//            return new DonantesCalendarEvent(source);
//        }
//
//        @Override
//        public DonantesCalendarEvent[] newArray(int size)
//        {
//            return new DonantesCalendarEvent[size];
//        }
//    };

//    public DonantesCalendarEvent(Parcel in){
//        mEventInfo = in.readSerializable();
//        mDate = (Date)in.readSerializable();
//        mColor = in.readInt();
//        mRange = (HashMap<Integer, Integer>)in.readSerializable();
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeSerializable((Serializable)mEventInfo);
//        dest.writeSerializable(mDate);
//        dest.writeInt(mColor);
//        dest.writeSerializable(mRange);
//    }

    public DonantesCalendarEvent(Object eventInfo, Date date, int color){
        mEventInfo = eventInfo;
        mDate = date;
        mColor = color;
    }

    public DonantesCalendarEvent(Object eventInfo, Date date, int color, DonantesCalendarRange... ranges){
        mEventInfo = eventInfo;
        mDate = date;
        mColor = color;
        mRange=new ArrayList<>();
        for(DonantesCalendarRange range:ranges){
            mRange.add(range);
        }
    }

    public DonantesCalendarEvent(Object eventInfo, int year, int month, int day, int color){
        mEventInfo = eventInfo;
        mDate = new Date();
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month-1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        mDate.setTime(c.getTimeInMillis());
        mColor = color;
        //SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        //Log.e("XXX", "Fecha: " + format1.format(mDate));
    }

    public DonantesCalendarEvent(Object eventInfo, int year, int month, int day, int color, DonantesCalendarRange... ranges){
        mEventInfo = eventInfo;
        mDate = new Date();
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month-1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        mDate.setTime(c.getTimeInMillis());
        mColor = color;
        //SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        //Log.e("XXX", "Fecha: " + format1.format(mDate));
        mRange=new ArrayList<>();
        for(DonantesCalendarRange range:ranges){
            mRange.add(range);
        }
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public ArrayList<DonantesCalendarRange> getRanges() {
        return mRange;
    }

    public void setRanges(ArrayList<DonantesCalendarRange> ranges) {
        this.mRange = ranges;
    }

    public void addRange(DonantesCalendarRange range){
        if(mRange ==null){
            mRange =new ArrayList<>();
        }
        mRange.add(range);
    }

    public Object getEventInfo() {
        return mEventInfo;
    }

    public void setEventInfo(Object eventInfo) {
        mEventInfo = eventInfo;
    }
}
