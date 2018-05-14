package com.iqbalhood.palmtree.monitoring;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.iqbalhood.palmtree.monitoring.InputActivity.InputDataKebun;


public class DashboardActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener , View.OnClickListener {
    String[] daftar;
    String[] daftarID;
    String[] daftarTahun;


    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DashboardActivity ma;

    // spinner1 element
    Spinner spinner1;
    Spinner spinner2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_kebun);

        // spinner1 element
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        // spinner1 click listener
        spinner1.setOnItemSelectedListener(new ViewSpinnerClass());
        spinner2.setOnItemSelectedListener(new EditSpinnerClass());

        Button btn=(Button)findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(DashboardActivity.this, InputDataKebun.class);
                startActivity(inte);
            }
        });

       // loadspinner1Data();


        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();

        SQLiteDatabase db = dbcenter.getReadableDatabase();
        SQLiteStatement s = db.compileStatement( "select count(*) from kebun ; " );

        long count = s.simpleQueryForLong();

        System.out.println("Tambah Satuan Kebun "+count);

        btn.setText("Add Garden Unit ("+(20-count)+")");

        if((20-count)<=1){

            btn.setVisibility(View.GONE);

        }



    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kebun",null);
        daftar = new String[cursor.getCount()+1];
        daftarID = new String[cursor.getCount()+1];
        daftarTahun = new String[cursor.getCount()+1];

        daftar[0] = "Select Garden";
        daftarID[0] = "0";
        daftarTahun[0] = "0";

//        daftar[1] = "1";
//        daftarID[1] = "1";
//        daftarTahun[1] = "1";



        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){

            //System.out.println("WHAT CC"+cc);
            cursor.moveToPosition(cc);
            daftar[cc+1] = cursor.getString(1).toString();
            daftarID[cc+1] = cursor.getString(0).toString();
            daftarTahun[cc+1] = cursor.getString(3).toString();
        }



        // Creating adapter for spinner1
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, daftar);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner1

        spinner1.setAdapter(dataAdapter); spinner1.setPrompt("MAU");
        spinner2.setAdapter(dataAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {




    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View view) {
        if (view == spinner1) {

            // Showing selected spinner1 item
            Toast.makeText(this, "You selected: " + "Spinner One",
                    Toast.LENGTH_LONG).show();
        }
        if (view == spinner2) {

            // Showing selected spinner1 item
            Toast.makeText(this, "You selected: " + "Spinner Two",
                    Toast.LENGTH_LONG).show();
        }
    }


    class ViewSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
        {
            String label = daftarID[i];
            String nama = daftar[i];
            String tahun = daftarTahun[i];



            if(label.contains("0")){

                Toast.makeText(view.getContext(), "Select Garden",Toast.LENGTH_SHORT).show();


            }else{
                Toast.makeText(view.getContext(), "Loading ...",Toast.LENGTH_SHORT).show();


            Intent xxx = new Intent(DashboardActivity.this, HomeActivity.class);
            xxx.putExtra("nama", nama);
            xxx.putExtra("id", label);
            xxx.putExtra("tahun", tahun);
            startActivity(xxx);

            }



        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    class EditSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
        {


            String label = daftarID[i];

            if(label.contains("0")){

                Toast.makeText(view.getContext(), "Please Select The Garden",Toast.LENGTH_SHORT).show();


            }else{
                Toast.makeText(view.getContext(), "Loading ...",Toast.LENGTH_SHORT).show();

            Intent xxx = new Intent(DashboardActivity.this, EditDataKebun.class);
            xxx.putExtra("id", label);
            startActivity(xxx);

            }

//            String label = daftarID[i];
//            Toast.makeText(view.getContext(), "Spinner 2 Ditekan + "+label,Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}