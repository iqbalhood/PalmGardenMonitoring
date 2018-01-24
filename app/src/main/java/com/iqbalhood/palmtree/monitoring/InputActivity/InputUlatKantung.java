package com.iqbalhood.palmtree.monitoring.InputActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.iqbalhood.palmtree.monitoring.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;

public class InputUlatKantung extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar, calendar2;
    private TextView dateView;
    private TextView dateView2;
    private int year, month, day, day2;

    int set = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_clania);

        Bundle bbb = getIntent().getExtras();
        if (bbb != null) {

            String id = bbb.getString("id");
            String nama = bbb.getString("nama");
            String tahun = bbb.getString("tahun");

            TextView satuanKebun = (TextView)findViewById(R.id.satuanKebun);
            satuanKebun.setText(nama+" ("+tahun+")");


        }


        dateView = (TextView) findViewById(R.id.textView3);
        dateView2 = (TextView) findViewById(R.id.textView33);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, 7);

        showDate(year, month+1, day);
        showDate2(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH)+1, calendar2.get(Calendar.DAY_OF_MONTH));

        setData();


    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @SuppressWarnings("deprecation")
    public void setDate2(View view) {
        showDialog(999);
        set = 2;
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
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
                    if(set == 2){
                        showDate2(arg1, arg2+1, arg3);
                    }else {
                        showDate(arg1, arg2 + 1, arg3);
                    }

                }
            };




    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showDate2(int year, int month, int day) {
        dateView2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    public void setData(){

        BarChart barChart = (BarChart) findViewById(R.id.chart);

        // HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);

        float Januari = 100;
        float Februari = 100;
        float Maret = 50;
        float April = 55;
        float Mei = 100;
        float Juni = 60;
        float Juli = 100;
        float Agustus = 40;
        float September = 70;
        float Oktober = 100;
        float November = 30;
        float Desember = 100;

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




        if(Januari < 60  ){

            colorJanuari = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorJanuari = ContextCompat.getColor(this, R.color.hijau);

        }


        if(Februari < 60  ){

            colorFebruari = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorFebruari = ContextCompat.getColor(this, R.color.hijau);

        }


        if(Maret < 60  ){

            colorMaret = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorMaret = ContextCompat.getColor(this, R.color.hijau);

        }


        if(April < 60  ){

            colorApril = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorApril = ContextCompat.getColor(this, R.color.hijau);

        }


        if(Mei < 60  ){

            colorMei = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorMei = ContextCompat.getColor(this, R.color.hijau);

        }


        if(Juni < 60  ){

            colorJuni = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorJuni = ContextCompat.getColor(this, R.color.hijau);

        }


        if(Juli < 60  ){

            colorJuli = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorJuli = ContextCompat.getColor(this, R.color.hijau);

        }


        if(Agustus < 60  ){

            colorAgustus = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorAgustus = ContextCompat.getColor(this, R.color.hijau);

        }

        if(September < 60  ){

            colorSeptember = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorSeptember = ContextCompat.getColor(this, R.color.hijau);

        }


        if(Oktober < 60  ){

            colorOktober = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorOktober = ContextCompat.getColor(this, R.color.hijau);

        }

        if(November < 60  ){

            colorNovember = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorNovember = ContextCompat.getColor(this, R.color.hijau);

        }


        if(Desember < 60  ){

            colorDesember = ContextCompat.getColor(this, R.color.merah);

        }else{

            colorDesember = ContextCompat.getColor(this, R.color.hijau);

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


        BarDataSet dataset = new BarDataSet(entries, "#  Jumlah Ulat Kantung per Pelepah ");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");
        labels.add("Range - Range");


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
}
