package tr.edu.yildiz.omerburakdagli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterCekmecelerim;
import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterEtkinliklerim;
import tr.edu.yildiz.omerburakdagli.classes.etkinlik;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class Etkinliklerim extends AppCompatActivity {
    private RecyclerView Recycler;
    private RecycleAdapterEtkinliklerim adapter;
    Button bt_geridon,bt_detay;
    private veritabani v1;
    String isim,tarih,lokasyon,tur,etkinlikno,kombin_adi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinliklerim);
        v1 = new veritabani(this);
        getInput();
        adapter.notifyDataSetChanged();
        etkinlik.etkinlikler.clear();
        showdata(readdata());
        bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etkinliklerim.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getInput(){
        Recycler = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new RecycleAdapterEtkinliklerim(Etkinliklerim.this,1);
        Recycler.setAdapter(adapter);
        Recycler.setLayoutManager(new LinearLayoutManager(this));
        bt_geridon = findViewById(R.id.bt_geridonetkinliklerim);
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
            etkinlik.etkinlikler.add(Etkinlik);

        }
    }
}