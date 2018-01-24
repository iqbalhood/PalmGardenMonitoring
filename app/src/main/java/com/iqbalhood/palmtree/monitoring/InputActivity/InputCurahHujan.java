package com.iqbalhood.palmtree.monitoring.InputActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iqbalhood.palmtree.monitoring.DataHelper;
import com.iqbalhood.palmtree.monitoring.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

public class InputCurahHujan extends AppCompatActivity {

    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    String tanggal , bulan , tahun;

    String kebun = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_rainfall);
        dateView = (TextView) findViewById(R.id.textView3);

            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            showDate(year, month+1, day);
            //Hitung();


        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            TextView satuanKebun = (TextView)findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama+" ("+tahun+")");


        }


        settingDatabase();

        setData();

    }





    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

        if(day<10){

            tanggal = "0"+day;

        }else{
            tanggal = ""+day;
        }


        if(month<10){

            bulan = "0"+month;

        }else{

            bulan = ""+month;
        }


        tahun = ""+year;


    }



    public void inputCH(View view){




        EditText jumlah = (EditText)findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();

        if(jlh.isEmpty()||jlh.matches("")){

            Toast.makeText(getApplicationContext(), "Silahkan Masukkan Jumlah Curah Hujan" , Toast.LENGTH_SHORT).show();

        }else{

            String query = "UPDATE curah_hujan  SET jumlah ="+jlh+" WHERE   id ="+kebun+tahun+bulan+tanggal+";";

            System.out.println("QUERY " + query);

            Toast.makeText(getApplicationContext(), query , Toast.LENGTH_SHORT).show();


            DataHelper dbHelper = new DataHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            db.execSQL(query);
            Toast.makeText(getApplicationContext(), "Data Recorded Sucessfully" , Toast.LENGTH_SHORT).show();


            Intent intent = getIntent();
            finish();
            startActivity(intent);


        }

    }


    public float Hitung (String month){

        DataHelper dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement( "select count(*) from curah_hujan WHERE tahun ='"+"2017"+"' AND bulan ='"+month+"' AND kebun ='"+kebun+"' ; " );

        long count = s.simpleQueryForLong();
        float returnData = 0;


        if(count>0) {

            String QPerhitungan = "SELECT\n" +
                    " sum(jumlah)\n" +
                    "FROM\n" +
                    " curah_hujan\n" +
                    "WHERE\n" +
                    " bulan = '"+month+"'\n" +
                    "AND\n" +
                    " kebun = '"+kebun+"'\n" +
                    "AND\n" +
                    " tahun = '2017';";

            SQLiteStatement ss = db.compileStatement(QPerhitungan);
            long counts = ss.simpleQueryForLong();

            returnData = counts;


            Toast.makeText(getApplicationContext(), "Jumlah Data " + counts, Toast.LENGTH_SHORT).show();

        }else{

            returnData = count;

            Toast.makeText(getApplicationContext(), "Data Bulan Belum ada  Data " + count, Toast.LENGTH_SHORT).show();

        }


        return  returnData ;

    }

    public void settingDatabase(){

        DataHelper dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String year = "2017";

        for (int i=0; i<12; i++){

            String ii = "";

            if((i+1)<10){

                ii = "0"+(i+1);

            }else {

                ii =""+ (i+1);
            }

            SQLiteStatement s = db.compileStatement( "select count(*) from curah_hujan WHERE tahun ='"+year+"' AND bulan ='"+ii+"' AND kebun ='"+kebun+"'  ; " );

            long count = s.simpleQueryForLong();


            if(count>0) {

                System.out.println("Data MONTH "+(i+1)+" Already Created");


            }else{
                System.out.println("Data MONTH "+(i+1)+" Not  Created");

                dbHelper.printDate(db, i, kebun);

            }

        }



    }



    public void setData(){


        BarChart barChart = (BarChart) findViewById(R.id.chart);

        // HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);

        float Januari = Hitung("01");
        float Februari = Hitung("02");
        float Maret = Hitung("03");
        float April = Hitung("04");
        float Mei = Hitung("05");
        float Juni = Hitung("06");
        float Juli = Hitung("07");
        float Agustus = Hitung("08");
        float September = Hitung("09");
        float Oktober = Hitung("10");
        float November = Hitung("11");
        float Desember = Hitung("12");

        int colorJanuari;
        int colorFebruari;
        int colorMaret;
        int colorApril;
        int colorMei;
        int colorJuni;
        int colorJuli;
        int colorAgustus;
        int colorSeptember;
        int colorOktober;
        int colorNovember;
        int colorDesember;




        if(Januari < 60  ){

            colorJanuari = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorJanuari = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(Februari < 60  ){

            colorFebruari = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorFebruari = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(Maret < 60  ){

            colorMaret = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorMaret = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(April < 60  ){

            colorApril = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorApril = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(Mei < 60  ){

            colorMei = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorMei = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(Juni < 60  ){

            colorJuni = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorJuni = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(Juli < 60  ){

            colorJuli = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorJuli = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(Agustus < 60  ){

            colorAgustus = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorAgustus = ContextCompat.getColor(this, R.color.birumuda);

        }

        if(September < 60  ){

            colorSeptember = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorSeptember = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(Oktober < 60  ){

            colorOktober = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorOktober = ContextCompat.getColor(this, R.color.birumuda);

        }

        if(November < 60  ){

            colorNovember = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorNovember = ContextCompat.getColor(this, R.color.birumuda);

        }


        if(Desember < 60  ){

            colorDesember = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorDesember = ContextCompat.getColor(this, R.color.birumuda);

        }

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(Januari, 0));
        entries.add(new BarEntry(Februari, 1));
        entries.add(new BarEntry(Maret, 2));
        entries.add(new BarEntry(April, 3));
        entries.add(new BarEntry(Mei, 4));
        entries.add(new BarEntry(Juni, 5));
        entries.add(new BarEntry(Juli, 6));
        entries.add(new BarEntry(Agustus, 7));
        entries.add(new BarEntry(September, 8));
        entries.add(new BarEntry(Oktober, 9));
        entries.add(new BarEntry(November, 10));
        entries.add(new BarEntry(Desember, 11));


        BarDataSet dataset = new BarDataSet(entries, "# mm Jumlah Pohon ");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("Desember");


        dataset.setColors(new int[]{
                colorJanuari,
                colorFebruari,
                colorMaret,
                colorApril,
                colorMei,
                colorJuni,
                colorJuli,
                colorAgustus,
                colorSeptember,
                colorOktober,
                colorNovember,
                colorDesember});

        BarData data = new BarData(labels, dataset);
        // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        barChart.setBorderWidth(0.5f);
        barChart.setData(data);
        barChart.animateY(5000);

    }


    public class MyBarDataSet extends BarDataSet {


        public MyBarDataSet(List<BarEntry> yVals, String label) {
            super(yVals, label);
        }

        @Override
        public int getColor(int index) {
            if(getEntryForXIndex(index).getVal() < 95) // less than 95 green
                return mColors.get(0);
            else if(getEntryForXIndex(index).getVal() < 100) // less than 100 orange
                return mColors.get(1);
            else // greater or equal than 100 red
                return mColors.get(2);
        }

    }

}
