package com.college.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by G on 2017/3/11.
 */
public class TimeHelper {
    public static Date getCurrentDate(){
        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        return sqlDate;
    }

    public static Timestamp getCurrentTime(){
        java.sql.Timestamp time = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        return time;
    }
}
