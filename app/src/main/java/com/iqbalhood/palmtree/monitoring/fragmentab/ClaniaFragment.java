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

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Fragment that displays "Wednesday".
 */
public class ClaniaFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    String[] daftar;
    int[] warna;


    private DatePicker datePicker;
    private Calendar calendar, calendar2;
    private TextView dateView;
    private TextView dateView2;
    private int year, month, day, day2;

    String kebunDB = "1";
    String tahunDB = "2017";
    int     koeftahun = 1;


    int bulan1;
    int bulan2;

    int tahun1;
    int tahun2;

    int hari1;
    int hari2;

    int set = 1;

    //Untuk Perulangan Ambil Data

    protected Cursor cursor;
    DataHelper dbcenter;

    LinearLayout layout;

    Spinner spinnerJenisFile;
    Spinner tahunTampil;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_clania, container, false);
        layout = (LinearLayout) view.findViewById(R.id.progressbar_view);

        dbcenter = new DataHelper(getActivity());

        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            TextView satuanKebun = (TextView) view.findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama + " (" + tahun + ")");

        }

        dateView = (TextView) view.findViewById(R.id.textView3);
        dateView2 = (TextView) view.findViewById(R.id.textView33);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, 7);

        showDate(year, month+1, day);
        showDate2(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH)+1, calendar2.get(Calendar.DAY_OF_MONTH));


        ImageButton button1 = (ImageButton) view.findViewById(R.id.button11);
        ImageButton button2 = (ImageButton) view.findViewById(R.id.button111);

        Button buttonSubmit = (Button)view.findViewById(R.id.button1);



        boolean k = initialize_database_ulatKantung();

        System.out.println("STATUS  DB Ulat API " + k);

        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (k == false) {
            dbHelper.createTableUlatKantung(db);
        }



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputUlatKantung(view);


            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                // setDate(v);
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH)+1;
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = "Date" + String.valueOf(year) + "-" + String.valueOf(monthOfYear+1)
                                + "-" + String.valueOf(dayOfMonth);
                       showDate(year, monthOfYear+1, dayOfMonth);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                // setDate(v);
//                show(getFragmentManager(), "datePicker");

                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH)+1;
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = "Date" + String.valueOf(year) + "-" + String.valueOf(monthOfYear+1)
                                + "-" + String.valueOf(dayOfMonth);

                         showDate2(year, monthOfYear+1, dayOfMonth);

                    }
                }, yy, mm, dd);
                datePicker.show();


            }
        });




        tahunTampil = (Spinner)view.findViewById(R.id.spinner0);

        tahunTampil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                TextView tvTahun1 = (TextView)view.findViewById(R.id.tvTahun);
                TextView tvTahun2 = (TextView)view.findViewById(R.id.tvTahun2);
                TextView tvTahun3 = (TextView)view.findViewById(R.id.tvTahun3);
                TextView tvTahun4 = (TextView)view.findViewById(R.id.tvTahun4);
                TextView tvTahun5 = (TextView)view.findViewById(R.id.tvTahun5);

                BarChart barChart1 = (BarChart) view.findViewById(R.id.chart);
                BarChart barChart2 = (BarChart) view.findViewById(R.id.chart2);
                BarChart barChart3 = (BarChart) view.findViewById(R.id.chart3);
                BarChart barChart4 = (BarChart) view.findViewById(R.id.chart4);
                BarChart barChart5 = (BarChart) view.findViewById(R.id.chart5);

                if(position==0){

                    tahunDB = "2017";
                    koeftahun =1;

                    tvTahun1.setVisibility(View.VISIBLE);
                    tvTahun2.setVisibility(View.VISIBLE);
                    tvTahun3.setVisibility(View.VISIBLE);
                    tvTahun4.setVisibility(View.VISIBLE);
                    tvTahun5.setVisibility(View.VISIBLE);


                    barChart1.setVisibility(View.VISIBLE);
                    barChart2.setVisibility(View.VISIBLE);
                    barChart3.setVisibility(View.VISIBLE);
                    barChart4.setVisibility(View.VISIBLE);
                    barChart5.setVisibility(View.VISIBLE);

                }



                if(position==1){

                    tahunDB = "2017";
                    koeftahun =1;

                    tvTahun1.setVisibility(View.VISIBLE);
                    tvTahun2.setVisibility(View.GONE);
                    tvTahun3.setVisibility(View.GONE);
                    tvTahun4.setVisibility(View.GONE);
                    tvTahun5.setVisibility(View.GONE);


                    barChart1.setVisibility(View.VISIBLE);
                    barChart2.setVisibility(View.GONE);
                    barChart3.setVisibility(View.GONE);
                    barChart4.setVisibility(View.GONE);
                    barChart5.setVisibility(View.GONE);

                }



                if(position==2){

                    tahunDB = "2018";
                    koeftahun =2;

                    tvTahun1.setVisibility(View.GONE);
                    tvTahun2.setVisibility(View.VISIBLE);
                    tvTahun3.setVisibility(View.GONE);
                    tvTahun4.setVisibility(View.GONE);
                    tvTahun5.setVisibility(View.GONE);


                    barChart1.setVisibility(View.GONE);
                    barChart2.setVisibility(View.VISIBLE);
                    barChart3.setVisibility(View.GONE);
                    barChart4.setVisibility(View.GONE);
                    barChart5.setVisibility(View.GONE);

                }


                if(position==3){

                    tahunDB = "2019";
                    koeftahun =3;

                    tvTahun1.setVisibility(View.GONE);
                    tvTahun2.setVisibility(View.GONE);
                    tvTahun3.setVisibility(View.VISIBLE);
                    tvTahun4.setVisibility(View.GONE);
                    tvTahun5.setVisibility(View.GONE);


                    barChart1.setVisibility(View.GONE);
                    barChart2.setVisibility(View.GONE);
                    barChart3.setVisibility(View.VISIBLE);
                    barChart4.setVisibility(View.GONE);
                    barChart5.setVisibility(View.GONE);

                }


                if(position==4){

                    tahunDB = "2020";
                    koeftahun =4;

                    tvTahun1.setVisibility(View.GONE);
                    tvTahun2.setVisibility(View.GONE);
                    tvTahun3.setVisibility(View.GONE);
                    tvTahun4.setVisibility(View.VISIBLE);
                    tvTahun5.setVisibility(View.GONE);


                    barChart1.setVisibility(View.GONE);
                    barChart2.setVisibility(View.GONE);
                    barChart3.setVisibility(View.GONE);
                    barChart4.setVisibility(View.VISIBLE);
                    barChart5.setVisibility(View.GONE);

                }


                if(position==5){

                    tahunDB = "2021";
                    koeftahun =5;

                    tvTahun1.setVisibility(View.GONE);
                    tvTahun2.setVisibility(View.GONE);
                    tvTahun3.setVisibility(View.GONE);
                    tvTahun4.setVisibility(View.GONE);
                    tvTahun5.setVisibility(View.VISIBLE);


                    barChart1.setVisibility(View.GONE);
                    barChart2.setVisibility(View.GONE);
                    barChart3.setVisibility(View.GONE);
                    barChart4.setVisibility(View.GONE);
                    barChart5.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spinnerJenisFile = (Spinner) view.findViewById(R.id.spinner00);
        final BarChart barChart1 = (BarChart) view.findViewById(R.id.chart);
        final BarChart barChart2 = (BarChart) view.findViewById(R.id.chart2);
        final BarChart barChart3 = (BarChart) view.findViewById(R.id.chart3);
        final BarChart barChart4 = (BarChart) view.findViewById(R.id.chart4);
        final BarChart barChart5 = (BarChart) view.findViewById(R.id.chart5);

        Button btnExport = (Button)view.findViewById(R.id.btnExport);

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int idSpinner = spinnerJenisFile.getSelectedItemPosition();

                if(idSpinner == 1){

                    if(koeftahun==1){
                        barChart1.saveToGallery("Ulat Kantung "+tahunDB, 100);
                    }
                    if(koeftahun==2){
                        barChart2.saveToGallery("Ulat Kantung "+tahunDB, 100);
                    }

                    if(koeftahun==3){
                        barChart3.saveToGallery("Ulat Kantung "+tahunDB, 100);
                    }

                    if(koeftahun==4){
                        barChart4.saveToGallery("Ulat Kantung "+tahunDB, 100);
                    }

                    if(koeftahun==5){
                        barChart5.saveToGallery("Ulat Kantung "+tahunDB, 100);
                    }

                    Toast.makeText(getActivity(), "Export Chart Ke File JPG Berhasil", Toast.LENGTH_SHORT).show();



                }else{
                    new Task().execute();
                }

            }
        });

        setData1(view, "2017");
        setData2(view, "2018");
        setData3(view, "2019");
        setData4(view, "2020");
        setData5(view, "2021");

        return view;
    }


    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
        bulan1  = month;
        hari1   = day;
        tahun1  = year;
    }

    private void showDate2(int year, int month, int day) {
        dateView2.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
        bulan2  = month;
        hari2   = day;
        tahun2  = year;
    }

    public boolean initialize_database_ulatKantung() {
        /* open database, if doesn't exist, create it */
        SQLiteDatabase mDatabase = getActivity().openOrCreateDatabase("datasawit.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor c = null;
        boolean tableExists = false;
        /* get cursor on it */
        try {
            c = mDatabase.query("ulat_kantung", null,
                    null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
        /* fail */
            Log.d("STATUS TABEL", "TABEL  doesn't exist :(((");
        }
        return tableExists;
    }

    public void inputUlatKantung(View view) {

        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            kebunDB = id;


        }


        EditText jumlah = (EditText)  getView().findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();
        if (jlh.isEmpty() || jlh.matches("")) {

            Toast.makeText(getActivity(), "Silahkan Masukkan Jumlah Ulat API", Toast.LENGTH_SHORT).show();

        } else {

            DataHelper dbHelper = new DataHelper(getActivity());
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String sbulan1 = "";

            String stanggal1 = "";


            if(bulan1<10){

                sbulan1 = "0"+bulan1;


            }else{
                sbulan1 = ""+bulan1;
            }

            if(hari1<10){

                stanggal1 = "0"+hari1;


            }else{
                stanggal1 = ""+hari1;
            }


            SQLiteStatement s = db.compileStatement("select count(*) from ulat_kantung WHERE dateformat ='" + stanggal1 +  sbulan1 + tahun1+ "'  AND kebun ='" + kebunDB + "' ; ");

            long count = s.simpleQueryForLong();


            if (count > 0) {
                String query = "UPDATE ulat_kantung SET jumlah =" + jlh + " WHERE  dateformat ='" + stanggal1 +  sbulan1 + tahun1+ "'  AND kebun ='" + kebunDB + "' ;" ;
                db.execSQL(query);

                Toast.makeText(getActivity(), "Data Sucessfully Updated", Toast.LENGTH_SHORT).show();

            } else {

                String query = "INSERT INTO ulat_kantung ( dateformat, tanggal1, bulan1,  tanggal2, bulan2, jumlah,  tahun, kebun  ) VALUES ('" +  stanggal1 +  sbulan1 + tahun1 + "', '" + hari1 + "', '" + bulan1 + "', '" + hari2 + "', '" + bulan2 + "', '" + jlh + "','" + tahun1 + "','" + kebunDB + "')";

                //Toast.makeText(getApplicationContext(), "Data Belum Ada" , Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(), "query" + query , Toast.LENGTH_SHORT).show();

                System.out.println("QUERY JUMLAH ULAT KANTUNG " + query);

                db.execSQL(query);

                Toast.makeText(getActivity(), "Data Sucessfully Inserted", Toast.LENGTH_SHORT).show();

            }

        }

        setData1(getView(), "2017");
        setData2(getView(), "2018");
        setData3(getView(), "2019");
        setData4(getView(), "2020");
        setData5(getView(), "2021");

    }

    public void setData1(View v, String Tahun){
       // TO DO
        //1. PERULANGAN DATABASE MENGAMBIL TANGGAL 1 BULAN 1 - TANGGAL 2 - BULAN 2
        //2. PERULANGAN DATABASE MENGAMBIL JUMLAH NILAI
        //3. PENRULANGAN DATABASE MEGAMBIL NILAI DAN MELAKUKAN PERULANGAN WARNA

        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<BarEntry> entries = new ArrayList<>();

        BarChart barChart = (BarChart) v.findViewById(R.id.chart);



        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ulat_kantung WHERE tahun ='"+Tahun+"'",null);

        if (cursor.getCount()>0) {
            daftar = new String[cursor.getCount()];
            warna = new int[cursor.getCount()];
            cursor.moveToFirst();
            for (int cc = 0; cc < cursor.getCount(); cc++) {
                cursor.moveToPosition(cc);

                String CaptionGraph = cursor.getString(2).toString() + " " + getMonth(Integer.parseInt(cursor.getString(3))) + " - " + cursor.getString(4).toString() + " " + getMonth(Integer.parseInt(cursor.getString(5))) + " " + cursor.getString(7);

                daftar[cc] = CaptionGraph;

                int nilai = Integer.parseInt(cursor.getString(6));
                if (nilai < 6) {
                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.hijau);

                } else {

                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.merah);


                }


                labels.add(CaptionGraph);
                entries.add(new BarEntry(Float.parseFloat(cursor.getString(6)), cc));


                System.out.println("DATA PERULANGAN UNTUK DITAMPILKAN " + CaptionGraph);

            }


            BarDataSet dataset = new BarDataSet(entries, "# Jumlah Ulat Kantung ");
            dataset.setColors(warna);


            BarData data = new BarData(labels, dataset);
            // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            barChart.setBorderWidth(0.5f);
            barChart.setData(data);
            barChart.animateY(5000);

        }





    }

    public void setData2(View v, String Tahun){
        // TO DO
        //1. PERULANGAN DATABASE MENGAMBIL TANGGAL 1 BULAN 1 - TANGGAL 2 - BULAN 2
        //2. PERULANGAN DATABASE MENGAMBIL JUMLAH NILAI
        //3. PENRULANGAN DATABASE MEGAMBIL NILAI DAN MELAKUKAN PERULANGAN WARNA

        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<BarEntry> entries = new ArrayList<>();

        BarChart barChart = (BarChart) v.findViewById(R.id.chart2);

        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ulat_kantung WHERE tahun ='"+Tahun+"'",null);

        if (cursor.getCount()>0) {
            daftar = new String[cursor.getCount()];
            warna = new int[cursor.getCount()];
            cursor.moveToFirst();
            for (int cc = 0; cc < cursor.getCount(); cc++) {
                cursor.moveToPosition(cc);

                String CaptionGraph = cursor.getString(2).toString() + " " + getMonth(Integer.parseInt(cursor.getString(3))) + " - " + cursor.getString(4).toString() + " " + getMonth(Integer.parseInt(cursor.getString(5))) + " " + cursor.getString(7);

                daftar[cc] = CaptionGraph;

                int nilai = Integer.parseInt(cursor.getString(6));
                if (nilai < 5) {
                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.hijau);

                } else {

                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.merah);


                }


                labels.add(CaptionGraph);
                entries.add(new BarEntry(Float.parseFloat(cursor.getString(6)), cc));


                System.out.println("DATA PERULANGAN UNTUK DITAMPILKAN " + CaptionGraph);

            }


            BarDataSet dataset = new BarDataSet(entries, "# Jumlah Ulat Kantung ");
            dataset.setColors(warna);


            BarData data = new BarData(labels, dataset);
            // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            barChart.setBorderWidth(0.5f);
            barChart.setData(data);
            barChart.animateY(5000);

        }

    }

    public void setData3(View v, String Tahun){
        // TO DO
        //1. PERULANGAN DATABASE MENGAMBIL TANGGAL 1 BULAN 1 - TANGGAL 2 - BULAN 2
        //2. PERULANGAN DATABASE MENGAMBIL JUMLAH NILAI
        //3. PENRULANGAN DATABASE MEGAMBIL NILAI DAN MELAKUKAN PERULANGAN WARNA

        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<BarEntry> entries = new ArrayList<>();

        BarChart barChart = (BarChart) v.findViewById(R.id.chart3);



        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ulat_kantung WHERE tahun ='"+Tahun+"'",null);

        if (cursor.getCount()>0) {
            daftar = new String[cursor.getCount()];
            warna = new int[cursor.getCount()];
            cursor.moveToFirst();
            for (int cc = 0; cc < cursor.getCount(); cc++) {
                cursor.moveToPosition(cc);

                String CaptionGraph = cursor.getString(2).toString() + " " + getMonth(Integer.parseInt(cursor.getString(3))) + " - " + cursor.getString(4).toString() + " " + getMonth(Integer.parseInt(cursor.getString(5))) + " " + cursor.getString(7);

                daftar[cc] = CaptionGraph;

                int nilai = Integer.parseInt(cursor.getString(6));
                if (nilai < 5) {
                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.hijau);

                } else {

                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.merah);


                }


                labels.add(CaptionGraph);
                entries.add(new BarEntry(Float.parseFloat(cursor.getString(6)), cc));


                System.out.println("DATA PERULANGAN UNTUK DITAMPILKAN " + CaptionGraph);

            }


            BarDataSet dataset = new BarDataSet(entries, "# Jumlah Ulat Kantung ");
            dataset.setColors(warna);


            BarData data = new BarData(labels, dataset);
            // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            barChart.setBorderWidth(0.5f);
            barChart.setData(data);
            barChart.animateY(5000);

        }





    }

    public void setData4(View v, String Tahun){
        // TO DO
        //1. PERULANGAN DATABASE MENGAMBIL TANGGAL 1 BULAN 1 - TANGGAL 2 - BULAN 2
        //2. PERULANGAN DATABASE MENGAMBIL JUMLAH NILAI
        //3. PENRULANGAN DATABASE MEGAMBIL NILAI DAN MELAKUKAN PERULANGAN WARNA

        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<BarEntry> entries = new ArrayList<>();

        BarChart barChart = (BarChart) v.findViewById(R.id.chart4);



        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ulat_kantung WHERE tahun ='"+Tahun+"'",null);

        if (cursor.getCount()>0) {
            daftar = new String[cursor.getCount()];
            warna = new int[cursor.getCount()];
            cursor.moveToFirst();
            for (int cc = 0; cc < cursor.getCount(); cc++) {
                cursor.moveToPosition(cc);

                String CaptionGraph = cursor.getString(2).toString() + " " + getMonth(Integer.parseInt(cursor.getString(3))) + " - " + cursor.getString(4).toString() + " " + getMonth(Integer.parseInt(cursor.getString(5))) + " " + cursor.getString(7);

                daftar[cc] = CaptionGraph;

                int nilai = Integer.parseInt(cursor.getString(6));
                if (nilai < 5) {
                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.hijau);

                } else {

                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.merah);


                }


                labels.add(CaptionGraph);
                entries.add(new BarEntry(Float.parseFloat(cursor.getString(6)), cc));


                System.out.println("DATA PERULANGAN UNTUK DITAMPILKAN " + CaptionGraph);

            }


            BarDataSet dataset = new BarDataSet(entries, "# Jumlah Ulat Kantung ");
            dataset.setColors(warna);


            BarData data = new BarData(labels, dataset);
            // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            barChart.setBorderWidth(0.5f);
            barChart.setData(data);
            barChart.animateY(5000);

        }





    }

    public void setData5(View v, String Tahun){
        // TO DO
        //1. PERULANGAN DATABASE MENGAMBIL TANGGAL 1 BULAN 1 - TANGGAL 2 - BULAN 2
        //2. PERULANGAN DATABASE MENGAMBIL JUMLAH NILAI
        //3. PENRULANGAN DATABASE MEGAMBIL NILAI DAN MELAKUKAN PERULANGAN WARNA

        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<BarEntry> entries = new ArrayList<>();

        BarChart barChart = (BarChart) v.findViewById(R.id.chart5);



        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ulat_kantung WHERE tahun ='"+Tahun+"'",null);

        if (cursor.getCount()>0) {
            daftar = new String[cursor.getCount()];
            warna = new int[cursor.getCount()];
            cursor.moveToFirst();
            for (int cc = 0; cc < cursor.getCount(); cc++) {
                cursor.moveToPosition(cc);

                String CaptionGraph = cursor.getString(2).toString() + " " + getMonth(Integer.parseInt(cursor.getString(3))) + " - " + cursor.getString(4).toString() + " " + getMonth(Integer.parseInt(cursor.getString(5))) + " " + cursor.getString(7);

                daftar[cc] = CaptionGraph;

                int nilai = Integer.parseInt(cursor.getString(6));
                if (nilai < 5) {
                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.hijau);

                } else {

                    warna[cc] = ContextCompat.getColor(getActivity(), R.color.merah);


                }


                labels.add(CaptionGraph);
                entries.add(new BarEntry(Float.parseFloat(cursor.getString(6)), cc));


                System.out.println("DATA PERULANGAN UNTUK DITAMPILKAN " + CaptionGraph);

            }


            BarDataSet dataset = new BarDataSet(entries, "# Jumlah Ulat Kantung ");
            dataset.setColors(warna);


            BarData data = new BarData(labels, dataset);
            // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            barChart.setBorderWidth(0.5f);
            barChart.setData(data);
            barChart.animateY(5000);

        }





    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

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
            Toast.makeText(getActivity(), "Data Berhasil Dieksport Silahkan Cek UlatKantung.csv di SDCARD anda " , Toast.LENGTH_SHORT).show();

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
        File file = new File(exportDir, "UlatKantung.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM ulat_kantung WHERE tahun ='"+tahunDB+"'", null);
            //csvWrite.writeNext(curCSV.getColumnNames());
            String header[] = { "DARI", "KE", "POPULASI/PELEPAH"};
            csvWrite.writeNext(header);


            while (curCSV.moveToNext()) {
                //Which column you want to exprort
                String arrStr[] = {curCSV.getString(2) + "-" + curCSV.getString(3)  + "-" +  curCSV.getString(7)  , curCSV.getString(4)  + "-" +  curCSV.getString(5)  + "-" +  curCSV.getString(7) , curCSV.getString(6)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        } catch (Exception sqlEx) {
            Log.e("TurneraSubulataFragment", sqlEx.getMessage(), sqlEx);
        }
    }
}
