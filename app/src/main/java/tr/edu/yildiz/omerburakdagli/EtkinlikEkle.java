package tr.edu.yildiz.omerburakdagli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.adapter.kombin.Kombinlerim;
import tr.edu.yildiz.omerburakdagli.adapter.kombin.RecycleAdapterKombinlerim;
import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.etkinlik;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class EtkinlikEkle extends AppCompatActivity {
    EditText et_etkinlikisim,et_etkinliktarih,et_etkinliklokasyon,et_etkinliktur,et_etkinlikno;
    String isim,tarih,lokasyon,tur,etkinlikno,kombin_adi;
    Button bt_olustur,bt_geridon;
    private RecyclerView Recycler;
    private RecycleAdapterKombinlerim adapter;
    private veritabani v1;
    public static String etkinlik_kombinkayit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik_ekle);

        v1 = new veritabani(this);
        callInputs();
        getInput();
        etkinlik.etkinlikler.clear();
        showdata(readdata());
        adapter.notifyDataSetChanged();
        bt_olustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextfun();
                SQLiteDatabase db = v1.getWritableDatabase();
                ContentValues data = new ContentValues();
                data.put("etkinlik_no",etkinlikno);
                data.put("ad",isim);
                data.put("tur",tur);
                data.put("tarih",tarih);
                data.put("lokasyon",lokasyon);
                data.put("kombin_adi",etkinlik_kombinkayit);
                db.insertOrThrow("Etkinlik",null,data);
                etkinlik.etkinlikler.clear();
                showdata(readdata());
                Toast.makeText(getApplicationContext(),"Etkinlik oluşturuldu!", Toast.LENGTH_SHORT).show();
            }
        });
        bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EtkinlikEkle.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getInput(){
        Recycler = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new RecycleAdapterKombinlerim(EtkinlikEkle.this,1);
        Recycler.setAdapter(adapter);
        Recycler.setLayoutManager(new LinearLayoutManager(this));
    }
    public void callInputs(){
        et_etkinlikisim = findViewById(R.id.et_etkinlikisim);
        et_etkinliktarih = findViewById(R.id.et_etkinliktarih);
        et_etkinliklokasyon = findViewById(R.id.et_etkinliklokasyon);
        et_etkinliktur = findViewById(R.id.et_etkinliktur);
        bt_olustur = findViewById(R.id.bt_olustur);
        bt_geridon = findViewById(R.id.bt_etkinlikgeri);
        et_etkinlikno = findViewById(R.id.et_etkinlikno);
    }
    public void getTextfun(){
        isim = et_etkinlikisim.getText().toString();
        tarih = et_etkinliktarih.getText().toString();
        lokasyon = et_etkinliklokasyon.getText().toString();
        tur = et_etkinliktur.getText().toString();
        etkinlikno = et_etkinlikno.getText().toString();
    }
    private String[] columns={"etkinlik_no","ad","tur","tarih","lokasyon","kombin_adi"};
    private Cursor readdata(){
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor read = db.query("Etkinlik",columns,null,null,null,null,null);
        return read;
    }
    private void showdata(Cursor show){
        while( show.moveToNext()) {
            tur = show.getString(show.getColumnIndex("tur"));
            etkinlikno = show.getString(show.getColumnIndex("etkinlik_no"));
            isim = show.getString(show.getColumnIndex("ad"));
            tarih = show.getString(show.getColumnIndex("tarih"));
            lokasyon = show.getString(show.getColumnIndex("lokasyon"));
            kombin_adi = show.getString(show.getColumnIndex("kombin_adi"));
            etkinlik Etkinlik = new etkinlik(etkinlikno,isim,tur,tarih,lokasyon,kombin_adi);
            System.out.println("etkinlik_ad"+Etkinlik);
            etkinlik.etkinlikler.add(Etkinlik);

        }
    }
}