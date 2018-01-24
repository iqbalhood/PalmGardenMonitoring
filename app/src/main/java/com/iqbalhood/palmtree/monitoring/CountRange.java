package com.iqbalhood.palmtree.monitoring;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mac on 7/9/17.
 */

public class CountRange {

    public String weekRange(int y , int m, int d){

        // set the date
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, d);

        // "calculate" the start date of the week
        Calendar first = (Calendar) cal.clone();


        // and add six days to the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);

        // print the result
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        System.out.println(df.format(first.getTime()) + " - " +
                df.format(last.getTime()));

        return ""+df.format(first.getTime()) + " - " +
                df.format(last.getTime());

    }
}
