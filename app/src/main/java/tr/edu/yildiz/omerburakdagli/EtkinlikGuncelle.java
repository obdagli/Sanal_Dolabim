package tr.edu.yildiz.omerburakdagli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tr.edu.yildiz.omerburakdagli.classes.etkinlik;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class EtkinlikGuncelle extends AppCompatActivity {
    EditText et_etkinlikisim,et_etkinliktarih,et_etkinliklokasyon,et_etkinliktur,et_etkinlikno;
    Button bt_olustur,bt_geridon;
    Integer pos;
    private veritabani v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik_guncelle);
        v1 = new veritabani(this);
        Intent intent = getIntent();
        pos = intent.getIntExtra("position",0);
        callInputs();
        getTextfun();
        et_etkinlikno.setEnabled(false);
        bt_olustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = v1.getWritableDatabase();
                ContentValues data = new ContentValues();
                //data.put("etkinlik_no",et_etkinlikno.getText().toString());
                data.put("ad",et_etkinlikisim.getText().toString());
                data.put("tur",et_etkinliktur.getText().toString());
                data.put("tarih",et_etkinliktarih.getText().toString());
                data.put("lokasyon",et_etkinliklokasyon.getText().toString());
                data.put("kombin_adi",etkinlik.etkinlikler.get(pos).getKombin_adi());
                db.update("Etkinlik",data,"etkinlik_no=?",new String[] {et_etkinlikno.getText().toString()});
                Toast.makeText(getApplicationContext(),"Etkinlik güncellendi!", Toast.LENGTH_SHORT).show();
            }
        });
        bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EtkinlikGuncelle.this, Etkinliklerim.class);
                startActivity(intent);
                finish();
            }
        });
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
        et_etkinlikisim.setText(etkinlik.etkinlikler.get(pos).getAd());
        et_etkinliktarih.setText(etkinlik.etkinlikler.get(pos).getTarih());
        et_etkinliklokasyon.setText(etkinlik.etkinlikler.get(pos).getLokasyon());
        et_etkinliktur.setText(etkinlik.etkinlikler.get(pos).getTur());
        et_etkinlikno.setText(etkinlik.etkinlikler.get(pos).getEtkinlik_no());
    }
}