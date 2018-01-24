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

public class InputJumlahPohon extends AppCompatActivity {

    String[] tahun;

    String kebunDB = "1";
    String tahunDB = "2017";

    // spinner1 element
    Spinner spinner1;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumlah_pohon);

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
        tahun = new String[5];

        String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};


        for (int i = 0; i < 5; i++) {
            tahun[i] = "" + (th + i);
        }


        // spinner1 element
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

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

        // attaching data adapter to spinner1
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapterBulan);


        boolean k = initialize_Database_Pohon();

        System.out.println("STATUS  DB POHON " + k);

        DataHelper dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (k == false) {
            dbHelper.createTablePohon(db);
        }

        setData();


    }


    public float Hitung(String month, String kebun, String tahun) {

        DataHelper dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement("select count(*) from jumlah_pohon WHERE tahun ='" + tahun + "' AND bulan ='" + month + "' AND kebun ='" + kebun + "' ; ");

        long count = s.simpleQueryForLong();
        float returnData = 0;


        if (count > 0) {

            String QPerhitungan = "SELECT\n" +
                    " sum(jumlah)\n" +
                    "FROM\n" +
                    " jumlah_pohon\n" +
                    "WHERE\n" +
                    " bulan = '" + month + "'\n" +
                    "AND\n" +
                    " kebun = '" + kebun + "'\n" +
                    "AND\n" +
                    " tahun = '" + tahun + "';";

            SQLiteStatement ss = db.compileStatement(QPerhitungan);
            long counts = ss.simpleQueryForLong();

            returnData = counts;


//            Toast.makeText(getApplicationContext(), "Jumlah Data " + counts, Toast.LENGTH_SHORT).show();
        } else {

            returnData = count;

//            Toast.makeText(getApplicationContext(), "Data Bulan Belum ada  Data " + count, Toast.LENGTH_SHORT).show();


        }


        return returnData;

    }


    public void setData() {


        BarChart barChart = (BarChart) findViewById(R.id.chart);

        // HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);

        String kebun = "";

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            kebun = bbb.getString("id");


        }


        float Januari = Hitung("Januari", kebun, "2017");
        float Februari = Hitung("Februari", kebun, "2017");
        float Maret = Hitung("Maret", kebun, "2017");
        float April = Hitung("April", kebun, "2017");
        float Mei = Hitung("Mei", kebun, "2017");
        float Juni = Hitung("Juni", kebun, "2017");
        float Juli = Hitung("Juli", kebun, "2017");
        float Agustus = Hitung("Agustus", kebun, "2017");
        float September = Hitung("September", kebun, "2017");
        float Oktober = Hitung("Oktober", kebun, "2017");
        float November = Hitung("November", kebun, "2017");
        float Desember = Hitung("Desember", kebun, "2017");


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


        BarDataSet dataset = new BarDataSet(entries, "# mm Jumlah Curah Hujan ");

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
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau),
                ContextCompat.getColor(this, R.color.hijau)});

        BarData data = new BarData(labels, dataset);
        // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        barChart.setBorderWidth(0.5f);
        barChart.setData(data);
        barChart.animateY(5000);


    }


    public boolean initialize_Database_Pohon() {

        /* open database, if doesn't exist, create it */
        SQLiteDatabase mDatabase = openOrCreateDatabase("datasawit.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor c = null;
        boolean tableExists = false;
/* get cursor on it */
        try {
            c = mDatabase.query("jumlah_pohon", null,
                    null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
    /* fail */
            Log.d("STATUS TABEL", "TABEL  doesn't exist :(((");
        }

        return tableExists;


    }


    public void inputPHN(View view) {

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            kebunDB = id;


        }


        EditText jumlah = (EditText) findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();
        if (jlh.isEmpty() || jlh.matches("")) {

            Toast.makeText(getApplicationContext(), "Silahkan Masukkan Jumlah Pohon", Toast.LENGTH_SHORT).show();

        } else {

            DataHelper dbHelper = new DataHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String tahunspinner = spinner1.getSelectedItem().toString();
            String bulanspinner = spinner2.getSelectedItem().toString();

            SQLiteStatement s = db.compileStatement("select count(*) from jumlah_pohon WHERE bulan ='" + bulanspinner + "' AND tahun ='" + tahunspinner + "' AND kebun ='" + kebunDB + "' ; ");

            long count = s.simpleQueryForLong();


            if (count > 0) {
                Toast.makeText(getApplicationContext(), "Data Sudah Ada", Toast.LENGTH_SHORT).show();

            } else {


                String query = "INSERT INTO jumlah_pohon (bulan , jumlah,  tahun , kebun ) VALUES ('" + bulanspinner + "','" + jlh + "','" + tahunspinner + "','" + kebunDB + "')";

                //Toast.makeText(getApplicationContext(), "Data Belum Ada" , Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(), "query" + query , Toast.LENGTH_SHORT).show();

                System.out.println("QUERY JUMLAH POHON " + query);

                db.execSQL(query);

                Toast.makeText(getApplicationContext(), "Data Sucessfully Inserted", Toast.LENGTH_SHORT).show();


            }


        }


    }


}
