package com.iqbalhood.palmtree.monitoring.InputActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iqbalhood.palmtree.monitoring.CountRange;
import com.iqbalhood.palmtree.monitoring.DataHelper;
import com.iqbalhood.palmtree.monitoring.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;

public class InputOryctes extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar, calendar2;
    private TextView dateView;
    private TextView dateView2;
    private int year, month, day, day2;

    int set = 1;
    String kebunDB = "1";

    private String tgl1 = "1";
    private String bln1 = "01";

    private String tgl2 = "2";
    private String bln2 = "02";

    private String thn = "2017";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_oryctes);

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {
            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");
            TextView satuanKebun = (TextView)findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama+" ("+tahun+")");
        }
        dateView = (TextView) findViewById(R.id.textView3);
        dateView2 = (TextView) findViewById(R.id.textView33);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, 7);

        showDate(year, month+1, day);
        showDate2(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH)+1, calendar2.get(Calendar.DAY_OF_MONTH));

        boolean k = initialize_database_oryctes();

        System.out.println("STATUS  DB GANODERMA " + k);

        DataHelper dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (k == false) {
            dbHelper.createTableOryctes(db);
        }

        setDataBar();

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @SuppressWarnings("deprecation")
    public void setDate2(View view) {
        showDialog(999);
        set = 2;
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
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
                    if(set == 2){
                        showDate2(arg1, arg2+1, arg3);
                    }else {
                        showDate(arg1, arg2 + 1, arg3);
                    }

                }
            };




    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

        tgl1 = ""+day;



        bln1 = ""+month;


        thn = ""+year;




    }

    private void showDate2(int year, int month, int day) {
        dateView2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

        tgl2 = ""+day;



            bln2 = ""+month;


    }

    public void submitOrcytes(View view){



        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            kebunDB = id;


        }


        EditText jumlah = (EditText) findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();
        if (jlh.isEmpty() || jlh.matches("")) {

            Toast.makeText(getApplicationContext(), "Silahkan Masukkan Persentase Oryctes", Toast.LENGTH_SHORT).show();

        } else {



            DataHelper dbHelper = new DataHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            SQLiteStatement s = db.compileStatement("select count(*) from oryctes WHERE  tanggal1 ='" + tgl1 + "' AND  bulan1 ='" + bln1 + "' AND tanggal2 ='" + tgl2+"' AND bulan2 ='" + bln2 + "' AND tahun ='" + thn + "' AND kebun ='" + kebunDB + "' ; ");

            long count = s.simpleQueryForLong();


            if(count>0) {

                System.out.println("Data MONTH  Already Created");


            }else{
                System.out.println("Data MONTH Not  Created");

                String query = "INSERT INTO oryctes (tanggal1, bulan1, tanggal2, bulan2, jumlah,  tahun , kebun ) VALUES ('" + tgl1 + "', '" + bln2 + "', '" + tgl2 + "',  '" + bln2 + "', '" + jlh + "','" + thn + "','" + kebunDB + "')";

                db.execSQL(query);

            }



//            String hasilPicker1 = "TGL 1 "+tgl1 + "BLN 1 "+bln1;
//
//            String hasilPicker2 = "TGL 2 "+tgl2 + "BLN 2 "+bln2;
//
//            Toast.makeText(getApplicationContext(), hasilPicker1 + " " + hasilPicker2, Toast.LENGTH_SHORT).show();




        }






    }


    public void setDataBar(){

        CountRange cr = new CountRange();

        BarChart barChart = (BarChart) findViewById(R.id.chart);

        float Range11 = Hitung("2017", "1", "1", kebunDB);
        float Range12 = Hitung("2017", "1", "8", kebunDB);
        float Range13 = Hitung("2017", "1", "15", kebunDB);
        float Range14 = Hitung("2017", "1", "22", kebunDB);
        float Range15 = Hitung("2017", "1", "29", kebunDB);


        float Range21 = Hitung("2017", "2", "5", kebunDB);
        float Range22 = Hitung("2017", "2", "12", kebunDB);
        float Range23 = Hitung("2017", "2", "19", kebunDB);
        float Range24 = Hitung("2017", "2", "26", kebunDB);


        float Range31 = Hitung("2017", "3", "5", kebunDB);
        float Range32 = Hitung("2017", "3", "12", kebunDB);
        float Range33 = Hitung("2017", "3", "19", kebunDB);
        float Range34 = Hitung("2017", "3", "26", kebunDB);


        float Range41 = Hitung("2017", "4", "2", kebunDB);
        float Range42 = Hitung("2017", "4", "9", kebunDB);
        float Range43 = Hitung("2017", "4", "16", kebunDB);
        float Range44 = Hitung("2017", "4", "23", kebunDB);
        float Range45 = Hitung("2017", "4", "29", kebunDB);








        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(Range11, 0));
        entries.add(new BarEntry(Range11, 1));
        entries.add(new BarEntry(Range11, 2));
        entries.add(new BarEntry(Range11, 3));
        entries.add(new BarEntry(Range11, 5));


        entries.add(new BarEntry(Range11, 6));
        entries.add(new BarEntry(Range11, 7));
        entries.add(new BarEntry(Range11, 8));
        entries.add(new BarEntry(Range11, 9));


        entries.add(new BarEntry(Range11, 10));
        entries.add(new BarEntry(Range11, 11));
        entries.add(new BarEntry(Range11, 12));
        entries.add(new BarEntry(Range11, 13));


        entries.add(new BarEntry(Range11, 14));
        entries.add(new BarEntry(19f, 15));
        entries.add(new BarEntry(19f, 16));
        entries.add(new BarEntry(Range11, 17));
        entries.add(new BarEntry(Range11, 18));


        BarDataSet bardataset = new BarDataSet(entries, "Cells");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add(cr.weekRange(2017, 1, 1));
        labels.add(cr.weekRange(2017, 1, 8));
        labels.add(cr.weekRange(2017, 1, 15));
        labels.add(cr.weekRange(2017, 1, 22));
        labels.add(cr.weekRange(2017, 1, 29));

        labels.add(cr.weekRange(2017, 2, 5));
        labels.add(cr.weekRange(2017, 2, 12));
        labels.add(cr.weekRange(2017, 2, 19));
        labels.add(cr.weekRange(2017, 2, 26));

        labels.add(cr.weekRange(2017, 3, 5));
        labels.add(cr.weekRange(2017, 3, 12));
        labels.add(cr.weekRange(2017, 3, 19));
        labels.add(cr.weekRange(2017, 3, 26));

        labels.add(cr.weekRange(2017, 4, 2));
        labels.add(cr.weekRange(2017, 4, 9));
        labels.add(cr.weekRange(2017, 4, 16));
        labels.add(cr.weekRange(2017, 4, 23));
        labels.add(cr.weekRange(2017, 4, 29));

        bardataset.setColors(new int[]{
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),

                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),

                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),



                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu),
                ContextCompat.getColor(this, R.color.abu)});


        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of lables into chart

        barChart.setDescription("Set Bar Chart Description");  // set the description

        barChart.animateY(5000);
    }



    public boolean initialize_database_oryctes() {

        /* open database, if doesn't exist, create it */
        SQLiteDatabase mDatabase = openOrCreateDatabase("datasawit.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor c = null;
        boolean tableExists = false;
/* get cursor on it */
        try {
            c = mDatabase.query("oryctes", null,
                    null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
    /* fail */
            Log.d("STATUS TABEL", "TABEL  doesn't exist :(((");
        }

        return tableExists;


    }


    public float Hitung(String tahun,  String month1, String date1, String kebun) {

        DataHelper dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement("select count(*) from oryctes WHERE tahun ='" + tahun + "' AND bulan1 ='" + month1 + "' AND tanggal1 ='" + date1 + "' AND kebun ='" + kebun + "' ; ");

        long count = s.simpleQueryForLong();
        float returnData = 0;


        if (count > 0) {

            String QPerhitungan = "SELECT\n" +
                    " sum(jumlah)\n" +
                    "FROM\n" +
                    " ganoderma\n" +
                    "WHERE\n" +
                    " bulan1 = '" + month1 + "'\n" +
                    "AND\n" +
                    " tanggal1 = '" + date1 + "'\n" +
                    "AND\n" +
                    " kebun = '" + kebun + "'\n" +
                    "AND\n" +
                    " tahun = '" + tahun + "';";

            SQLiteStatement ss = db.compileStatement(QPerhitungan);
            long counts = ss.simpleQueryForLong();

            returnData = counts;


            // Toast.makeText(getApplicationContext(), "Jumlah Data " + counts, Toast.LENGTH_SHORT).show();
        } else {

            returnData = count;

            // Toast.makeText(getApplicationContext(), "Data Bulan Belum ada  Data " + count, Toast.LENGTH_SHORT).show();


        }


        return returnData;

    }




}
