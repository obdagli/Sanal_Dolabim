package tr.edu.yildiz.omerburakdagli;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.adapter.kabin_turleri.KabinKiyafetSecim;
import tr.edu.yildiz.omerburakdagli.adapter.kombin.Kombinlerim;
import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

import static java.lang.Integer.parseInt;

public class KabinOdasi extends AppCompatActivity {
    Button pchoice1,pchoice2,pchoice3,pchoice4,pchoice5,bt_kaydet,bt_kombingoster,bt_geridon;
    Bitmap bitmap;
    String tur,renk,desen,alinmatarihi,fiyat,kombin_adi_eski;
    EditText et_kombinadi;
    public static ImageView photo1,photo2,photo3,photo4,photo5;
    byte[] f;
    Integer cekmece_id;
    private veritabani v1;
    public static ArrayList<kiyafet> Kiyafet_test2 = new ArrayList<>();
    public static ArrayList<String> kombinisimler = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kabin_odasi);
        callInputs();
        v1 = new veritabani(this);
        kiyafet.kombinler.clear();
        kombinisimler.clear();
        kiyafet.Kiyafet_test.clear();
        showdata(readdata());
        showdata2(readdata2());

        pchoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataset("1");
                switchRoom("1");
            }
        });
        pchoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataset("2");
                switchRoom("2");
            }
        });
        pchoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataset("3");switchRoom("3");
            }
        });
        pchoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataset("4");switchRoom("4");
            }
        });
        pchoice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataset("5");
                switchRoom("5");
            }
        });
        bt_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlEkle();
                kombinisimler.clear();
                showdata(readdata());
                Toast.makeText(getApplicationContext(),"Kaydedildi", Toast.LENGTH_SHORT).show();
            }
        });
        bt_kombingoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KabinOdasi.this, Kombinlerim.class);
                intent.putExtra("kombin_isim",et_kombinadi.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KabinOdasi.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void sqlEkle(){
        SQLiteDatabase db = v1.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("kombin_adi",et_kombinadi.getText().toString());
        data.put("foto1",BytetoArray(kiyafet.kombin.get("1").getFoto()));
        data.put("foto2",BytetoArray(kiyafet.kombin.get("2").getFoto()));
        data.put("foto3",BytetoArray(kiyafet.kombin.get("3").getFoto()));
        data.put("foto4",BytetoArray(kiyafet.kombin.get("4").getFoto()));
        data.put("foto5",BytetoArray(kiyafet.kombin.get("5").getFoto()));
        db.insertOrThrow("Kombin",null,data);
    }
    public byte[] BytetoArray(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
    public void callInputs(){
        bt_geridon = findViewById(R.id.bt_geridon_kabin);
        pchoice1 = findViewById(R.id.bt_pchoice);
        pchoice2 = findViewById(R.id.bt_pchoice2);
        pchoice3 = findViewById(R.id.bt_pchoice3);
        pchoice4 = findViewById(R.id.bt_pchoice4);
        pchoice5 = findViewById(R.id.bt_pchoice5);
        photo1 = findViewById(R.id.photo1);
        photo2 = findViewById(R.id.photo2);
        photo3 = findViewById(R.id.photo3);
        photo4 = findViewById(R.id.photo4);
        photo5 = findViewById(R.id.photo5);
        bt_kaydet = findViewById(R.id.bt_kombinkaydet);
        bt_kombingoster = findViewById(R.id.bt_gecmiskombin);
        et_kombinadi = findViewById(R.id.et_kombinadi);
    }
    private String[] columns={"kombin_adi","foto1","foto2","foto3","foto4","foto5"};
    private Cursor readdata(){
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor read = db.query("Kombin",columns,null,null,null,null,null);
        return read;
    }
    private void showdata(Cursor show){
        while( show.moveToNext()) {
            ArrayList<Bitmap> kombin_resimler = new ArrayList<>();
            f = show.getBlob(show.getColumnIndex("foto1"));
            bitmap = BitmapFactory.decodeByteArray(f,0,f.length);
            kombin_resimler.add(bitmap);
            f = show.getBlob(show.getColumnIndex("foto2"));
            bitmap = BitmapFactory.decodeByteArray(f,0,f.length);
            kombin_resimler.add(bitmap);
            f = show.getBlob(show.getColumnIndex("foto3"));
            bitmap = BitmapFactory.decodeByteArray(f,0,f.length);
            kombin_resimler.add(bitmap);
            f = show.getBlob(show.getColumnIndex("foto4"));
            bitmap = BitmapFactory.decodeByteArray(f,0,f.length);
            kombin_resimler.add(bitmap);
            f = show.getBlob(show.getColumnIndex("foto5"));
            bitmap = BitmapFactory.decodeByteArray(f,0,f.length);
            kombin_resimler.add(bitmap);
            kombinisimler.add(show.getString(show.getColumnIndex("kombin_adi")));
            kiyafet.kombinler.put(show.getString(show.getColumnIndex("kombin_adi")),kombin_resimler);

        }
    }
    public void dataset(String kontrol){
        Kiyafet_test2.clear();
        kiyafet.Kiyafet_test.forEach((n) -> {
            if(n.getTur().equals(kontrol)){
                Kiyafet_test2.add(n);
            }
        });
    }
    private String[] columns2={"tur","renk","desen","alinmatarihi","fiyat","foto","cekmece_id"};
    private Cursor readdata2(){
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor read = db.query("Kiyafet",columns2,null,null,null,null,null);
        return read;
    }
    private void showdata2(Cursor show){
        while( show.moveToNext()) {
            tur = show.getString(show.getColumnIndex("tur"));
            renk = show.getString(show.getColumnIndex("renk"));
            desen = show.getString(show.getColumnIndex("desen"));
            alinmatarihi = show.getString(show.getColumnIndex("alinmatarihi"));
            fiyat = show.getString(show.getColumnIndex("fiyat"));
            cekmece_id = show.getInt(show.getColumnIndex("cekmece_id"));
            f = show.getBlob(show.getColumnIndex("foto"));
            bitmap = BitmapFactory.decodeByteArray(f,0,f.length);
            kiyafet q = new kiyafet(tur,renk,desen,alinmatarihi,fiyat,bitmap,cekmece_id);
            kiyafet.Kiyafet_test.add(q);
        }
    }
    public void switchRoom(String tur){
        Intent intent = new Intent(KabinOdasi.this, KabinKiyafetSecim.class);
        intent.putExtra("tur",tur);
        startActivityForResult(intent, parseInt(tur));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            String result=data.getStringExtra("result");
            if(result.equals("1")){
                photo1.setImageBitmap(kiyafet.kombin.get("1").getFoto());
            }else if(result.equals("2")){
                photo2.setImageBitmap(kiyafet.kombin.get("2").getFoto());
            }else if(result.equals("3")){
                photo3.setImageBitmap(kiyafet.kombin.get("3").getFoto());
            }else if(result.equals("4")){
                photo4.setImageBitmap(kiyafet.kombin.get("4").getFoto());
            }else{
                photo5.setImageBitmap(kiyafet.kombin.get("5").getFoto());
            }
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            // Write your code if there's no result
        }
    }
}
