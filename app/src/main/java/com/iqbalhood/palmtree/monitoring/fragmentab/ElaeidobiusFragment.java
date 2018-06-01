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
import android.widget.AdapterView;
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

public class ElaeidobiusFragment extends Fragment {

    String[] tahun;

    String kebunDB = "1";
    String tahunDB = "2017";
    int    koeftahun = 1;


    // spinner1 element
    Spinner spinner1;
    Spinner spinner2;

    LinearLayout layout;

    Spinner spinnerJenisFile;
    Spinner tahunTampil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_jumlah_elaeidobius, container, false);
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
        tahun = new String[5];

        String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};


        for (int i = 0; i < 5; i++) {
            tahun[i] = "" + (th + i);
        }


        // spinner1 element
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);

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

        // attaching data adapter to spinner1
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapterBulan);


        boolean k = initialize_Database_Pohon();

        System.out.println("STATUS  DB Elaeidobius " + k);

        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (k == false) {


            dbHelper.createTableElaeidobius(db);


        }




        Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                inputPHN(v);

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

                    tvTahun1.setVisibility(View.GONE);
                    tvTahun2.setVisibility(View.GONE);
                    tvTahun3.setVisibility(View.GONE);
                    tvTahun4.setVisibility(View.GONE);
                    tvTahun5.setVisibility(View.GONE);


                    barChart1.setVisibility(View.GONE);
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
                        barChart1.saveToGallery("Elaeidobius "+tahunDB, 100);
                    }
                    if(koeftahun==2){
                        barChart2.saveToGallery("Elaeidobius "+tahunDB, 100);
                    }

                    if(koeftahun==3){
                        barChart3.saveToGallery("Elaeidobius "+tahunDB, 100);
                    }

                    if(koeftahun==4){
                        barChart4.saveToGallery("Elaeidobius "+tahunDB, 100);
                    }

                    if(koeftahun==5){
                        barChart5.saveToGallery("Elaeidobius "+tahunDB, 100);
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



    public float Hitung(String month, String kebun, String tahun) {

        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement("select count(*) from elaeidobius WHERE tahun ='" + tahun + "' AND bulan ='" + month + "' AND kebun ='" + kebun + "' ; ");

        long count = s.simpleQueryForLong();
        float returnData = 0;


        if (count > 0) {

            String QPerhitungan = "SELECT\n" +
                    " sum(jumlah)\n" +
                    "FROM\n" +
                    " elaeidobius\n" +
                    "WHERE\n" +
                    " bulan = '" + month + "'\n" +
                    "AND\n" +
                    " kebun = '" + kebun + "'\n" +
                    "AND\n" +
                    " tahun = '" + tahun + "';";

            SQLiteStatement ss = db.compileStatement(QPerhitungan);
            long counts = ss.simpleQueryForLong();

            returnData = counts;


//            Toast.makeText(getActivity(), "Jumlah Data " + counts, Toast.LENGTH_SHORT).show();
        } else {

            returnData = count;

//            Toast.makeText(getActivity(), "Data Bulan Belum ada  Data " + count, Toast.LENGTH_SHORT).show();


        }


        return returnData;

    }


    public void setData1(View v, String Tahun) {

        BarChart barChart = (BarChart) v.findViewById(R.id.chart);
        String kebun = "";
        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {
            kebun = bbb.getString("id");
        }

        float Januari = Hitung("Januari", kebun, Tahun);
        float Februari = Hitung("Februari", kebun, Tahun);
        float Maret = Hitung("Maret", kebun, Tahun);
        float April = Hitung("April", kebun, Tahun);
        float Mei = Hitung("Mei", kebun, Tahun);
        float Juni = Hitung("Juni", kebun, Tahun);
        float Juli = Hitung("Juli", kebun, Tahun);
        float Agustus = Hitung("Agustus", kebun, Tahun);
        float September = Hitung("September", kebun, Tahun);
        float Oktober = Hitung("Oktober", kebun, Tahun);
        float November = Hitung("November", kebun, Tahun);
        float Desember = Hitung("Desember", kebun, Tahun);


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


        BarDataSet dataset = new BarDataSet(entries, " Jumlah Elaeidobius/Ha");

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


        if(Januari < 20000  ){

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Februari < 20000  ){

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Maret < 20000  ){

            colorMaret = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMaret = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(April < 20000  ){

            colorApril = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorApril = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Mei < 20000  ){

            colorMei = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMei = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juni < 20000  ){

            colorJuni = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuni = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juli < 20000  ){

            colorJuli = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuli = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Agustus < 20000  ){

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(September < 20000  ){

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Oktober < 20000  ){

            colorOktober = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorOktober = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(November < 20000  ){

            colorNovember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorNovember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Desember < 20000  ){

            colorDesember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorDesember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }







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
        barChart.setBorderWidth(0.5f);
        barChart.setData(data);
        barChart.animateY(5000);





    }

    public void setData2(View v, String Tahun) {

        BarChart barChart = (BarChart) v.findViewById(R.id.chart2);
        String kebun = "";
        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {
            kebun = bbb.getString("id");
        }

        float Januari = Hitung("Januari", kebun, Tahun);
        float Februari = Hitung("Februari", kebun, Tahun);
        float Maret = Hitung("Maret", kebun, Tahun);
        float April = Hitung("April", kebun, Tahun);
        float Mei = Hitung("Mei", kebun, Tahun);
        float Juni = Hitung("Juni", kebun, Tahun);
        float Juli = Hitung("Juli", kebun, Tahun);
        float Agustus = Hitung("Agustus", kebun, Tahun);
        float September = Hitung("September", kebun, Tahun);
        float Oktober = Hitung("Oktober", kebun, Tahun);
        float November = Hitung("November", kebun, Tahun);
        float Desember = Hitung("Desember", kebun, Tahun);


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


        BarDataSet dataset = new BarDataSet(entries, " Jumlah Elaeidobius/Ha");

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


        if(Januari < 20000  ){

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Februari < 20000  ){

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Maret < 20000  ){

            colorMaret = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMaret = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(April < 20000  ){

            colorApril = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorApril = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Mei < 20000  ){

            colorMei = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMei = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juni < 20000  ){

            colorJuni = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuni = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juli < 20000  ){

            colorJuli = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuli = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Agustus < 20000  ){

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(September < 20000  ){

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Oktober < 20000  ){

            colorOktober = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorOktober = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(November < 20000  ){

            colorNovember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorNovember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Desember < 20000  ){

            colorDesember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorDesember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }







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

    public void setData3(View v, String Tahun) {

        BarChart barChart = (BarChart) v.findViewById(R.id.chart3);
        String kebun = "";
        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {
            kebun = bbb.getString("id");
        }

        float Januari = Hitung("Januari", kebun, Tahun);
        float Februari = Hitung("Februari", kebun, Tahun);
        float Maret = Hitung("Maret", kebun, Tahun);
        float April = Hitung("April", kebun, Tahun);
        float Mei = Hitung("Mei", kebun, Tahun);
        float Juni = Hitung("Juni", kebun, Tahun);
        float Juli = Hitung("Juli", kebun, Tahun);
        float Agustus = Hitung("Agustus", kebun, Tahun);
        float September = Hitung("September", kebun, Tahun);
        float Oktober = Hitung("Oktober", kebun, Tahun);
        float November = Hitung("November", kebun, Tahun);
        float Desember = Hitung("Desember", kebun, Tahun);


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


        BarDataSet dataset = new BarDataSet(entries, " Jumlah Elaeidobius/Ha");

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


        if(Januari < 20000  ){

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Februari < 20000  ){

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Maret < 20000  ){

            colorMaret = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMaret = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(April < 20000  ){

            colorApril = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorApril = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Mei < 20000  ){

            colorMei = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMei = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juni < 20000  ){

            colorJuni = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuni = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juli < 20000  ){

            colorJuli = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuli = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Agustus < 20000  ){

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(September < 20000  ){

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Oktober < 20000  ){

            colorOktober = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorOktober = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(November < 20000  ){

            colorNovember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorNovember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Desember < 20000  ){

            colorDesember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorDesember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }







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

    public void setData4(View v, String Tahun) {

        BarChart barChart = (BarChart) v.findViewById(R.id.chart4);
        String kebun = "";
        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {
            kebun = bbb.getString("id");
        }

        float Januari = Hitung("Januari", kebun, Tahun);
        float Februari = Hitung("Februari", kebun, Tahun);
        float Maret = Hitung("Maret", kebun, Tahun);
        float April = Hitung("April", kebun, Tahun);
        float Mei = Hitung("Mei", kebun, Tahun);
        float Juni = Hitung("Juni", kebun, Tahun);
        float Juli = Hitung("Juli", kebun, Tahun);
        float Agustus = Hitung("Agustus", kebun, Tahun);
        float September = Hitung("September", kebun, Tahun);
        float Oktober = Hitung("Oktober", kebun, Tahun);
        float November = Hitung("November", kebun, Tahun);
        float Desember = Hitung("Desember", kebun, Tahun);


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


        BarDataSet dataset = new BarDataSet(entries, " Jumlah Elaeidobius/Ha");

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


        if(Januari < 20000  ){

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Februari < 20000  ){

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Maret < 20000  ){

            colorMaret = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMaret = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(April < 20000  ){

            colorApril = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorApril = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Mei < 20000  ){

            colorMei = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMei = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juni < 20000  ){

            colorJuni = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuni = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juli < 20000  ){

            colorJuli = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuli = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Agustus < 20000  ){

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(September < 20000  ){

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Oktober < 20000  ){

            colorOktober = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorOktober = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(November < 20000  ){

            colorNovember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorNovember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Desember < 20000  ){

            colorDesember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorDesember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }







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

    public void setData5(View v, String Tahun) {

        BarChart barChart = (BarChart) v.findViewById(R.id.chart5);
        String kebun = "";
        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {
            kebun = bbb.getString("id");
        }

        float Januari = Hitung("Januari", kebun, Tahun);
        float Februari = Hitung("Februari", kebun, Tahun);
        float Maret = Hitung("Maret", kebun, Tahun);
        float April = Hitung("April", kebun, Tahun);
        float Mei = Hitung("Mei", kebun, Tahun);
        float Juni = Hitung("Juni", kebun, Tahun);
        float Juli = Hitung("Juli", kebun, Tahun);
        float Agustus = Hitung("Agustus", kebun, Tahun);
        float September = Hitung("September", kebun, Tahun);
        float Oktober = Hitung("Oktober", kebun, Tahun);
        float November = Hitung("November", kebun, Tahun);
        float Desember = Hitung("Desember", kebun, Tahun);


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


        BarDataSet dataset = new BarDataSet(entries, " Jumlah Elaeidobius/Ha");

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


        if(Januari < 20000  ){

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Februari < 20000  ){

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorFebruari = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Maret < 20000  ){

            colorMaret = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMaret = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(April < 20000  ){

            colorApril = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorApril = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Mei < 20000  ){

            colorMei = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorMei = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juni < 20000  ){

            colorJuni = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuni = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Juli < 20000  ){

            colorJuli = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorJuli = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Agustus < 20000  ){

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorAgustus = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(September < 20000  ){

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorSeptember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Oktober < 20000  ){

            colorOktober = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorOktober = ContextCompat.getColor(getActivity(), R.color.hijau);

        }

        if(November < 20000  ){

            colorNovember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorNovember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if(Desember < 20000  ){

            colorDesember = ContextCompat.getColor(getActivity(), R.color.merah);

        }else{

            colorDesember = ContextCompat.getColor(getActivity(), R.color.hijau);

        }







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

    public boolean initialize_Database_Pohon() {

        /* open database, if doesn't exist, create it */
        SQLiteDatabase mDatabase = getActivity().openOrCreateDatabase("datasawit.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor c = null;
        boolean tableExists = false;
/* get cursor on it */
        try {
            c = mDatabase.query("elaeidobius", null,
                    null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
    /* fail */
            Log.d("STATUS TABEL", "TABEL  doesn't exist :(((");
        }

        return tableExists;


    }


    public void inputPHN(View view) {

        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            kebunDB = id;


        }


        EditText jumlah = (EditText) getView().findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();
        if (jlh.isEmpty() || jlh.matches("")) {

            Toast.makeText(getActivity(), "Silahkan Masukkan Jumlah Pohon", Toast.LENGTH_SHORT).show();

        } else {

            DataHelper dbHelper = new DataHelper(getActivity());
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String tahunspinner = spinner1.getSelectedItem().toString();
            String bulanspinner = spinner2.getSelectedItem().toString();

            SQLiteStatement s = db.compileStatement("select count(*) from elaeidobius WHERE bulan ='" + bulanspinner + "' AND tahun ='" + tahunspinner + "' AND kebun ='" + kebunDB + "' ; ");

            long count = s.simpleQueryForLong();


            if (count > 0) {
                String query = "UPDATE elaeidobius SET jumlah =" + jlh + " WHERE bulan ='" + bulanspinner + "' AND tahun ='" + tahunspinner + "' AND kebun ='" + kebunDB + "' ; " ;
                db.execSQL(query);

                Toast.makeText(getActivity(), "Data Sucessfully Updated", Toast.LENGTH_SHORT).show();

            } else {


                String query = "INSERT INTO elaeidobius (bulan , jumlah,  tahun , kebun ) VALUES ('" + bulanspinner + "','" + jlh + "','" + tahunspinner + "','" + kebunDB + "')";

                //Toast.makeText(getActivity(), "Data Belum Ada" , Toast.LENGTH_SHORT).show();
                // Toast.makeText(getActivity(), "query" + query , Toast.LENGTH_SHORT).show();

                System.out.println("QUERY JUMLAH elaeidobius " + query);

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
            Toast.makeText(getActivity(), "Data Berhasil Dieksport Silahkan Cek Elaeidobius.csv di SDCARD anda " , Toast.LENGTH_SHORT).show();
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

        File file = new File(exportDir, "Elaeidobius.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            // Cursor curCSV = db.rawQuery("SELECT * FROM jumlah_pohon", null);
            //csvWrite.writeNext(curCSV.getColumnNames());
            String header[] = {"ID", "BULAN", "TAHUN", "JUMLAH"};
            csvWrite.writeNext(header);

            String kebun = "";

            Bundle bbb = getActivity().getIntent().getExtras();
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


            String Jan[] = {"01", "JANUARI", "2017", ""+Januari};
            String Feb[] = {"02", "FEBRURARI", "2017", ""+Februari};
            String Mar[] = {"03", "MARET", "2017", ""+Maret};
            String Apr[] = {"04", "APRIL", "2017", ""+April};
            String May[] = {"05", "MEI", "2017", ""+Mei};
            String Jun[] = {"06", "JUNI", "2017", ""+Juni};
            String Jul[] = {"07", "JULI", "2017", ""+Juli};
            String Agu[] = {"08", "AGUSTUS", "2017", ""+Agustus};
            String Sep[] = {"09", "SEPTEMBER", "2017", ""+September};
            String Okt[] = {"10", "OKTOBER", "2017", ""+Oktober};
            String Nov[] = {"11", "NOVEMBER", "2017", ""+November};
            String Des[] = {"12", "DESEMBER", "2017", ""+Desember};


            csvWrite.writeNext(Jan);
            csvWrite.writeNext(Feb);
            csvWrite.writeNext(Mar);
            csvWrite.writeNext(Apr);
            csvWrite.writeNext(May);
            csvWrite.writeNext(Jun);
            csvWrite.writeNext(Jul);
            csvWrite.writeNext(Agu);
            csvWrite.writeNext(Sep);
            csvWrite.writeNext(Okt);
            csvWrite.writeNext(Nov);
            csvWrite.writeNext(Des);
            csvWrite.close();
        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }

    }




}


