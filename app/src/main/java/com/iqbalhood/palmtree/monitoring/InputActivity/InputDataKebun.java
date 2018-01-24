package com.iqbalhood.palmtree.monitoring.InputActivity;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iqbalhood.palmtree.monitoring.DashboardActivity;
import com.iqbalhood.palmtree.monitoring.DataHelper;
import com.iqbalhood.palmtree.monitoring.R;

public class InputDataKebun extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3;

    LinearLayout layout;

    String kebun ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_kebun);

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            TextView satuanKebun = (TextView)findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama+" ("+tahun+")");


        }

        layout = (LinearLayout)findViewById(R.id.progressbar_view);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editTextNama);
        text2 = (EditText) findViewById(R.id.editTextLuas);
        text3 = (EditText) findViewById(R.id.editTextTahun);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);


        long JumlahKebun = HitungJumlahKebun();

        final int _jumlahkebun = (int) JumlahKebun;

         kebun = String.valueOf(_jumlahkebun+1);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into kebun( id, nama, luas, tahun) values('" +
                        (_jumlahkebun+1) + "','" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                DashboardActivity.ma.RefreshList();
                db.close();

                settingDatabase(kebun);


                finish();
            }
        });






        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }



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
            settingDatabase(kebun);
            //  DrySpellExcecute();

            return null;
        }
    }


    public void settingDatabase(String kebun) {

        DataHelper dbHelper = new DataHelper(InputDataKebun.this);
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



    public long HitungJumlahKebun() {

        DataHelper dbHelper = new DataHelper(InputDataKebun.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement s = db.compileStatement("select count(*) from kebun  ; ");

        long count = s.simpleQueryForLong();

        s.close();

        return count;

    }




}