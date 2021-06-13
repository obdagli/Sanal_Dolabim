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

import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterCekmecelerim;
import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class Cekmecelerim extends AppCompatActivity {
    Button bt_geridon;
    private RecyclerView Recycler;
    private RecycleAdapterCekmecelerim adapter;
    private veritabani v1;
    private ArrayList<cekmece> Cekmeceler = new ArrayList<cekmece>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekmecelerim);
        v1 = new veritabani(this);

        getInput();
        adapter.notifyDataSetChanged();
        showdata(readdata());
        bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cekmecelerim.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getInput(){
        Recycler = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new RecycleAdapterCekmecelerim(Cekmeceler,Cekmecelerim.this);
        Recycler.setAdapter(adapter);
        Recycler.setLayoutManager(new LinearLayoutManager(this));
        bt_geridon = findViewById(R.id.bt_geridon);
    }
    private String[] columns={"ad"};
    private Cursor readdata(){
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor read = db.query("Cekmece",columns,null,null,null,null,null);
        return read;
    }
    private void showdata(Cursor show){

        while( show.moveToNext()) {
                cekmece q = new cekmece(show.getString(show.getColumnIndex("ad")));
                Cekmeceler.add(q);
            }
        }
    }