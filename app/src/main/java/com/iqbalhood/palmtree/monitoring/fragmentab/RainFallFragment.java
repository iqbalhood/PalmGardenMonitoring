
package com.iqbalhood.palmtree.monitoring.fragmentab;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import au.com.bytecode.opencsv.CSVWriter;

public class RainFallFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;


    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    int koeftahun = 1;

    LinearLayout layout;


    // spinner1 element
    Spinner spinnerJenisFile;
    Spinner tahunTampil;
    String tahunDB = "2017";

    TextView tvDry1;
    TextView tvDry2;
    TextView tvDry3;
    TextView tvDry4;
    TextView tvDry5;


    BarChart barChart ;
    BarChart barChart2 ;
    BarChart barChart3 ;
    BarChart barChart4 ;
    BarChart barChart5 ;




    String tanggal, bulan, tahun;

    String tahunInput = "";

    String kebun = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_input_rainfall, container, false);
        layout = (LinearLayout) view.findViewById(R.id.progressbar_view);

        barChart = (BarChart) view.findViewById(R.id.chart);
        barChart2 = (BarChart) view.findViewById(R.id.chart2);
        barChart3 = (BarChart) view.findViewById(R.id.chart3);
        barChart4 = (BarChart) view.findViewById(R.id.chart4);
        barChart5 = (BarChart) view.findViewById(R.id.chart5);


        dateView = (TextView) view.findViewById(R.id.textView3);


        tvDry1 =  (TextView) view.findViewById(R.id.tvDrySpell);
        tvDry2 =  (TextView) view.findViewById(R.id.tvDrySpell2);
        tvDry3 =  (TextView) view.findViewById(R.id.tvDrySpell3);
        tvDry4 =  (TextView) view.findViewById(R.id.tvDrySpell4);
        tvDry5 =  (TextView) view.findViewById(R.id.tvDrySpell5);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month, day);
        //Hitung();


        Bundle bbb = getActivity().getIntent().getExtras();
        if (bbb != null) {
            String id = bbb.getString("id");
            kebun = id;
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");
            TextView satuanKebun = (TextView) view.findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama + " (" + tahun + ")");
        }

       // new TaskCreate().execute();


        // spinner1 element
        spinnerJenisFile = (Spinner) view.findViewById(R.id.spinner00);
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


                LinearLayout Informasi1 = (LinearLayout)view.findViewById(R.id.Informasi);
                LinearLayout Informasi2 = (LinearLayout)view.findViewById(R.id.Informasi2);
                LinearLayout Informasi3 = (LinearLayout)view.findViewById(R.id.Informasi3);
                LinearLayout Informasi4 = (LinearLayout)view.findViewById(R.id.Informasi4);
                LinearLayout Informasi5 = (LinearLayout)view.findViewById(R.id.Informasi5);

                if(position==0){

                    koeftahun =1;

                    tahunDB = "2017";


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

                    Informasi1.setVisibility(View.VISIBLE);
                    Informasi2.setVisibility(View.VISIBLE);
                    Informasi3.setVisibility(View.VISIBLE);
                    Informasi4.setVisibility(View.VISIBLE);
                    Informasi5.setVisibility(View.VISIBLE);

                }



                if(position==1){

                    koeftahun =1;

                    tahunDB = "2017";


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

                    Informasi1.setVisibility(View.VISIBLE);
                    Informasi2.setVisibility(View.GONE);
                    Informasi3.setVisibility(View.GONE);
                    Informasi4.setVisibility(View.GONE);
                    Informasi5.setVisibility(View.GONE);

                }



                if(position==2){
                    koeftahun =2;
                    tahunDB = "2018";


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

                    Informasi1.setVisibility(View.GONE);
                    Informasi2.setVisibility(View.VISIBLE);
                    Informasi3.setVisibility(View.GONE);
                    Informasi4.setVisibility(View.GONE);
                    Informasi5.setVisibility(View.GONE);



                }


                if(position==3){

                    koeftahun =3;
                    tahunDB = "2019";


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


                    Informasi1.setVisibility(View.GONE);
                    Informasi2.setVisibility(View.GONE);
                    Informasi3.setVisibility(View.VISIBLE);
                    Informasi4.setVisibility(View.GONE);
                    Informasi5.setVisibility(View.GONE);


                }


                if(position==4){

                    koeftahun   = 4;
                    tahunDB     = "2020";


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

                    Informasi1.setVisibility(View.GONE);
                    Informasi2.setVisibility(View.GONE);
                    Informasi3.setVisibility(View.GONE);
                    Informasi4.setVisibility(View.VISIBLE);
                    Informasi5.setVisibility(View.GONE);


                }


                if(position==5){
                    koeftahun   = 5;
                    tahunDB     = "2021";


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

                    Informasi1.setVisibility(View.GONE);
                    Informasi2.setVisibility(View.GONE);
                    Informasi3.setVisibility(View.GONE);
                    Informasi4.setVisibility(View.GONE);
                    Informasi5.setVisibility(View.VISIBLE);

                }



//                Toast.makeText(getActivity(), "Tahun ID " +position,
//               Toast.LENGTH_SHORT)
//              .show();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        ImageButton button = (ImageButton) view.findViewById(R.id.button11);
        Button buttonSubmit = (Button) view.findViewById(R.id.button1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                // setDate(v);
//                show(getFragmentManager(), "datePicker");

                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = "Date" + String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                                + "-" + String.valueOf(dayOfMonth);



                        showDate(year, monthOfYear, dayOfMonth);
                        //  tfDescription.setText(date);
                        ///  tfDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();


            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputCH(view, tahunInput );

            }
        });



        final BarChart barChart = (BarChart) view.findViewById(R.id.chart);
        Button btnExport = (Button)view.findViewById(R.id.btnExport);

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idSpinner = spinnerJenisFile.getSelectedItemPosition();

                if(idSpinner == 1){

                    if(koeftahun==1){
                        barChart.saveToGallery("Curah Hujan "+tahunDB, 100);

                        Toast.makeText(getActivity(), "Grafik Curah Hujan "+ tahunDB +"Berhasil Di Eksport Silahkan Cek Di Gallery" , Toast.LENGTH_SHORT).show();


                    }
                    if(koeftahun==2){
                        barChart2.saveToGallery("Curah Hujan "+tahunDB, 100);
                        Toast.makeText(getActivity(), "Grafik Curah Hujan "+ tahunDB +"Berhasil Di Eksport Silahkan Cek Di Gallery" , Toast.LENGTH_SHORT).show();

                    }

                    if(koeftahun==3){
                        barChart3.saveToGallery("Curah Hujan "+tahunDB, 100);
                        Toast.makeText(getActivity(), "Grafik Curah Hujan "+ tahunDB +"Berhasil Di Eksport Silahkan Cek Di Gallery" , Toast.LENGTH_SHORT).show();

                    }

                    if(koeftahun==4){
                        barChart4.saveToGallery("Curah Hujan "+tahunDB, 100);
                        Toast.makeText(getActivity(), "Grafik Curah Hujan "+ tahunDB +"Berhasil Di Eksport Silahkan Cek Di Gallery" , Toast.LENGTH_SHORT).show();

                    }

                    if(koeftahun==5){
                        barChart5.saveToGallery("Curah Hujan "+tahunDB, 100);
                        Toast.makeText(getActivity(), "Grafik Curah Hujan "+ tahunDB +"Berhasil Di Eksport Silahkan Cek Di Gallery" , Toast.LENGTH_SHORT).show();

                    }

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





//        Handler handler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                // Any UI task, example
//
//                new TaskDry().execute();
//
//            }
//        };
//        handler.sendEmptyMessage(1);
       // DrySpellExcecute();

        return view;
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        getActivity().showDialog(999);
//        Toast.makeText(getApplicationContext(), "ca",
//                Toast.LENGTH_SHORT)
//                .show();
    }


    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(getActivity(),
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
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month+1).append("/").append(year));

        tahunInput = String.valueOf(year);

        if (day < 10) {

            tanggal = "0" + day;

        } else {
            tanggal = "" + day;
        }


        if (month < 10) {

            bulan = "0" + (month+1);

        } else {
            bulan = "" + (month+1);
        }





    }






    public void inputCH(View view , String TInput) {


        EditText jumlah = (EditText) getView().findViewById(R.id.editTextJumlah);
        String jlh = jumlah.getText().toString();

        if (jlh.isEmpty() || jlh.matches("")) {

            Toast.makeText(getActivity(), "Silahkan Masukkan Jumlah Curah Hujan", Toast.LENGTH_SHORT).show();

        } else {

            String query = "UPDATE curah_hujan  SET jumlah =" + jlh + " WHERE   id =" + kebun + TInput + bulan + tanggal + ";";

            System.out.println("QUERY " + query);

         //   Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();


            DataHelper dbHelper = new DataHelper(getActivity());
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            db.execSQL(query);
            Toast.makeText(getActivity(), "Data Recorded Sucessfully", Toast.LENGTH_SHORT).show();


            Intent intent = getActivity().getIntent();
            getActivity().finish();
            startActivity(intent);


        }

    }

    public void DrySpellExcecute(){



        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        System.out.println("/**** DRY SPELL COUNT ***/");

        DataHelper dh = new DataHelper(getActivity());

        String tahun1 =  dh.drySpell(db, kebun, 2017);
        System.out.println("TAHUN 1  " + tahun1);



        System.out.println("/**** DRY SPELL COUNT ***/");

        String tahun2 =  dh.drySpell(db, kebun, 2018);
        System.out.println("TAHUN 2  " + tahun2);

        System.out.println("/**** DRY SPELL COUNT ***/");

        String tahun3 =  dh.drySpell(db, kebun, 2019);
        System.out.println("TAHUN 3  " + tahun3);

        System.out.println("/**** DRY SPELL COUNT ***/");

        String tahun4 =  dh.drySpell(db, kebun, 2020);
        System.out.println("TAHUN 4  " + tahun4);

        System.out.println("/**** DRY SPELL COUNT ***/");


        String tahun5 =  dh.drySpell(db, kebun, 2021);
        System.out.println("TAHUN 5  " + tahun5);

        System.out.println("/**** DRY SPELL COUNT ***/");

        tvDry1.setText("DRY SPELL  "+tahun1);
        tvDry2.setText("DRY SPELL  "+tahun2);
        tvDry3.setText("DRY SPELL  "+tahun3);
        tvDry4.setText("DRY SPELL  "+tahun4);
        tvDry5.setText("DRY SPELL  "+tahun5);

    }


    public float Hitung(String month, String Tahun) {

        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement("select count(*) from curah_hujan WHERE tahun ='" + Tahun + "' AND bulan ='" + month + "' AND kebun ='" + kebun + "' ; ");

        long count = s.simpleQueryForLong();
        float returnData = 0;


        if (count > 0) {

            String QPerhitungan = "SELECT\n" +
                    " sum(jumlah)\n" +
                    "FROM\n" +
                    " curah_hujan\n" +
                    "WHERE\n" +
                    " bulan = '" + month + "'\n" +
                    "AND\n" +
                    " kebun = '" + kebun + "'\n" +
                    "AND\n" +
                    " tahun = '"+Tahun+"';";

            SQLiteStatement ss = db.compileStatement(QPerhitungan);
            long counts = ss.simpleQueryForLong();

            returnData = counts;


           // Toast.makeText(getActivity(), "Jumlah Data " + counts, Toast.LENGTH_SHORT).show();
        } else {

            returnData = count;

           // Toast.makeText(getActivity(), "Data Bulan Belum ada  Data " + count, Toast.LENGTH_SHORT).show();


        }

        s.close();


        return returnData;

    }


    public long HitungHariTidakHujan(String month, String Year) {

        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement("select count(*) from curah_hujan WHERE jumlah ='0' AND tahun ='" + Year + "' AND bulan ='" + month + "' AND kebun ='" + kebun + "' ; ");

        long count = s.simpleQueryForLong();

        s.close();

        return count;

    }

    public long HitungHariHujan(String month, String Year) {

        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement("select count(*) from curah_hujan WHERE jumlah !='0' AND tahun ='" + Year + "' AND bulan ='" + month + "' AND kebun ='" + kebun + "' ; ");

        long count = s.simpleQueryForLong();

        s.close();

        return count;

    }

    public void settingDatabase() {

        DataHelper dbHelper = new DataHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String year1 = "2017";
        String year2 = "2018";
        String year3 = "2019";
        String year4 = "2020";
        String year5 = "2021";


        for (int i = 0; i < 12; i++) {

            String ii = "";

            if ((i + 1) < 10) {

                ii = "0" + (i + 1);

            } else {

                ii = "" + (i + 1);
            }

            SQLiteStatement s = db.compileStatement("select count(*) from curah_hujan WHERE tahun ='" + year1 + "' AND bulan ='" + ii + "' AND kebun ='" + kebun + "'  ; ");
            SQLiteStatement ss = db.compileStatement("select count(*) from curah_hujan WHERE tahun ='" + year2 + "' AND bulan ='" + ii + "' AND kebun ='" + kebun + "'  ; ");
            SQLiteStatement sss = db.compileStatement("select count(*) from curah_hujan WHERE tahun ='" + year3 + "' AND bulan ='" + ii + "' AND kebun ='" + kebun + "'  ; ");
            SQLiteStatement ssss = db.compileStatement("select count(*) from curah_hujan WHERE tahun ='" + year4 + "' AND bulan ='" + ii + "' AND kebun ='" + kebun + "'  ; ");
            SQLiteStatement sssss = db.compileStatement("select count(*) from curah_hujan WHERE tahun ='" + year5 + "' AND bulan ='" + ii + "' AND kebun ='" + kebun + "'  ; ");


            long count1 = s.simpleQueryForLong();
            long count2 = ss.simpleQueryForLong();
            long count3 = sss.simpleQueryForLong();
            long count4 = ssss.simpleQueryForLong();
            long count5 = sssss.simpleQueryForLong();




            if (count1 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.printDateNeo(db, i, kebun, 2017);

            }


            if (count2 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.printDateNeo(db, i, kebun, 2018);

            }

            if (count3 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.printDateNeo(db, i, kebun, 2019);

            }

            if (count4 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.printDateNeo(db, i, kebun, 2020);

            }

            if (count5 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.printDateNeo(db, i, kebun, 2021);

            }






        }


    }


    public void setData1(View view, String Tahun) {


        BarChart barChart = (BarChart) view.findViewById(R.id.chart);
        TextView txtBulanKering = (TextView) view.findViewById(R.id.txtBulanKering);
        TextView txtBulanBasah  = (TextView)view.findViewById(R.id.txtBulanBasah);
        TextView tvBulanBasah = (TextView)view.findViewById(R.id.BulanBasah);
        TextView tvBulanKering = (TextView)view.findViewById(R.id.BulanKering);

        TextView txtInfoHK = (TextView)view.findViewById(R.id.txtInfoHK);
        TextView txtInfoHH = (TextView)view.findViewById(R.id.txtInfoHH);

        TextView txtDefisitAir = (TextView)view.findViewById(R.id.txtDefisitAir);


        int bb = 0 ;
        int bk = 0;


        String BulanBasah = "";
        String BulanKering = "";

        float Januari = Hitung("01",Tahun);
        float Februari = Hitung("02",Tahun);
        float Maret = Hitung("03",Tahun);
        float April = Hitung("04",Tahun);
        float Mei = Hitung("05",Tahun);
        float Juni = Hitung("06",Tahun);
        float Juli = Hitung("07",Tahun);
        float Agustus = Hitung("08",Tahun);
        float September = Hitung("09",Tahun);
        float Oktober = Hitung("10",Tahun);
        float November = Hitung("11",Tahun);
        float Desember = Hitung("12",Tahun);

        String TidakHujan1 = "Jan "+HitungHariTidakHujan("01",  Tahun);
        String TidakHujan2 = ", Feb "+HitungHariTidakHujan("02",  Tahun);
        String TidakHujan3 = ", Mar "+HitungHariTidakHujan("03",  Tahun);
        String TidakHujan4 = ", Apr "+HitungHariTidakHujan("04",  Tahun);
        String TidakHujan5 = ", Mei "+HitungHariTidakHujan("05",  Tahun);
        String TidakHujan6 = ", Jun "+HitungHariTidakHujan("06",  Tahun);
        String TidakHujan7 = ", Jul "+HitungHariTidakHujan("07",  Tahun);
        String TidakHujan8 = ", Agu "+HitungHariTidakHujan("08",  Tahun);
        String TidakHujan9 = ", Sep "+HitungHariTidakHujan("09",  Tahun);
        String TidakHujan10 = ", Okt "+HitungHariTidakHujan("10",  Tahun);
        String TidakHujan11 = ", Nov "+HitungHariTidakHujan("11",  Tahun);
        String TidakHujan12 = ", Des "+HitungHariTidakHujan("12",  Tahun);


        String TidakHujan = TidakHujan1 + TidakHujan2 +TidakHujan3 +TidakHujan4 +TidakHujan5 +TidakHujan6 +TidakHujan7 +TidakHujan8 +TidakHujan9 +TidakHujan10 +TidakHujan11 +TidakHujan12 ;


        String Hujan1 = "Jan "+HitungHariHujan("01",  Tahun);
        String Hujan2 = ", Feb "+HitungHariHujan("02",  Tahun);
        String Hujan3 = ", Mar "+HitungHariHujan("03",  Tahun);
        String Hujan4 = ", Apr "+HitungHariHujan("04",  Tahun);
        String Hujan5 = ", Mei "+HitungHariHujan("05",  Tahun);
        String Hujan6 = ", Jun "+HitungHariHujan("06",  Tahun);
        String Hujan7 = ", Jul "+HitungHariHujan("07",  Tahun);
        String Hujan8 = ", Agu "+HitungHariHujan("08",  Tahun);
        String Hujan9 = ", Sep "+HitungHariHujan("09",  Tahun);
        String Hujan10 = ", Okt "+HitungHariHujan("10",  Tahun);
        String Hujan11 = ", Nov "+HitungHariHujan("11",  Tahun);
        String Hujan12 = ", Des "+HitungHariHujan("12",  Tahun);

        String  Hujan =  Hujan1 +  Hujan2 + Hujan3 + Hujan4 + Hujan5 + Hujan6 + Hujan7 + Hujan8 + Hujan9 + Hujan10 + Hujan11 + Hujan12 ;


        int hh_jan = Math.round(HitungHariHujan("01",  Tahun));
        int ch_jan = Math.round(Januari);
        String DA_JANUARI = defisitAir(" Januari " , hh_jan, ch_jan );

        int hh_feb = Math.round(HitungHariHujan("02",  Tahun));
        int ch_feb = Math.round(Februari);
        String DA_FEBRUARI = defisitAir(" Februari " , hh_feb, ch_feb );


        int hh_mar = Math.round(HitungHariHujan("03",  Tahun));
        int ch_mar = Math.round(Maret);
        String DA_MARET = defisitAir(" Maret " , hh_mar, ch_mar );

        int hh_apr = Math.round(HitungHariHujan("04",  Tahun));
        int ch_apr = Math.round(Maret);
        String DA_APRIL = defisitAir(" April " , hh_apr, ch_apr );


        int hh_mei = Math.round(HitungHariHujan("05",  Tahun));
        int ch_mei = Math.round(Mei);
        String DA_MEI = defisitAir(" Mei " , hh_mei, ch_mei );


        int hh_jun = Math.round(HitungHariHujan("06",  Tahun));
        int ch_jun = Math.round(Juni);
        String DA_JUNI = defisitAir(" Juni " , hh_jun, ch_jun );

        int hh_jul = Math.round(HitungHariHujan("07",  Tahun));
        int ch_jul = Math.round(Juli);
        String DA_JULI = defisitAir(" Juli " , hh_jul, ch_jul );

        int hh_agu = Math.round(HitungHariHujan("08",  Tahun));
        int ch_agu = Math.round(Agustus);
        String DA_AGUSTUS = defisitAir(" Agustus " , hh_agu, ch_agu );


        int hh_sep = Math.round(HitungHariHujan("09",  Tahun));
        int ch_sep = Math.round(Agustus);
        String DA_SEPTEMBER = defisitAir(" September " , hh_sep, ch_sep );


        int hh_okt = Math.round(HitungHariHujan("10",  Tahun));
        int ch_okt = Math.round(Oktober);
        String DA_OKTOBER = defisitAir(" Oktober " , hh_okt, ch_okt );


        int hh_nov = Math.round(HitungHariHujan("11",  Tahun));
        int ch_nov = Math.round(November);
        String DA_NOVEMBER = defisitAir(" November " , hh_nov, ch_nov );


        int hh_des = Math.round(HitungHariHujan("12",  Tahun));
        int ch_des = Math.round(Desember);
        String DA_DESEMBER = defisitAir(" Desember " , hh_des, ch_des );
        String  DADISPLAY  = DA_JANUARI + DA_FEBRUARI + DA_MARET + DA_APRIL + DA_MEI + DA_JUNI + DA_JULI+ DA_AGUSTUS +DA_SEPTEMBER + DA_OKTOBER + DA_NOVEMBER +DA_DESEMBER;


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


        if (Januari < 60) {
            bk +=1;

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Jan, ";



        } else {
            bb +=1;


            colorJanuari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Jan, ";

        }


        if (Februari < 60) {

            bk +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Feb, ";

        } else {
            bb +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Feb, ";

        }


        if (Maret < 60) {
            bk +=1;

            BulanKering += "Mar, ";

            colorMaret = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            colorMaret = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Mar, ";
        }


        if (April < 60) {
            bk +=1;


            BulanKering += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;


            BulanBasah += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Mei < 60) {
            bk +=1;


            BulanKering += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juni < 60) {
            bk +=1;


            BulanKering += "Jun, ";

            colorJuni = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Jun, ";


            colorJuni = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juli < 60) {
            bk +=1;

            BulanKering += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;



            BulanBasah += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Agustus < 60) {
            bk +=1;

            BulanKering += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (September < 60) {
            bk +=1;

            BulanKering += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Oktober < 60) {
            bk +=1;


            BulanKering += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (November < 60) {
            bk +=1;


            BulanKering += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Desember < 60) {
            bk +=1;


            BulanKering += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.birumuda);

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


        BarDataSet dataset = new BarDataSet(entries, "Curah Hujan dalam mm ");

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


        txtBulanBasah.setText(BulanBasah);
        txtBulanKering.setText(BulanKering);
        tvBulanBasah.setText("(BB) Bulan Basah = "+bb);
        tvBulanKering.setText("(BK) Bulan Kering = "+bk);

        txtInfoHK.setText(TidakHujan);
        txtInfoHH.setText(Hujan);
        if(DADISPLAY != null && !DADISPLAY.isEmpty()) {
            /* do your stuffs here */
            txtDefisitAir.setText(DADISPLAY);

        }




     }




    public void setData2(View view, String Tahun) {


        BarChart barChart = (BarChart) view.findViewById(R.id.chart2);
        TextView txtBulanKering = (TextView) view.findViewById(R.id.txtBulanKering2);
        TextView txtBulanBasah  = (TextView)view.findViewById(R.id.txtBulanBasah2);
        TextView tvBulanBasah = (TextView)view.findViewById(R.id.BulanBasah2);
        TextView tvBulanKering = (TextView)view.findViewById(R.id.BulanKering2);

        TextView txtInfoHK = (TextView)view.findViewById(R.id.txtInfoHK2);
        TextView txtInfoHH = (TextView)view.findViewById(R.id.txtInfoHH2);
        TextView txtDefisitAir = (TextView)view.findViewById(R.id.txtDefisitAir2);


        int bb = 0 ;
        int bk = 0;


        String BulanBasah = "";
        String BulanKering = "";

        float Januari = Hitung("01",Tahun);
        float Februari = Hitung("02",Tahun);
        float Maret = Hitung("03",Tahun);
        float April = Hitung("04",Tahun);
        float Mei = Hitung("05",Tahun);
        float Juni = Hitung("06",Tahun);
        float Juli = Hitung("07","2018");
        float Agustus = Hitung("08","2018");
        float September = Hitung("09","2018");
        float Oktober = Hitung("10",Tahun);
        float November = Hitung("11",Tahun);
        float Desember = Hitung("12",Tahun);

        String TidakHujan1 = "Jan "+HitungHariTidakHujan("01",  Tahun);
        String TidakHujan2 = ", Feb "+HitungHariTidakHujan("02",  Tahun);
        String TidakHujan3 = ", Mar "+HitungHariTidakHujan("03",  Tahun);
        String TidakHujan4 = ", Apr "+HitungHariTidakHujan("04",  Tahun);
        String TidakHujan5 = ", Mei "+HitungHariTidakHujan("05",  Tahun);
        String TidakHujan6 = ", Jun "+HitungHariTidakHujan("06",  Tahun);
        String TidakHujan7 = ", Jul "+HitungHariTidakHujan("07",  Tahun);
        String TidakHujan8 = ", Agu "+HitungHariTidakHujan("08",  Tahun);
        String TidakHujan9 = ", Sep "+HitungHariTidakHujan("09",  Tahun);
        String TidakHujan10 = ", Okt "+HitungHariTidakHujan("10",  Tahun);
        String TidakHujan11 = ", Nov "+HitungHariTidakHujan("11",  Tahun);
        String TidakHujan12 = ", Des "+HitungHariTidakHujan("12",  Tahun);


        String TidakHujan = TidakHujan1 + TidakHujan2 +TidakHujan3 +TidakHujan4 +TidakHujan5 +TidakHujan6 +TidakHujan7 +TidakHujan8 +TidakHujan9 +TidakHujan10 +TidakHujan11 +TidakHujan12 ;


        String Hujan1 = "Jan "+HitungHariHujan("01",  Tahun);
        String Hujan2 = ", Feb "+HitungHariHujan("02",  Tahun);
        String Hujan3 = ", Mar "+HitungHariHujan("03",  Tahun);
        String Hujan4 = ", Apr "+HitungHariHujan("04",  Tahun);
        String Hujan5 = ", Mei "+HitungHariHujan("05",  Tahun);
        String Hujan6 = ", Jun "+HitungHariHujan("06",  Tahun);
        String Hujan7 = ", Jul "+HitungHariHujan("07",  Tahun);
        String Hujan8 = ", Agu "+HitungHariHujan("08",  Tahun);
        String Hujan9 = ", Sep "+HitungHariHujan("09",  Tahun);
        String Hujan10 = ", Okt "+HitungHariHujan("10",  Tahun);
        String Hujan11 = ", Nov "+HitungHariHujan("11",  Tahun);
        String Hujan12 = ", Des "+HitungHariHujan("12",  Tahun);

        String  Hujan =  Hujan1 +  Hujan2 + Hujan3 + Hujan4 + Hujan5 + Hujan6 + Hujan7 + Hujan8 + Hujan9 + Hujan10 + Hujan11 + Hujan12 ;


        int hh_jan = Math.round(HitungHariHujan("01",  Tahun));
        int ch_jan = Math.round(Januari);
        String DA_JANUARI = defisitAir(" Januari " , hh_jan, ch_jan );

        int hh_feb = Math.round(HitungHariHujan("02",  Tahun));
        int ch_feb = Math.round(Februari);
        String DA_FEBRUARI = defisitAir(" Februari " , hh_feb, ch_feb );


        int hh_mar = Math.round(HitungHariHujan("03",  Tahun));
        int ch_mar = Math.round(Maret);
        String DA_MARET = defisitAir(" Maret " , hh_mar, ch_mar );

        int hh_apr = Math.round(HitungHariHujan("04",  Tahun));
        int ch_apr = Math.round(Maret);
        String DA_APRIL = defisitAir(" April " , hh_apr, ch_apr );


        int hh_mei = Math.round(HitungHariHujan("05",  Tahun));
        int ch_mei = Math.round(Mei);
        String DA_MEI = defisitAir(" Mei " , hh_mei, ch_mei );


        int hh_jun = Math.round(HitungHariHujan("06",  Tahun));
        int ch_jun = Math.round(Juni);
        String DA_JUNI = defisitAir(" Juni " , hh_jun, ch_jun );

        int hh_jul = Math.round(HitungHariHujan("07",  Tahun));
        int ch_jul = Math.round(Juli);
        String DA_JULI = defisitAir(" Juli " , hh_jul, ch_jul );

        int hh_agu = Math.round(HitungHariHujan("08",  Tahun));
        int ch_agu = Math.round(Agustus);
        String DA_AGUSTUS = defisitAir(" Agustus " , hh_agu, ch_agu );


        int hh_sep = Math.round(HitungHariHujan("09",  Tahun));
        int ch_sep = Math.round(Agustus);
        String DA_SEPTEMBER = defisitAir(" September " , hh_sep, ch_sep );


        int hh_okt = Math.round(HitungHariHujan("10",  Tahun));
        int ch_okt = Math.round(Oktober);
        String DA_OKTOBER = defisitAir(" Oktober " , hh_okt, ch_okt );


        int hh_nov = Math.round(HitungHariHujan("11",  Tahun));
        int ch_nov = Math.round(November);
        String DA_NOVEMBER = defisitAir(" November " , hh_nov, ch_nov );


        int hh_des = Math.round(HitungHariHujan("12",  Tahun));
        int ch_des = Math.round(Desember);
        String DA_DESEMBER = defisitAir(" Desember " , hh_des, ch_des );
        String  DADISPLAY  = DA_JANUARI + DA_FEBRUARI + DA_MARET + DA_APRIL + DA_MEI + DA_JUNI + DA_JULI+ DA_AGUSTUS +DA_SEPTEMBER + DA_OKTOBER + DA_NOVEMBER +DA_DESEMBER;


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


        if (Januari < 60) {
            bk +=1;

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Jan, ";



        } else {
            bb +=1;


            colorJanuari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Jan, ";

        }


        if (Februari < 60) {

            bk +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Feb, ";

        } else {
            bb +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Feb, ";

        }


        if (Maret < 60) {
            bk +=1;

            BulanKering += "Mar, ";

            colorMaret = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            colorMaret = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Mar, ";
        }


        if (April < 60) {
            bk +=1;


            BulanKering += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;


            BulanBasah += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Mei < 60) {
            bk +=1;


            BulanKering += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juni < 60) {
            bk +=1;


            BulanKering += "Jun, ";

            colorJuni = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Jun, ";


            colorJuni = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juli < 60) {
            bk +=1;

            BulanKering += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;



            BulanBasah += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.hijau);

        }


        if (Agustus < 60) {
            bk +=1;

            BulanKering += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (September < 60) {
            bk +=1;

            BulanKering += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Oktober < 60) {
            bk +=1;


            BulanKering += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (November < 60) {
            bk +=1;


            BulanKering += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Desember < 60) {
            bk +=1;


            BulanKering += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.birumuda);

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


        BarDataSet dataset = new BarDataSet(entries, "Curah Hujan dalam mm ");

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


        txtBulanBasah.setText(BulanBasah);
        txtBulanKering.setText(BulanKering);
        tvBulanBasah.setText("(BB) Bulan Basah = "+bb);
        tvBulanKering.setText("(BK) Bulan Kering = "+bk);

        txtInfoHK.setText(TidakHujan);
        txtInfoHH.setText(Hujan);
        if(DADISPLAY != null && !DADISPLAY.isEmpty()) {
            /* do your stuffs here */
            txtDefisitAir.setText(DADISPLAY);

        }

    }

    public void setData3(View view, String Tahun) {


        BarChart barChart = (BarChart) view.findViewById(R.id.chart3);
        TextView txtBulanKering = (TextView) view.findViewById(R.id.txtBulanKering3);
        TextView txtBulanBasah  = (TextView)view.findViewById(R.id.txtBulanBasah3);
        TextView tvBulanBasah = (TextView)view.findViewById(R.id.BulanBasah3);
        TextView tvBulanKering = (TextView)view.findViewById(R.id.BulanKering3);

        TextView txtInfoHK = (TextView)view.findViewById(R.id.txtInfoHK3);
        TextView txtInfoHH = (TextView)view.findViewById(R.id.txtInfoHH3);
        TextView txtDefisitAir = (TextView)view.findViewById(R.id.txtDefisitAir3);


        int bb = 0 ;
        int bk = 0;


        String BulanBasah = "";
        String BulanKering = "";

        float Januari = Hitung("01",Tahun);
        float Februari = Hitung("02",Tahun);
        float Maret = Hitung("03",Tahun);
        float April = Hitung("04",Tahun);
        float Mei = Hitung("05",Tahun);
        float Juni = Hitung("06",Tahun);
        float Juli = Hitung("07",Tahun);
        float Agustus = Hitung("08",Tahun);
        float September = Hitung("09",Tahun);
        float Oktober = Hitung("10",Tahun);
        float November = Hitung("11",Tahun);
        float Desember = Hitung("12",Tahun);

        String TidakHujan1 = "Jan "+HitungHariTidakHujan("01",  Tahun);
        String TidakHujan2 = ", Feb "+HitungHariTidakHujan("02",  Tahun);
        String TidakHujan3 = ", Mar "+HitungHariTidakHujan("03",  Tahun);
        String TidakHujan4 = ", Apr "+HitungHariTidakHujan("04",  Tahun);
        String TidakHujan5 = ", Mei "+HitungHariTidakHujan("05",  Tahun);
        String TidakHujan6 = ", Jun "+HitungHariTidakHujan("06",  Tahun);
        String TidakHujan7 = ", Jul "+HitungHariTidakHujan("07",  Tahun);
        String TidakHujan8 = ", Agu "+HitungHariTidakHujan("08",  Tahun);
        String TidakHujan9 = ", Sep "+HitungHariTidakHujan("09",  Tahun);
        String TidakHujan10 = ", Okt "+HitungHariTidakHujan("10",  Tahun);
        String TidakHujan11 = ", Nov "+HitungHariTidakHujan("11",  Tahun);
        String TidakHujan12 = ", Des "+HitungHariTidakHujan("12",  Tahun);


        String TidakHujan = TidakHujan1 + TidakHujan2 +TidakHujan3 +TidakHujan4 +TidakHujan5 +TidakHujan6 +TidakHujan7 +TidakHujan8 +TidakHujan9 +TidakHujan10 +TidakHujan11 +TidakHujan12 ;


        String Hujan1 = "Jan "+HitungHariHujan("01",  Tahun);
        String Hujan2 = ", Feb "+HitungHariHujan("02",  Tahun);
        String Hujan3 = ", Mar "+HitungHariHujan("03",  Tahun);
        String Hujan4 = ", Apr "+HitungHariHujan("04",  Tahun);
        String Hujan5 = ", Mei "+HitungHariHujan("05",  Tahun);
        String Hujan6 = ", Jun "+HitungHariHujan("06",  Tahun);
        String Hujan7 = ", Jul "+HitungHariHujan("07",  Tahun);
        String Hujan8 = ", Agu "+HitungHariHujan("08",  Tahun);
        String Hujan9 = ", Sep "+HitungHariHujan("09",  Tahun);
        String Hujan10 = ", Okt "+HitungHariHujan("10",  Tahun);
        String Hujan11 = ", Nov "+HitungHariHujan("11",  Tahun);
        String Hujan12 = ", Des "+HitungHariHujan("12",  Tahun);

        String  Hujan =  Hujan1 +  Hujan2 + Hujan3 + Hujan4 + Hujan5 + Hujan6 + Hujan7 + Hujan8 + Hujan9 + Hujan10 + Hujan11 + Hujan12 ;

        int hh_jan = Math.round(HitungHariHujan("01",  Tahun));
        int ch_jan = Math.round(Januari);
        String DA_JANUARI = defisitAir(" Januari " , hh_jan, ch_jan );

        int hh_feb = Math.round(HitungHariHujan("02",  Tahun));
        int ch_feb = Math.round(Februari);
        String DA_FEBRUARI = defisitAir(" Februari " , hh_feb, ch_feb );


        int hh_mar = Math.round(HitungHariHujan("03",  Tahun));
        int ch_mar = Math.round(Maret);
        String DA_MARET = defisitAir(" Maret " , hh_mar, ch_mar );

        int hh_apr = Math.round(HitungHariHujan("04",  Tahun));
        int ch_apr = Math.round(Maret);
        String DA_APRIL = defisitAir(" April " , hh_apr, ch_apr );


        int hh_mei = Math.round(HitungHariHujan("05",  Tahun));
        int ch_mei = Math.round(Mei);
        String DA_MEI = defisitAir(" Mei " , hh_mei, ch_mei );


        int hh_jun = Math.round(HitungHariHujan("06",  Tahun));
        int ch_jun = Math.round(Juni);
        String DA_JUNI = defisitAir(" Juni " , hh_jun, ch_jun );

        int hh_jul = Math.round(HitungHariHujan("07",  Tahun));
        int ch_jul = Math.round(Juli);
        String DA_JULI = defisitAir(" Juli " , hh_jul, ch_jul );

        int hh_agu = Math.round(HitungHariHujan("08",  Tahun));
        int ch_agu = Math.round(Agustus);
        String DA_AGUSTUS = defisitAir(" Agustus " , hh_agu, ch_agu );


        int hh_sep = Math.round(HitungHariHujan("09",  Tahun));
        int ch_sep = Math.round(Agustus);
        String DA_SEPTEMBER = defisitAir(" September " , hh_sep, ch_sep );


        int hh_okt = Math.round(HitungHariHujan("10",  Tahun));
        int ch_okt = Math.round(Oktober);
        String DA_OKTOBER = defisitAir(" Oktober " , hh_okt, ch_okt );


        int hh_nov = Math.round(HitungHariHujan("11",  Tahun));
        int ch_nov = Math.round(November);
        String DA_NOVEMBER = defisitAir(" November " , hh_nov, ch_nov );


        int hh_des = Math.round(HitungHariHujan("12",  Tahun));
        int ch_des = Math.round(Desember);
        String DA_DESEMBER = defisitAir(" Desember " , hh_des, ch_des );
        String  DADISPLAY  = DA_JANUARI + DA_FEBRUARI + DA_MARET + DA_APRIL + DA_MEI + DA_JUNI + DA_JULI+ DA_AGUSTUS +DA_SEPTEMBER + DA_OKTOBER + DA_NOVEMBER +DA_DESEMBER;



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


        if (Januari < 60) {
            bk +=1;

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Jan, ";



        } else {
            bb +=1;


            colorJanuari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Jan, ";

        }


        if (Februari < 60) {

            bk +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Feb, ";

        } else {
            bb +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Feb, ";

        }


        if (Maret < 60) {
            bk +=1;

            BulanKering += "Mar, ";

            colorMaret = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            colorMaret = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Mar, ";
        }


        if (April < 60) {
            bk +=1;


            BulanKering += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;


            BulanBasah += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Mei < 60) {
            bk +=1;


            BulanKering += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juni < 60) {
            bk +=1;


            BulanKering += "Jun, ";

            colorJuni = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Jun, ";


            colorJuni = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juli < 60) {
            bk +=1;

            BulanKering += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;



            BulanBasah += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Agustus < 60) {
            bk +=1;

            BulanKering += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (September < 60) {
            bk +=1;

            BulanKering += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Oktober < 60) {
            bk +=1;


            BulanKering += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (November < 60) {
            bk +=1;


            BulanKering += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Desember < 60) {
            bk +=1;


            BulanKering += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.birumuda);

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


        BarDataSet dataset = new BarDataSet(entries, "Curah Hujan dalam mm ");

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


        txtBulanBasah.setText(BulanBasah);
        txtBulanKering.setText(BulanKering);
        tvBulanBasah.setText("(BB) Bulan Basah = "+bb);
        tvBulanKering.setText("(BK) Bulan Kering = "+bk);

        txtInfoHK.setText(TidakHujan);
        txtInfoHH.setText(Hujan);
        if(DADISPLAY != null && !DADISPLAY.isEmpty()) {
            /* do your stuffs here */
            txtDefisitAir.setText(DADISPLAY);

        }


    }

    public void setData4(View view, String Tahun) {


        BarChart barChart = (BarChart) view.findViewById(R.id.chart4);
        TextView txtBulanKering = (TextView) view.findViewById(R.id.txtBulanKering4);
        TextView txtBulanBasah  = (TextView)view.findViewById(R.id.txtBulanBasah4);
        TextView tvBulanBasah = (TextView)view.findViewById(R.id.BulanBasah4);
        TextView tvBulanKering = (TextView)view.findViewById(R.id.BulanKering4);

        TextView txtInfoHK = (TextView)view.findViewById(R.id.txtInfoHK4);
        TextView txtInfoHH = (TextView)view.findViewById(R.id.txtInfoHH4);
        TextView txtDefisitAir = (TextView)view.findViewById(R.id.txtDefisitAir4);


        int bb = 0 ;
        int bk = 0;


        String BulanBasah = "";
        String BulanKering = "";

        float Januari = Hitung("01",Tahun);
        float Februari = Hitung("02",Tahun);
        float Maret = Hitung("03",Tahun);
        float April = Hitung("04",Tahun);
        float Mei = Hitung("05",Tahun);
        float Juni = Hitung("06",Tahun);
        float Juli = Hitung("07",Tahun);
        float Agustus = Hitung("08",Tahun);
        float September = Hitung("09",Tahun);
        float Oktober = Hitung("10",Tahun);
        float November = Hitung("11",Tahun);
        float Desember = Hitung("12",Tahun);

        String TidakHujan1 = "Jan "+HitungHariTidakHujan("01",  Tahun);
        String TidakHujan2 = ", Feb "+HitungHariTidakHujan("02",  Tahun);
        String TidakHujan3 = ", Mar "+HitungHariTidakHujan("03",  Tahun);
        String TidakHujan4 = ", Apr "+HitungHariTidakHujan("04",  Tahun);
        String TidakHujan5 = ", Mei "+HitungHariTidakHujan("05",  Tahun);
        String TidakHujan6 = ", Jun "+HitungHariTidakHujan("06",  Tahun);
        String TidakHujan7 = ", Jul "+HitungHariTidakHujan("07",  Tahun);
        String TidakHujan8 = ", Agu "+HitungHariTidakHujan("08",  Tahun);
        String TidakHujan9 = ", Sep "+HitungHariTidakHujan("09",  Tahun);
        String TidakHujan10 = ", Okt "+HitungHariTidakHujan("10",  Tahun);
        String TidakHujan11 = ", Nov "+HitungHariTidakHujan("11",  Tahun);
        String TidakHujan12 = ", Des "+HitungHariTidakHujan("12",  Tahun);


        String TidakHujan = TidakHujan1 + TidakHujan2 +TidakHujan3 +TidakHujan4 +TidakHujan5 +TidakHujan6 +TidakHujan7 +TidakHujan8 +TidakHujan9 +TidakHujan10 +TidakHujan11 +TidakHujan12 ;


        String Hujan1 = "Jan "+HitungHariHujan("01",  Tahun);
        String Hujan2 = ", Feb "+HitungHariHujan("02",  Tahun);
        String Hujan3 = ", Mar "+HitungHariHujan("03",  Tahun);
        String Hujan4 = ", Apr "+HitungHariHujan("04",  Tahun);
        String Hujan5 = ", Mei "+HitungHariHujan("05",  Tahun);
        String Hujan6 = ", Jun "+HitungHariHujan("06",  Tahun);
        String Hujan7 = ", Jul "+HitungHariHujan("07",  Tahun);
        String Hujan8 = ", Agu "+HitungHariHujan("08",  Tahun);
        String Hujan9 = ", Sep "+HitungHariHujan("09",  Tahun);
        String Hujan10 = ", Okt "+HitungHariHujan("10",  Tahun);
        String Hujan11 = ", Nov "+HitungHariHujan("11",  Tahun);
        String Hujan12 = ", Des "+HitungHariHujan("12",  Tahun);

        String  Hujan =  Hujan1 +  Hujan2 + Hujan3 + Hujan4 + Hujan5 + Hujan6 + Hujan7 + Hujan8 + Hujan9 + Hujan10 + Hujan11 + Hujan12 ;

        int hh_jan = Math.round(HitungHariHujan("01",  Tahun));
        int ch_jan = Math.round(Januari);
        String DA_JANUARI = defisitAir(" Januari " , hh_jan, ch_jan );

        int hh_feb = Math.round(HitungHariHujan("02",  Tahun));
        int ch_feb = Math.round(Februari);
        String DA_FEBRUARI = defisitAir(" Februari " , hh_feb, ch_feb );


        int hh_mar = Math.round(HitungHariHujan("03",  Tahun));
        int ch_mar = Math.round(Maret);
        String DA_MARET = defisitAir(" Maret " , hh_mar, ch_mar );

        int hh_apr = Math.round(HitungHariHujan("04",  Tahun));
        int ch_apr = Math.round(Maret);
        String DA_APRIL = defisitAir(" April " , hh_apr, ch_apr );


        int hh_mei = Math.round(HitungHariHujan("05",  Tahun));
        int ch_mei = Math.round(Mei);
        String DA_MEI = defisitAir(" Mei " , hh_mei, ch_mei );


        int hh_jun = Math.round(HitungHariHujan("06",  Tahun));
        int ch_jun = Math.round(Juni);
        String DA_JUNI = defisitAir(" Juni " , hh_jun, ch_jun );

        int hh_jul = Math.round(HitungHariHujan("07",  Tahun));
        int ch_jul = Math.round(Juli);
        String DA_JULI = defisitAir(" Juli " , hh_jul, ch_jul );

        int hh_agu = Math.round(HitungHariHujan("08",  Tahun));
        int ch_agu = Math.round(Agustus);
        String DA_AGUSTUS = defisitAir(" Agustus " , hh_agu, ch_agu );


        int hh_sep = Math.round(HitungHariHujan("09",  Tahun));
        int ch_sep = Math.round(Agustus);
        String DA_SEPTEMBER = defisitAir(" September " , hh_sep, ch_sep );


        int hh_okt = Math.round(HitungHariHujan("10",  Tahun));
        int ch_okt = Math.round(Oktober);
        String DA_OKTOBER = defisitAir(" Oktober " , hh_okt, ch_okt );


        int hh_nov = Math.round(HitungHariHujan("11",  Tahun));
        int ch_nov = Math.round(November);
        String DA_NOVEMBER = defisitAir(" November " , hh_nov, ch_nov );


        int hh_des = Math.round(HitungHariHujan("12",  Tahun));
        int ch_des = Math.round(Desember);
        String DA_DESEMBER = defisitAir(" Desember " , hh_des, ch_des );
        String  DADISPLAY  = DA_JANUARI + DA_FEBRUARI + DA_MARET + DA_APRIL + DA_MEI + DA_JUNI + DA_JULI+ DA_AGUSTUS +DA_SEPTEMBER + DA_OKTOBER + DA_NOVEMBER +DA_DESEMBER;



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


        if (Januari < 60) {
            bk +=1;

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Jan, ";



        } else {
            bb +=1;


            colorJanuari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Jan, ";

        }


        if (Februari < 60) {

            bk +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Feb, ";

        } else {
            bb +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Feb, ";

        }


        if (Maret < 60) {
            bk +=1;

            BulanKering += "Mar, ";

            colorMaret = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            colorMaret = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Mar, ";
        }


        if (April < 60) {
            bk +=1;


            BulanKering += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;


            BulanBasah += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Mei < 60) {
            bk +=1;


            BulanKering += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juni < 60) {
            bk +=1;


            BulanKering += "Jun, ";

            colorJuni = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Jun, ";


            colorJuni = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juli < 60) {
            bk +=1;

            BulanKering += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;



            BulanBasah += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Agustus < 60) {
            bk +=1;

            BulanKering += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (September < 60) {
            bk +=1;

            BulanKering += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Oktober < 60) {
            bk +=1;


            BulanKering += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (November < 60) {
            bk +=1;


            BulanKering += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Desember < 60) {
            bk +=1;


            BulanKering += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.birumuda);

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


        BarDataSet dataset = new BarDataSet(entries, "Curah Hujan dalam mm ");

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


        txtBulanBasah.setText(BulanBasah);
        txtBulanKering.setText(BulanKering);
        tvBulanBasah.setText("(BB) Bulan Basah = "+bb);
        tvBulanKering.setText("(BK) Bulan Kering = "+bk);

        txtInfoHK.setText(TidakHujan);
        txtInfoHH.setText(Hujan);
        if(DADISPLAY != null && !DADISPLAY.isEmpty()) {
            /* do your stuffs here */
            txtDefisitAir.setText(DADISPLAY);

        }



    }

    public void setData5(View view, String Tahun) {


        BarChart barChart = (BarChart) view.findViewById(R.id.chart5);
        TextView txtBulanKering = (TextView) view.findViewById(R.id.txtBulanKering5);
        TextView txtBulanBasah  = (TextView)view.findViewById(R.id.txtBulanBasah5);
        TextView tvBulanBasah = (TextView)view.findViewById(R.id.BulanBasah5);
        TextView tvBulanKering = (TextView)view.findViewById(R.id.BulanKering5);

        TextView txtInfoHK = (TextView)view.findViewById(R.id.txtInfoHK5);
        TextView txtInfoHH = (TextView)view.findViewById(R.id.txtInfoHH5);
        TextView txtDefisitAir = (TextView)view.findViewById(R.id.txtDefisitAir5);


        int bb = 0 ;
        int bk = 0;


        String BulanBasah = "";
        String BulanKering = "";

        float Januari = Hitung("01",Tahun);
        float Februari = Hitung("02",Tahun);
        float Maret = Hitung("03",Tahun);
        float April = Hitung("04",Tahun);
        float Mei = Hitung("05",Tahun);
        float Juni = Hitung("06",Tahun);
        float Juli = Hitung("07",Tahun);
        float Agustus = Hitung("08",Tahun);
        float September = Hitung("09",Tahun);
        float Oktober = Hitung("10",Tahun);
        float November = Hitung("11",Tahun);
        float Desember = Hitung("12",Tahun);

        String TidakHujan1 = "Jan "+HitungHariTidakHujan("01",  Tahun);
        String TidakHujan2 = ", Feb "+HitungHariTidakHujan("02",  Tahun);
        String TidakHujan3 = ", Mar "+HitungHariTidakHujan("03",  Tahun);
        String TidakHujan4 = ", Apr "+HitungHariTidakHujan("04",  Tahun);
        String TidakHujan5 = ", Mei "+HitungHariTidakHujan("05",  Tahun);
        String TidakHujan6 = ", Jun "+HitungHariTidakHujan("06",  Tahun);
        String TidakHujan7 = ", Jul "+HitungHariTidakHujan("07",  Tahun);
        String TidakHujan8 = ", Agu "+HitungHariTidakHujan("08",  Tahun);
        String TidakHujan9 = ", Sep "+HitungHariTidakHujan("09",  Tahun);
        String TidakHujan10 = ", Okt "+HitungHariTidakHujan("10",  Tahun);
        String TidakHujan11 = ", Nov "+HitungHariTidakHujan("11",  Tahun);
        String TidakHujan12 = ", Des "+HitungHariTidakHujan("12",  Tahun);


        String TidakHujan = TidakHujan1 + TidakHujan2 +TidakHujan3 +TidakHujan4 +TidakHujan5 +TidakHujan6 +TidakHujan7 +TidakHujan8 +TidakHujan9 +TidakHujan10 +TidakHujan11 +TidakHujan12 ;


        String Hujan1 = "Jan "+HitungHariHujan("01",  Tahun);
        String Hujan2 = ", Feb "+HitungHariHujan("02",  Tahun);
        String Hujan3 = ", Mar "+HitungHariHujan("03",  Tahun);
        String Hujan4 = ", Apr "+HitungHariHujan("04",  Tahun);
        String Hujan5 = ", Mei "+HitungHariHujan("05",  Tahun);
        String Hujan6 = ", Jun "+HitungHariHujan("06",  Tahun);
        String Hujan7 = ", Jul "+HitungHariHujan("07",  Tahun);
        String Hujan8 = ", Agu "+HitungHariHujan("08",  Tahun);
        String Hujan9 = ", Sep "+HitungHariHujan("09",  Tahun);
        String Hujan10 = ", Okt "+HitungHariHujan("10",  Tahun);
        String Hujan11 = ", Nov "+HitungHariHujan("11",  Tahun);
        String Hujan12 = ", Des "+HitungHariHujan("12",  Tahun);

        String  Hujan =  Hujan1 +  Hujan2 + Hujan3 + Hujan4 + Hujan5 + Hujan6 + Hujan7 + Hujan8 + Hujan9 + Hujan10 + Hujan11 + Hujan12 ;

        int hh_jan = Math.round(HitungHariHujan("01",  Tahun));
        int ch_jan = Math.round(Januari);
        String DA_JANUARI = defisitAir(" Januari " , hh_jan, ch_jan );

        int hh_feb = Math.round(HitungHariHujan("02",  Tahun));
        int ch_feb = Math.round(Februari);
        String DA_FEBRUARI = defisitAir(" Februari " , hh_feb, ch_feb );


        int hh_mar = Math.round(HitungHariHujan("03",  Tahun));
        int ch_mar = Math.round(Maret);
        String DA_MARET = defisitAir(" Maret " , hh_mar, ch_mar );

        int hh_apr = Math.round(HitungHariHujan("04",  Tahun));
        int ch_apr = Math.round(Maret);
        String DA_APRIL = defisitAir(" April " , hh_apr, ch_apr );


        int hh_mei = Math.round(HitungHariHujan("05",  Tahun));
        int ch_mei = Math.round(Mei);
        String DA_MEI = defisitAir(" Mei " , hh_mei, ch_mei );


        int hh_jun = Math.round(HitungHariHujan("06",  Tahun));
        int ch_jun = Math.round(Juni);
        String DA_JUNI = defisitAir(" Juni " , hh_jun, ch_jun );

        int hh_jul = Math.round(HitungHariHujan("07",  Tahun));
        int ch_jul = Math.round(Juli);
        String DA_JULI = defisitAir(" Juli " , hh_jul, ch_jul );

        int hh_agu = Math.round(HitungHariHujan("08",  Tahun));
        int ch_agu = Math.round(Agustus);
        String DA_AGUSTUS = defisitAir(" Agustus " , hh_agu, ch_agu );


        int hh_sep = Math.round(HitungHariHujan("09",  Tahun));
        int ch_sep = Math.round(Agustus);
        String DA_SEPTEMBER = defisitAir(" September " , hh_sep, ch_sep );


        int hh_okt = Math.round(HitungHariHujan("10",  Tahun));
        int ch_okt = Math.round(Oktober);
        String DA_OKTOBER = defisitAir(" Oktober " , hh_okt, ch_okt );


        int hh_nov = Math.round(HitungHariHujan("11",  Tahun));
        int ch_nov = Math.round(November);
        String DA_NOVEMBER = defisitAir(" November " , hh_nov, ch_nov );


        int hh_des = Math.round(HitungHariHujan("12",  Tahun));
        int ch_des = Math.round(Desember);
        String DA_DESEMBER = defisitAir(" Desember " , hh_des, ch_des );
        String  DADISPLAY  = DA_JANUARI + DA_FEBRUARI + DA_MARET + DA_APRIL + DA_MEI + DA_JUNI + DA_JULI+ DA_AGUSTUS +DA_SEPTEMBER + DA_OKTOBER + DA_NOVEMBER +DA_DESEMBER;






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


        if (Januari < 60) {
            bk +=1;

            colorJanuari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Jan, ";



        } else {
            bb +=1;


            colorJanuari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Jan, ";

        }


        if (Februari < 60) {

            bk +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.abu);

            BulanKering += "Feb, ";

        } else {
            bb +=1;


            colorFebruari = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Feb, ";

        }


        if (Maret < 60) {
            bk +=1;

            BulanKering += "Mar, ";

            colorMaret = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            colorMaret = ContextCompat.getColor(getActivity(), R.color.birumuda);

            BulanBasah += "Mar, ";
        }


        if (April < 60) {
            bk +=1;


            BulanKering += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;


            BulanBasah += "Apr, ";

            colorApril = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Mei < 60) {
            bk +=1;


            BulanKering += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Mei, ";

            colorMei = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juni < 60) {
            bk +=1;


            BulanKering += "Jun, ";

            colorJuni = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Jun, ";


            colorJuni = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Juli < 60) {
            bk +=1;

            BulanKering += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;



            BulanBasah += "Jul, ";


            colorJuli = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Agustus < 60) {
            bk +=1;

            BulanKering += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;

            BulanBasah += "Agu, ";


            colorAgustus = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (September < 60) {
            bk +=1;

            BulanKering += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Sept, ";


            colorSeptember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Oktober < 60) {
            bk +=1;


            BulanKering += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {
            bb +=1;

            BulanBasah += "Okt, ";


            colorOktober = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }

        if (November < 60) {
            bk +=1;


            BulanKering += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Nov, ";


            colorNovember = ContextCompat.getColor(getActivity(), R.color.birumuda);

        }


        if (Desember < 60) {
            bk +=1;


            BulanKering += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.abu);

        } else {

            bb +=1;


            BulanBasah += "Des ";


            colorDesember = ContextCompat.getColor(getActivity(), R.color.birumuda);

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


        BarDataSet dataset = new BarDataSet(entries, "Curah Hujan dalam mm ");

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


        txtBulanBasah.setText(BulanBasah);
        txtBulanKering.setText(BulanKering);
        tvBulanBasah.setText("(BB) Bulan Basah = "+bb);
        tvBulanKering.setText("(BK) Bulan Kering = "+bk);

        txtInfoHK.setText(TidakHujan);
        txtInfoHH.setText(Hujan);

        if(DADISPLAY != null && !DADISPLAY.isEmpty()) {
            /* do your stuffs here */
            txtDefisitAir.setText(DADISPLAY);

        }


    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(c.getTime());

    }


    public class MyBarDataSet extends BarDataSet {


        public MyBarDataSet(List<BarEntry> yVals, String label) {
            super(yVals, label);
        }

        @Override
        public int getColor(int index) {
            if (getEntryForXIndex(index).getVal() < 95) // less than 95 green
                return mColors.get(0);
            else if (getEntryForXIndex(index).getVal() < 100) // less than 100 orange
                return mColors.get(1);
            else // greater or equal than 100 red
                return mColors.get(2);
        }

    }



    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
           // String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
            String mPath =  "HasilCetak.jpg";

            // create bitmap screen capture
            View v1 = getActivity().getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            System.out.println("FILE :"+imageFile+" HAS BEEN CREATED");

            //openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }


    public void CobaInternal(View view){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/req_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "SImage-" + n + ".jpg";
        File file = new File(myDir, fname);
        Log.i("LOG TAG INTER", "" + file);
        if (file.exists())
            file.delete();
        try {

            BarChart v1 = (BarChart) view.findViewById(R.id.chart);;
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
            Toast.makeText(getActivity(), "Data Berhasil Dieksport Silahkan Cek CurahHujan.csv di SDCARD anda " , Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Boolean doInBackground(String... params) {

            exportDB(tahunDB);

            return null;
        }
    }



    class TaskDry extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            layout.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            layout.setVisibility(View.GONE);
            super.onPostExecute(result);
          //  Toast.makeText(getActivity(), "Data Berhasil Dieksport Silahkan Cek CurahHujan.csv di SDCARD anda " , Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Boolean doInBackground(String... params) {

            DrySpellExcecute();


            return null;
        }
    }



    private void exportDB(String Tahun) {

        File dbFile = getActivity().getDatabasePath( "datasawit.db");
        DataHelper dbhelper = new DataHelper(getActivity());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "CurahHujan.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            String header[] = {"ID", "BULAN", "TAHUN", "JUMLAH"};
            csvWrite.writeNext(header);

            float Januari = Hitung("01",Tahun);
            float Februari = Hitung("02",Tahun);
            float Maret = Hitung("03",Tahun);
            float April = Hitung("04",Tahun);
            float Mei = Hitung("05",Tahun);
            float Juni = Hitung("06",Tahun);
            float Juli = Hitung("07",Tahun);
            float Agustus = Hitung("08",Tahun);
            float September = Hitung("09",Tahun);
            float Oktober = Hitung("10",Tahun);
            float November = Hitung("11",Tahun);
            float Desember = Hitung("12",Tahun);


            String Jan[] = {"01", "JANUARI",  Tahun, ""+Januari};
            String Feb[] = {"02", "FEBRURARI",  Tahun, ""+Februari};
            String Mar[] = {"03", "MARET",  Tahun, ""+Maret};
            String Apr[] = {"04", "APRIL",  Tahun, ""+April};
            String May[] = {"05", "MEI",  Tahun, ""+Mei};
            String Jun[] = {"06", "JUNI",  Tahun, ""+Juni};
            String Jul[] = {"07", "JULI",  Tahun, ""+Juli};
            String Agu[] = {"08", "AGUSTUS",  Tahun, ""+Agustus};
            String Sep[] = {"09", "SEPTEMBER",  Tahun, ""+September};
            String Okt[] = {"10", "OKTOBER",  Tahun, ""+Oktober};
            String Nov[] = {"11", "NOVEMBER",  Tahun, ""+November};
            String Des[] = {"12", "DESEMBER",  Tahun, ""+Desember};


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



//    ####

    class TaskCreate extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            layout.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            layout.setVisibility(View.GONE);
            super.onPostExecute(result);
           // Toast.makeText(getActivity(), "Data Berhasil Dieksport Silahkan Cek CurahHujan.csv di SDCARD anda " , Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            settingDatabase();
          //  DrySpellExcecute();

            return null;
        }
    }


public String defisitAir(String bulan , int hh, int ch){
    String strDefisit = "";

    int faktor_pengurang = 150;
    if(hh<11){

        faktor_pengurang = 120;

    }

    int nilai_defisit = (200 + ch) - faktor_pengurang;


    if(nilai_defisit<0){

        strDefisit = "DA "+bulan+" "+nilai_defisit;


    }






    return strDefisit;
}



//    ####


//    private void showDateDialog(){
//
//        /**
//         * Calendar untuk mendapatkan tanggal sekarang
//         */
//        Calendar newCalendar = Calendar.getInstance();
//
//        /**
//         * Initiate DatePicker dialog
//         */
//        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                /**
//                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
//                 */
//
//                /**
//                 * Set Calendar untuk menampung tanggal yang dipilih
//                 */
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//
//                /**
//                 * Update TextView dengan tanggal yang kita pilih
//                 */
//                btnTanggal.setText(dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//        /**
//         * Tampilkan DatePicker dialog
//         */
//        datePickerDialog.show();
//    }
}


