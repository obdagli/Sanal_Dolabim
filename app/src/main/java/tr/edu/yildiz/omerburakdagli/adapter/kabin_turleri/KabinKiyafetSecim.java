package tr.edu.yildiz.omerburakdagli.adapter.kabin_turleri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tr.edu.yildiz.omerburakdagli.Cekmecelerim;
import tr.edu.yildiz.omerburakdagli.KabinOdasi;
import tr.edu.yildiz.omerburakdagli.Kiyafetlerim;
import tr.edu.yildiz.omerburakdagli.MainActivity;
import tr.edu.yildiz.omerburakdagli.R;
import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterCekmecelerim;

public class KabinKiyafetSecim extends AppCompatActivity {
    private RecyclerView Recycler;
    private RecycleAdapterKabin adapter;
    Button bt_geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kabin_kiyafet_secim);
        getInput();
        adapter.notifyDataSetChanged();
        bt_geri = findViewById(R.id.bt_geri);
        bt_geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KabinKiyafetSecim.this, KabinOdasi.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getInput(){
        Recycler = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new RecycleAdapterKabin(KabinOdasi.Kiyafet_test2,KabinKiyafetSecim.this);
        Recycler.setAdapter(adapter);
        Recycler.setLayoutManager(new LinearLayoutManager(this));

    }
}