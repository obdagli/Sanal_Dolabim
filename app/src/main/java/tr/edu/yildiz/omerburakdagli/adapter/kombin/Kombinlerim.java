package tr.edu.yildiz.omerburakdagli.adapter.kombin;

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

import tr.edu.yildiz.omerburakdagli.Cekmecelerim;
import tr.edu.yildiz.omerburakdagli.KabinOdasi;
import tr.edu.yildiz.omerburakdagli.Kiyafetlerim;
import tr.edu.yildiz.omerburakdagli.MainActivity;
import tr.edu.yildiz.omerburakdagli.R;
import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterKiyafetlerim;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class Kombinlerim extends AppCompatActivity {
    Button bt_geridon;
    private RecyclerView Recycler;
    private RecycleAdapterKombinlerim adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kombinlerim);
        getInput();
        adapter.notifyDataSetChanged();

        bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kombinlerim.this, KabinOdasi.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getInput(){
        Recycler = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new RecycleAdapterKombinlerim(Kombinlerim.this,0);
        Recycler.setAdapter(adapter);
        Recycler.setLayoutManager(new LinearLayoutManager(this));
        bt_geridon = findViewById(R.id.bt_geridonmain);
    }
}