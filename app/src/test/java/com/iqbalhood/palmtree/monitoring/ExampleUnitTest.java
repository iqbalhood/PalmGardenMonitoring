package com.iqbalhood.palmtree.monitoring;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
       // assertEquals(4, 2 + 2);
       // printDate();

//        weekRange(2017, 1, 1);
//        weekRange(2017, 1, 8);
//        weekRange(2017, 1, 15);
//        weekRange(2017, 1, 22);
//        weekRange(2017, 1, 29);
//
//        weekRange(2017, 2, 5);
//        weekRange(2017, 2, 12);
//        weekRange(2017, 2, 19);
//        weekRange(2017, 2, 26);
//
//        weekRange(2017, 3, 5);
//        weekRange(2017, 3, 12);
//        weekRange(2017, 3, 19);
//        weekRange(2017, 3, 26);
//
//        weekRange(2017, 4, 2);
//        weekRange(2017, 4, 9);
//        weekRange(2017, 4, 16);
//        weekRange(2017, 4, 23);
//        weekRange(2017, 4, 29);






        //  masihWeekRange();

//        int weeks = getWeeksOfMonth(2017, 7, Calendar.MONDAY);
//        System.out.println("################### WEEK " +weeks);
    }


    public void printDate(){
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MONTH, 1);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(2017, 6, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat id = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dm = new SimpleDateFormat("MM");
        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
        SimpleDateFormat dd = new SimpleDateFormat("dd");
        System.out.print(df.format(cal.getTime()));
        for (int i = 1; i < maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i );
            System.out.println("###################");
            System.out.println("" + df.format(cal.getTime()));
            System.out.println("ID>>" + id.format(cal.getTime()));
            System.out.println("BULAN >> "+ dm.format(cal.getTime()));
            System.out.println("TAHUN >> "+ dy.format(cal.getTime()));
            System.out.println("HARI >> "+ dd.format(cal.getTime()));
            System.out.println("###################");
        }
    }

    public void weekRange(int y , int m, int d){

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

    }


    public  void dayplusSeven(int y , int m, int d){



        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        System.out.println("Date = "+ cal.getTime());


        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        System.out.println(df.format(cal.getTime()));




    }


    public void masihWeekRange(){
        int jaar = 2015;
        int week = 50;

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, jaar);
        cal.set(Calendar.WEEK_OF_YEAR, week);

        while(true) //loop that always adds 1 to week.
        {

            cal.add(Calendar.WEEK_OF_YEAR, 1);
            jaar = cal.get(Calendar.YEAR);
            week = cal.get(Calendar.WEEK_OF_YEAR);

            System.out.println("jaar: " + jaar);
            System.out.println("week: " + week);
        }
    }


    public int getWeeksOfMonth(int year, int month, int weekStart) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        calendar.setFirstDayOfWeek(weekStart);
        int numOfWeeksInMonth = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

        return numOfWeeksInMonth;
    }
}