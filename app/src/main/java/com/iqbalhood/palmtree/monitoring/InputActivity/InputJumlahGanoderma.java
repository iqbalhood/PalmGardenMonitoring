package com.iqbalhood.palmtree.monitoring.InputActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iqbalhood.palmtree.monitoring.DataHelper;
import com.iqbalhood.palmtree.monitoring.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;

public class InputJumlahGanoderma extends AppCompatActivity {

    String[] tahun;
    String kebunDB = "1";

    // spinner1 element
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumlah_ganoderma);


        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            TextView satuanKebun = (TextView) findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama + " (" + tahun + ")");


        }

        Calendar calendar = Calendar.getInstance();
        int th = 2017;
        tahun = new String[15];

        String[] bulan = {"Januari", "Juli"};
        String[] bulan2 = {"Juni", "Desember"};


        for (int i = 0; i < 15; i++) {
            tahun[i] = "" + (th + i);
        }


        // spinner1 element
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        // Creating adapter for spinner1
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tahun);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Creating adapter for spinner1
        ArrayAdapter<String> dataAdapterBulan = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bulan);

        // Drop down layout style - list view with radio button
        dataAdapterBulan
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Creating adapter for spinner1
        ArrayAdapter<String> dataAdapterBulan2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bulan2);

        // Drop down layout style - list view with radio button
        dataAdapterBulan2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner1
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapterBulan);
        spinner3.setAdapter(dataAdapterBulan2);


        initialize_database_ganoderma();

        boolean k = initialize_database_ganoderma();

        System.out.println("STATUS  DB GANODERMA " + k);

        DataHelper dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (k == false) {


            dbHelper.createTableGanoderma(db);


        }

        setData();


    }


    public void setData() {


        BarChart barChart = (BarChart) findViewById(R.id.chart);

        // HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);

        float Range11 = Hitung("Januari", "Juni", kebunDB, "2017");
        float Range12 = Hitung("Juli", "Desember", kebunDB, "2017");

        float Range21 = Hitung("Januari", "Juni", kebunDB, "2018");
        float Range22 = Hitung("Juli", "Desember", kebunDB, "2018");


        float Range31 = Hitung("Januari", "Juni", kebunDB, "2019");
        float Range32 = Hitung("Juli", "Desember", kebunDB, "2019");

        float Range41 = Hitung("Januari", "Juni", kebunDB, "2020");
        float Range42 = Hitung("Juli", "Desember", kebunDB, "2020");


        float Range51 = Hitung("Januari", "Juni", kebunDB, "2021");
        float Range52 = Hitung("Juli", "Desember", kebunDB, "2021");


        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(Range11, 0));
        entries.add(new BarEntry(Range12, 1));

        entries.add(new BarEntry(Range21, 2));
        entries.add(new BarEntry(Range22, 3));

        entries.add(new BarEntry(Range31, 4));
        entries.add(new BarEntry(Range32, 5));

        entries.add(new BarEntry(Range41, 6));
        entries.add(new BarEntry(Range42, 7));

        entries.add(new BarEntry(Range51, 8));
        entries.add(new BarEntry(Range52, 9));


        BarDataSet dataset = new BarDataSet(entries, "# (%) Jumlah Ganoderma ");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Jan 2017 - Jun 2017");
        labels.add("Jul 2017 - Des 2017");
        labels.add("Jan 2018 - Jun 2018");
        labels.add("Jul 2018 - Des 2018");
        labels.add("Jan 2019 - Jun 2019");
        labels.add("Jul 2019 - Des 2019");
        labels.add("Jan 2020 - Jun 2020");
        labels.add("Jul 2020 - Des 2020");
        labels.add("Jan 2021 - Jun 2021");
        labels.add("Jul 2021 - Des 2021");


        dataset.setColors(new int[]{
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

        BarData data = new BarData(labels, dataset);
        // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        barChart.setBorderWidth(0.5f);
        barChart.setData(data);
        barChart.animateY(5000);


    }

    public boolean initialize_database_ganoderma() {

        /* open database, if doesn't exist, create it */
        SQLiteDatabase mDatabase = openOrCreateDatabase("datasawit.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor c = null;
        boolean tableExists = false;
/* get cursor on it */
        try {
            c = mDatabase.query("ganoderma", null,
                    null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
    /* fail */
            Log.d("STATUS TABEL", "TABEL  doesn't exist :(((");
        }

        return tableExists;


    }


    public void inputGANODERMA(View view) {

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            kebunDB = id;


        }


        EditText jumlah = (EditText) findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();
        if (jlh.isEmpty() || jlh.matches("")) {

            Toast.makeText(getApplicationContext(), "Silahkan Masukkan Persentase Ganoderma", Toast.LENGTH_SHORT).show();

        } else {

            DataHelper dbHelper = new DataHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            String tahunspinner = spinner1.getSelectedItem().toString();
            String bulan1spinner = spinner2.getSelectedItem().toString();
            String bulan2spinner = spinner3.getSelectedItem().toString();


            SQLiteStatement s = db.compileStatement("select count(*) from ganoderma WHERE bulan1 ='" + bulan1spinner + "' AND bulan2 ='" + bulan2spinner + "' AND tahun ='" + tahunspinner + "' AND kebun ='" + kebunDB + "' ; ");

            long count = s.simpleQueryForLong();


            if (count > 0) {
                Toast.makeText(getApplicationContext(), "Data Sudah Ada", Toast.LENGTH_SHORT).show();

            } else {


                String query = "INSERT INTO ganoderma (bulan1, bulan2, jumlah,  tahun , kebun ) VALUES ('" + bulan1spinner + "', '" + bulan2spinner + "', '" + jlh + "','" + tahunspinner + "','" + kebunDB + "')";

                //Toast.makeText(getApplicationContext(), "Data Belum Ada" , Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(), "query" + query , Toast.LENGTH_SHORT).show();

                System.out.println("QUERY JUMLAH GANODERMA " + query);

                db.execSQL(query);

                Toast.makeText(getApplicationContext(), "Data Sucessfully Inserted", Toast.LENGTH_SHORT).show();


            }


        }


    }


    public float Hitung(String month1, String month2, String kebun, String tahun) {

        DataHelper dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement("select count(*) from ganoderma WHERE tahun ='" + tahun + "' AND bulan1 ='" + month1 + "' AND bulan2 ='" + month2 + "' AND kebun ='" + kebun + "' ; ");

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
                    " bulan2 = '" + month2 + "'\n" +
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
