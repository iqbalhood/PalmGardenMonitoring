package com.iqbalhood.palmtree.monitoring.InputActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.iqbalhood.palmtree.monitoring.R;

public class InputBaseActivity extends AppCompatActivity {

    String id = "";
    String nama = "";
    String tahun ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_base);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            TextView satuanKebun = (TextView)findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama+" ("+tahun+")");


        }




    }


    public void curahHujan(View view){

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            Intent curah = new Intent(InputBaseActivity.this, InputCurahHujan.class);
            curah.putExtra("nama", nama);
            curah.putExtra("id", id);
            curah.putExtra("tahun", tahun);
            startActivity(curah);

        }


    }

    public void jumlahPohon(View view){
        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            Intent curah = new Intent(InputBaseActivity.this, InputJumlahPohon.class);
            curah.putExtra("nama", nama);
            curah.putExtra("id", id);
            curah.putExtra("tahun", tahun);
            startActivity(curah);

        }
    }

    public void ulatApi(View view){


        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            Intent curah = new Intent(InputBaseActivity.this, InputUlatApi.class);

            curah.putExtra("nama", nama);
            curah.putExtra("id", id);
            curah.putExtra("tahun", tahun);
            startActivity(curah);

        }
    }

    public void ulatKantung(View view){

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            Intent curah = new Intent(InputBaseActivity.this, InputUlatKantung.class);

            curah.putExtra("nama", nama);
            curah.putExtra("id", id);
            curah.putExtra("tahun", tahun);
            startActivity(curah);

        }

    }

    public void oryctes(View view){

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            Intent curah = new Intent(InputBaseActivity.this, InputOryctes.class);

            curah.putExtra("nama", nama);
            curah.putExtra("id", id);
            curah.putExtra("tahun", tahun);
            startActivity(curah);

        }

    }


    public void ganoderma(View view){

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            Intent curah = new Intent(InputBaseActivity.this, InputJumlahGanoderma.class);

            curah.putExtra("nama", nama);
            curah.putExtra("id", id);
            curah.putExtra("tahun", tahun);
            startActivity(curah);

        }

    }

    public void elaeidobius(View view){

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            Intent curah = new Intent(InputBaseActivity.this, InputJumlahElaeidobius.class);

            curah.putExtra("nama", nama);
            curah.putExtra("id", id);
            curah.putExtra("tahun", tahun);
            startActivity(curah);

        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
