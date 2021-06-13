package tr.edu.yildiz.omerburakdagli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.etkinlik;
import tr.edu.yildiz.omerburakdagli.classes.kabin;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class MainActivity extends AppCompatActivity {
    Button bt_cekmeceolustur,bt_cekmecelerim,bt_kiyafetlerim,bt_kabin,bt_etkinlik,bt_etkinliklerim;
    private veritabani v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callInputs();
        v1 = new veritabani(this);
        /*SQLiteDatabase db = v1.getReadableDatabase();
        v1.onUpgrade(db,1,1);*/
        bt_cekmeceolustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = v1.getWritableDatabase();
                ContentValues data = new ContentValues();
                cekmece Cekmece = new cekmece((cekmece.Cekmece_test.size()+1)+"");
                data.put("ad",(cekmece.Cekmece_test.size()+1)+"");
                db.insertOrThrow("Cekmece",null,data);
                cekmece.Cekmece_test.add(Cekmece);
                Toast.makeText(getApplicationContext(),"Çekmece oluşturuldu!", Toast.LENGTH_SHORT).show();
            }
        });
        bt_cekmecelerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cekmecelerim.class);
                startActivity(intent);
                finish();
            }
        });
        bt_kiyafetlerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Kiyafetlerim.class);
                startActivity(intent);
                finish();
            }
        });
        bt_kabin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KabinOdasi.class);
                startActivity(intent);
                finish();
            }
        });
        bt_etkinlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EtkinlikEkle.class);
                startActivity(intent);
                finish();
            }
        });
        bt_etkinliklerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etkinlik.etkinlikler.clear();
                Intent intent = new Intent(MainActivity.this, Etkinliklerim.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void callInputs(){
        bt_cekmeceolustur = findViewById(R.id.bt_cekmeceolustur);
        bt_cekmecelerim = findViewById(R.id.bt_cekmecelerim);
        bt_kiyafetlerim = findViewById(R.id.bt_kiyafetlerim);
        bt_kabin = findViewById(R.id.bt_kabin);
        bt_etkinlik = findViewById(R.id.bt_etkinlikekle);
        bt_etkinliklerim = findViewById(R.id.bt_etkinliklerim);
    }
}