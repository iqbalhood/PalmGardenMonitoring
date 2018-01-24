/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iqbalhood.palmtree.monitoring.fragmentab;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iqbalhood.palmtree.monitoring.DataHelper;
import com.iqbalhood.palmtree.monitoring.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Fragment that displays "Wednesday".
 */
public class GanodermaFragment extends Fragment {

    String[] tahun;
    String kebunDB = "1";

    // spinner1 element
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    Spinner spinnerJenisFile;
    LinearLayout layout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_jumlah_ganoderma, container, false);
        layout = (LinearLayout) view.findViewById(R.id.progressbar_view);


        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            TextView satuanKebun = (TextView)view.findViewById(R.id.satuanKebun);
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
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        spinner3 = (Spinner) view.findViewById(R.id.spinner3);

        // Creating adapter for spinner1
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, tahun);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Creating adapter for spinner1
        ArrayAdapter<String> dataAdapterBulan = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, bulan);

        // Drop down layout style - list view with radio button
        dataAdapterBulan
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Creating adapter for spinner1
        ArrayAdapter<String> dataAdapterBulan2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, bulan2);

        // Drop down layout style - list view with radio button
        dataAdapterBulan2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner1
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapterBulan);
        spinner3.setAdapter(dataAdapterBulan2);

        boolean k = initialize_database_ganoderma();

        System.out.println("STATUS  DB Ganoderma " + k);

        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (k == false) {
            dbHelper.createTableGanoderma(db);
        }


        Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                inputGANODERMA(v);

            }
        });

        setData(view);

        spinnerJenisFile = (Spinner) view.findViewById(R.id.spinner00);
        final BarChart barChart = (BarChart) view.findViewById(R.id.chart);
        Button btnExport = (Button)view.findViewById(R.id.btnExport);

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idSpinner = spinnerJenisFile.getSelectedItemPosition();
                if(idSpinner == 1){
                    barChart.saveToGallery("Grafik Ganoderma", 100);
                    Toast.makeText(getActivity(), "Grafik Ganoderma Berhasil Di Export Silahkan Cek di Galleri", Toast.LENGTH_SHORT).show();

                }else{
                    new Task().execute();
                }
            }
        });

        return view;
    }



    public void setData(View v) {


        BarChart barChart = (BarChart) v.findViewById(R.id.chart);

        // HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);

        String kebun = "";

        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {

            kebun = bbb.getString("id");


        }


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


        BarDataSet dataset = new BarDataSet(entries, "");

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
                ContextCompat.getColor(getActivity(), R.color.abu),
                ContextCompat.getColor(getActivity(), R.color.abu),
                ContextCompat.getColor(getActivity(), R.color.abu),

                ContextCompat.getColor(getActivity(), R.color.abu),
                ContextCompat.getColor(getActivity(), R.color.abu),
                ContextCompat.getColor(getActivity(), R.color.abu),

                ContextCompat.getColor(getActivity(), R.color.abu),
                ContextCompat.getColor(getActivity(), R.color.abu),
                ContextCompat.getColor(getActivity(), R.color.abu),
                ContextCompat.getColor(getActivity(), R.color.abu)});

        BarData data = new BarData(labels, dataset);
        // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        barChart.setBorderWidth(0.5f);
        barChart.setData(data);
        barChart.animateY(5000);


    }


    public float Hitung(String month, String kebun, String tahun) {

        DataHelper dbHelper = new DataHelper(getActivity());
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




    public boolean initialize_database_ganoderma() {

        /* open database, if doesn't exist, create it */
        SQLiteDatabase mDatabase = getActivity().openOrCreateDatabase("datasawit.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

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


    public void inputPHN(View view) {

        Toast.makeText(getActivity(), "Silahkan Masukkan Jumlah Pohon", Toast.LENGTH_SHORT).show();


        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            kebunDB = id;


        }


        EditText jumlah = (EditText)  getView().findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();
        if (jlh.isEmpty() || jlh.matches("")) {

            Toast.makeText(getActivity(), "Silahkan Masukkan Jumlah Pohon", Toast.LENGTH_SHORT).show();

        } else {

            DataHelper dbHelper = new DataHelper(getActivity());
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String tahunspinner = spinner1.getSelectedItem().toString();
            String bulanspinner = spinner2.getSelectedItem().toString();

            SQLiteStatement s = db.compileStatement("select count(*) from jumlah_pohon WHERE bulan ='" + bulanspinner + "' AND tahun ='" + tahunspinner + "' AND kebun ='" + kebunDB + "' ; ");

            long count = s.simpleQueryForLong();


            if (count > 0) {
                Toast.makeText(getActivity(), "Data Sudah Ada", Toast.LENGTH_SHORT).show();

            } else {


                String query = "INSERT INTO jumlah_pohon (bulan , jumlah,  tahun , kebun ) VALUES ('" + bulanspinner + "','" + jlh + "','" + tahunspinner + "','" + kebunDB + "')";

                //Toast.makeText(getApplicationContext(), "Data Belum Ada" , Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(), "query" + query , Toast.LENGTH_SHORT).show();

                System.out.println("QUERY JUMLAH POHON " + query);

                db.execSQL(query);

                Toast.makeText(getActivity(), "Data Sucessfully Inserted", Toast.LENGTH_SHORT).show();

                setData(getView());


            }


       }


    }



    public void inputGANODERMA(View view) {

        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            kebunDB = id;


        }


        EditText jumlah = (EditText)  getView().findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();
        if (jlh.isEmpty() || jlh.matches("")) {

            Toast.makeText(getActivity(), "Silahkan Masukkan Persentase Ganoderma", Toast.LENGTH_SHORT).show();

        } else {

            DataHelper dbHelper = new DataHelper(getActivity());
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            String tahunspinner = spinner1.getSelectedItem().toString();
            String bulan1spinner = spinner2.getSelectedItem().toString();
            String bulan2spinner = spinner3.getSelectedItem().toString();


            SQLiteStatement s = db.compileStatement("select count(*) from ganoderma WHERE bulan1 ='" + bulan1spinner + "' AND bulan2 ='" + bulan2spinner + "' AND tahun ='" + tahunspinner + "' AND kebun ='" + kebunDB + "' ; ");

            long count = s.simpleQueryForLong();


            if (count > 0) {

                String query = "UPDATE ganoderma SET jumlah =" + jlh + " WHERE bulan1 ='" + bulan1spinner + "' AND bulan2 ='" + bulan2spinner + "' AND tahun ='" + tahunspinner + "' AND kebun ='" + kebunDB + "' ;" ;
                db.execSQL(query);

                Toast.makeText(getActivity(), "Data Sucessfully Updated", Toast.LENGTH_SHORT).show();

            } else {

                String query = "INSERT INTO ganoderma (bulan1, bulan2, jumlah,  tahun , kebun ) VALUES ('" + bulan1spinner + "', '" + bulan2spinner + "', '" + jlh + "','" + tahunspinner + "','" + kebunDB + "')";

                //Toast.makeText(getApplicationContext(), "Data Belum Ada" , Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(), "query" + query , Toast.LENGTH_SHORT).show();

                System.out.println("QUERY JUMLAH GANODERMA " + query);

                db.execSQL(query);

                Toast.makeText(getActivity(), "Data Sucessfully Inserted", Toast.LENGTH_SHORT).show();

            }

        }


        setData(getView());


    }





    public float Hitung(String month1, String month2, String kebun, String tahun) {

        DataHelper dbHelper = new DataHelper(getActivity());
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



    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            layout.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            layout.setVisibility(View.GONE);

            super.onPostExecute(result);
            Toast.makeText(getActivity(), "Data Berhasil Dieksport Silahkan Cek Ganorderma.csv di SDCARD anda " , Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Boolean doInBackground(String... params) {

            exportDB();

            return null;
        }
    }



    private void exportDB() {

        File dbFile = getActivity().getDatabasePath( "datasawit.db");
        DataHelper dbhelper = new DataHelper(getActivity());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "Ganoderma.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM ganoderma", null);
            String header[] = { "DARI BULAN", "KE BULAN", "TAHUN", "POPULASI/PELEPAH"};
            csvWrite.writeNext(header);


            while (curCSV.moveToNext()) {
                //Which column you want to exprort
                String arrStr[] = {curCSV.getString(1)   , curCSV.getString(2)  , curCSV.getString(4)  , curCSV.getString(3)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        } catch (Exception sqlEx) {
            Log.e("Ganoderma Fragment", sqlEx.getMessage(), sqlEx);
        }

    }








}


