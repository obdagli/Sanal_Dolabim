package tr.edu.yildiz.omerburakdagli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterCekmecelerim;
import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterKiyafetlerim;
import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class Kiyafetlerim extends AppCompatActivity {
    Button bt_geridon;
    private RecyclerView Recycler;
    private RecycleAdapterKiyafetlerim adapter;
    private veritabani v1;
    String tur,renk,desen,fiyat,alinmatarihi;
    Integer cekmece_id;
    byte[] f;
    Bitmap bitmap;
    private ArrayList<kiyafet> Kiyafetler = new ArrayList<kiyafet>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiyafetlerim);
        v1 = new veritabani(this);
        getInput();
        adapter.notifyDataSetChanged();
        showdata(readdata());
        bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kiyafetlerim.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getInput(){
        Recycler = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new RecycleAdapterKiyafetlerim(Kiyafetler,Kiyafetlerim.this);
        Recycler.setAdapter(adapter);
        Recycler.setLayoutManager(new LinearLayoutManager(this));
        bt_geridon = findViewById(R.id.bt_geridon_kiyafet);
    }
    private String[] columns={"tur","renk","desen","alinmatarihi","fiyat","foto","cekmece_id"};
    private Cursor readdata(){
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor read = db.query("Kiyafet",columns,null,null,null,null,null);
        return read;
    }
    private void showdata(Cursor show){
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
            Kiyafetler.add(q);
            kiyafet.Kiyafet_test.add(q);
        }
    }
}