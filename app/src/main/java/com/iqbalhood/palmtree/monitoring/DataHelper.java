package com.iqbalhood.palmtree.monitoring;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "datasawit.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
//        super(context, Environment.getExternalStorageDirectory()
//                + File.separator+ DATABASE_NAME, null, DATABASE_VERSION);

//        super(context, "/sdcard/"+DATABASE_NAME, null, DATABASE_VERSION);
//        SQLiteDatabase.openOrCreateDatabase("/sdcard/"+DATABASE_NAME,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table biodata(no integer primary key, nama text null, tgl text null, jk text null, alamat text null);";
        String sql_profile = "create table profile(id integer primary key, fullname text null, handphone text null, email text null, perusahaan text null);";
        String sql_kebun = "create table kebun(id integer primary key autoincrement, nama text null, luas text null,  tahun text null);";
        String sql_curah_hujan = "create table curah_hujan(id text primary key, dateformat DATETIME null, jumlah int null,  tanggal text null,  bulan text null,  tahun text null, kebun text null);";
        Log.d("Data", "onCreate: " + sql);
        Log.d("Data", "onCreate: " + sql_profile);
        Log.d("Data", "onCreate: " + sql_kebun);
        Log.d("Data", "onCreate: " + sql_curah_hujan);
        db.execSQL(sql);
        db.execSQL(sql_profile);
        db.execSQL(sql_kebun);
        db.execSQL(sql_curah_hujan);
        sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('1', 'Darsiwan', '1996-07-12', 'Laki-laki','Indramayu');";
        String sql_insert_profile = "INSERT INTO profile (id, fullname, handphone, email, perusahaan) VALUES ('1', 'Silahkan Masukkan Nama', 'Silahkan Masukkan Nomor Handphone', 'Silahkan Masukkan Email','Silahkan Masukkan Nama Perusahaan');";
        db.execSQL(sql);
        db.execSQL(sql_insert_profile);

       // printDate(db);

    }

    public void printDate(SQLiteDatabase db , int month, String kebun){
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MONTH, 1);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(2017, month, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat id = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dm = new SimpleDateFormat("MM");
        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
        SimpleDateFormat dd = new SimpleDateFormat("dd");
        System.out.print(df.format(cal.getTime()));
        for (int i = 1; i <= maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i );
            System.out.println("###################");
            System.out.println("" + df.format(cal.getTime()));
            System.out.println("ID>>" + id.format(cal.getTime()));
            System.out.println("BULAN >> "+ dm.format(cal.getTime()));
            System.out.println("TAHUN >> "+ dy.format(cal.getTime()));
            System.out.println("HARI >> "+ dd.format(cal.getTime()));

            String dateformat = "" + df.format(cal.getTime());
            String ID = "" + id.format(cal.getTime());
            String BULAN = "" + dm.format(cal.getTime());
            String TAHUN = ""+ dy.format(cal.getTime());
            String HARI = "" + dd.format(cal.getTime());

            String query = "INSERT INTO curah_hujan (id, dateformat, jumlah, tanggal, bulan, tahun, kebun) VALUES ('"+kebun+ID+"', '"+dateformat+"', '0', '"+HARI+"', '"+BULAN+"', '"+TAHUN+"','"+kebun+"');";

            db.execSQL(query);
            System.out.println("###################");
        }
    }


    public void printDateNeo(SQLiteDatabase db , int month, String kebun, int tahun){
        Calendar cal = Calendar.getInstance();
        cal.set(tahun, month, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat id = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dm = new SimpleDateFormat("MM");
        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
        SimpleDateFormat dd = new SimpleDateFormat("dd");
        System.out.print(df.format(cal.getTime()));
        for (int i = 1; i <= maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i );
            System.out.println("###################");
            System.out.println("" + df.format(cal.getTime()));
            System.out.println("ID>>" + id.format(cal.getTime()));
            System.out.println("BULAN >> "+ dm.format(cal.getTime()));
            System.out.println("TAHUN >> "+ dy.format(cal.getTime()));
            System.out.println("HARI >> "+ dd.format(cal.getTime()));

            String dateformat = "" + df.format(cal.getTime());
            String ID = "" + id.format(cal.getTime());
            String BULAN = "" + dm.format(cal.getTime());
            String TAHUN = ""+ dy.format(cal.getTime());
            String HARI = "" + dd.format(cal.getTime());

            String query = "INSERT INTO curah_hujan (id, dateformat, jumlah, tanggal, bulan, tahun, kebun) VALUES ('"+kebun+ID+"', '"+dateformat+"', '0', '"+HARI+"', '"+BULAN+"', '"+TAHUN+"','"+kebun+"');";

            db.execSQL(query);
            System.out.println("###################");
        }
    }



    public void updateDateNeo(SQLiteDatabase db , int month, String kebun, int tahun){
        Calendar cal = Calendar.getInstance();
        cal.set(tahun, month, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat id = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dm = new SimpleDateFormat("MM");
        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
        SimpleDateFormat dd = new SimpleDateFormat("dd");
        System.out.print(df.format(cal.getTime()));
        for (int i = 1; i <= maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i );
            System.out.println("###################");
            System.out.println("" + df.format(cal.getTime()));
            System.out.println("ID>>" + id.format(cal.getTime()));
            System.out.println("BULAN >> "+ dm.format(cal.getTime()));
            System.out.println("TAHUN >> "+ dy.format(cal.getTime()));
            System.out.println("HARI >> "+ dd.format(cal.getTime()));

            String dateformat = "" + df.format(cal.getTime());
            String ID = "" + id.format(cal.getTime());
            String BULAN = "" + dm.format(cal.getTime());
            String TAHUN = ""+ dy.format(cal.getTime());
            String HARI = "" + dd.format(cal.getTime());

            String query = "UPDATE curah_hujan  set  jumlah = '0' WHERE id = '"+kebun+ID+"' AND dateformat= '"+dateformat+"');";

            db.execSQL(query);
            System.out.println("###################");
        }
    }

    public String drySpell( SQLiteDatabase db,  String kebun, int tahun){

        Calendar cal = Calendar.getInstance();

        int drycount = 0;
        int countdry = 0;

        for (int j = 0; j < 12; j++) {

            cal.set(tahun, j, 1);

            int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat id = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat dm = new SimpleDateFormat("MM");
            SimpleDateFormat dy = new SimpleDateFormat("yyyy");
            SimpleDateFormat dd = new SimpleDateFormat("dd");
            //System.out.print(df.format(cal.getTime()));

            for (int i = 1; i <= maxDay; i++) {
                cal.set(Calendar.DAY_OF_MONTH, i );
//                System.out.println("###################");
//                System.out.println("" + df.format(cal.getTime()));
//                System.out.println("ID>>" + id.format(cal.getTime()));
//                System.out.println("BULAN >> "+ dm.format(cal.getTime()));
//                System.out.println("TAHUN >> "+ dy.format(cal.getTime()));
//                System.out.println("HARI >> "+ dd.format(cal.getTime()));

                String ID = "" + id.format(cal.getTime());
                String value = hitungValue ( db, kebun+ID);





                if(value.contains("0")){
                   // System.out.println(" DRY QUERY  ZERO " + value);
                    countdry ++;

                }else {

                    //System.out.println(" DRY QUERY  ZERX " + value);

                    countdry = 0;


                }

                if(countdry == 20){

                    drycount ++;
                    countdry = 0;
                }

             //   String query = "INSERT INTO curah_hujan (id, dateformat, jumlah, tanggal, bulan, tahun, kebun) VALUES ('"+kebun+ID+"', '"+dateformat+"', '0', '"+HARI+"', '"+BULAN+"', '"+TAHUN+"','"+kebun+"');";





            }




        }

        return  "" +drycount;

    }


    public String hitungValue (SQLiteDatabase db , String id ){
        String strValue = "";

        String QPerhitungan = "SELECT\n" +
                " jumlah\n" +
                "FROM\n" +
                " curah_hujan\n" +
                "WHERE\n" +

                " id = '"+id+"';";



        SQLiteStatement ss = db.compileStatement(QPerhitungan);
        long counts = ss.simpleQueryForLong();

        strValue = counts+"";


        return strValue;
    }


    public void createTablePohon(SQLiteDatabase db){



        String sql = "create table jumlah_pohon(id integer primary key autoincrement, bulan text null, jumlah text null,  tahun text null, kebun text null);";
        db.execSQL(sql);

    }

    public void emptyTablePohon(SQLiteDatabase db){



        String sql = "DROP table jumlah_pohon ; ";
        db.execSQL(sql);

    }


    public void createTableElaeidobius(SQLiteDatabase db){



        String sql = "create table elaeidobius(id integer primary key autoincrement, bulan text null, jumlah text null,  tahun text null, kebun text null);";
        db.execSQL(sql);

    }

    public void emptyTableElaeidobius(SQLiteDatabase db){



        String sql = "DROP table elaeidobius ;";
        db.execSQL(sql);

    }


    public void createTableGanoderma(SQLiteDatabase db){

        String sql = "create table ganoderma(id integer primary key autoincrement, bulan1 text null, bulan2 text null, jumlah text null,  tahun text null, kebun text null);";
        db.execSQL(sql);

    }

    public void emptyTableGanoderma(SQLiteDatabase db){

        String sql = "DROP table ganoderma ;";
        db.execSQL(sql);

    }


    public void createTableOryctes(SQLiteDatabase db){

        String sql = "create table oryctes(id integer primary key autoincrement, dateformat text null , tanggal1 text null, bulan1 text null,  tanggal2 text null, bulan2 text null, jumlah text null,  tahun text null, kebun text null);";

        db.execSQL(sql);

    }

    public void emptyTableOryctes(SQLiteDatabase db){

        String sql = "DROP table oryctes ;";
        db.execSQL(sql);

    }


    public void createTableUlatAPI(SQLiteDatabase db){

        String sql = "create table ulat_api(id integer primary key autoincrement, dateformat text null , tanggal1 text null, bulan1 text null,  tanggal2 text null, bulan2 text null, jumlah text null,  tahun text null, kebun text null);";
        db.execSQL(sql);

    }

    public void emptyTableUlatAPI(SQLiteDatabase db){

        String sql = "DROP table ulat_api ;";
        db.execSQL(sql);

    }





    public void createTableUlatKantung(SQLiteDatabase db){

        String sql = "create table ulat_kantung(id integer primary key autoincrement, dateformat text null , tanggal1 text null, bulan1 text null,  tanggal2 text null, bulan2 text null, jumlah text null,  tahun text null, kebun text null);";
        db.execSQL(sql);

    }

    public void emptyUlatKantung(SQLiteDatabase db){

        String sql = "DROP table ulat_kantung ;";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }





}