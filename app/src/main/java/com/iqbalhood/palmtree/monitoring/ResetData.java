package com.iqbalhood.palmtree.monitoring;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResetData extends AppCompatActivity {

    String kebun = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {
            String id = bbb.getString("kebun");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            kebun = id;

            TextView satuanKebun = (TextView)findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama+" ("+tahun+")");

        }




    }


    public void resetCurahHujan(View view){

        DataHelper dbHelper = new DataHelper(ResetData.this);
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

                dbHelper.updateDateNeo(db, i, kebun, 2017);

            }


            if (count2 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.updateDateNeo(db, i, kebun, 2018);

            }

            if (count3 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.updateDateNeo(db, i, kebun, 2019);

            }

            if (count4 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.updateDateNeo(db, i, kebun, 2020);

            }

            if (count5 > 0) {

                System.out.println("Data MONTH " + (i + 1) + " Already Created");


            } else {
                System.out.println("Data MONTH " + (i + 1) + " Not  Created");

                dbHelper.updateDateNeo(db, i, kebun, 2021);

            }






        }


    }



    public void resetJumlahPohon(View view){

        DataHelper dbHelper = new DataHelper(ResetData.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        dbHelper.emptyTablePohon(db);


    }


    public void resetElaeidobius(View view){
        DataHelper dbHelper = new DataHelper(ResetData.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        dbHelper.emptyTableElaeidobius(db);

    }

    public void resetGanoderma(View view){
        DataHelper dbHelper = new DataHelper(ResetData.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        dbHelper.emptyTableGanoderma(db);

    }

    public void resetOryctes(View view){
        DataHelper dbHelper = new DataHelper(ResetData.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        dbHelper.emptyTableOryctes(db);

    }


    public void resetUlatAPI(View view){

        DataHelper dbHelper = new DataHelper(ResetData.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        dbHelper.emptyTableUlatAPI(db);

    }


    public void resetUlatKantung(View view){

        DataHelper dbHelper = new DataHelper(ResetData.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        dbHelper.emptyUlatKantung(db);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
