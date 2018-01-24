package com.iqbalhood.palmtree.monitoring;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataKebun extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_kebun);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editTextNama);
        text2 = (EditText) findViewById(R.id.editTextLuas);
        text3 = (EditText) findViewById(R.id.editTextTahun);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT id, nama, luas, tahun FROM kebun WHERE id = '"+id+"'  ", null);
            if(c.moveToFirst()){
                do{
                    //assing values
                    String column1 = c.getString(0);
                    String column2 = c.getString(1);
                    String column3 = c.getString(2);
                    String column4 = c.getString(3);

                    text1.setText(column2);
                    text2.setText(column3);
                    text3.setText(column4);

                    //Do something Here with values

                }while(c.moveToNext());
            }
            c.close();
            db.close();



        }



        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
//                // TODO Auto-generated method stub

                String id = "";

                Bundle bbb = getIntent().getExtras();
                if (bbb != null) {

                    id = bbb.getString("id");
                }


             SQLiteDatabase db = dbHelper.getWritableDatabase();

                db.execSQL("UPDATE  kebun SET "
                        +" nama = '" + text1.getText().toString() + "', "
                        +" luas = '" + text2.getText().toString() + "', "
                        +" tahun = '"+ text3.getText().toString() + "' WHERE id ="+id+" ");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                DashboardActivity.ma.RefreshList();
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



}