package tr.edu.yildiz.omerburakdagli;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class KiyafetEkle extends AppCompatActivity {
    Spinner sp_tur,sp_renk,sp_desen;
    EditText et_alinmatarihi, et_fiyat;
    String tur,renk,desen,alinmatarihi,fiyat;
    int request_imageopen=0,pos;
    Bitmap bitmap;
    ImageView userphoto;
    Button bt_pchoice,bt_kaydet;
    private veritabani v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiyafet_ekle);
        callInputs();
        SpinnerSettings();
        Intent intent = getIntent();
        pos = intent.getIntExtra("position",0);
        v1 = new veritabani(this);
        bt_pchoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, request_imageopen);
            }
        });
        bt_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpinner();
                SQLiteDatabase db = v1.getWritableDatabase();
                ContentValues data = new ContentValues();
                byte[] photo = BytetoArray();
                data.put("tur",tur);
                data.put("renk",renk);
                data.put("desen",desen);
                data.put("alinmatarihi",alinmatarihi);
                data.put("fiyat",fiyat);
                data.put("foto",photo);
                data.put("cekmece_id",pos+1);
                db.insertOrThrow("Kiyafet",null,data);
                Toast.makeText(getApplicationContext(),"Kıyafet eklendi!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void callInputs(){
        sp_tur = findViewById(R.id.sp_tur);
        sp_renk = findViewById(R.id.sp_renk);
        sp_desen = findViewById(R.id.sp_desen);
        et_alinmatarihi = findViewById(R.id.et_alinmatarihi);
        et_fiyat = findViewById(R.id.et_fiyat);
        bt_pchoice = findViewById(R.id.bt_pchoice);
        userphoto = findViewById(R.id.u_photo);
        bt_kaydet = findViewById(R.id.bt_kaydet);
    }
    public void SpinnerSettings(){
        String[] tur = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter_tur = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,tur); sp_tur.setAdapter(adapter_tur);
        String[] renk = new String[]{"Kırmızı", "Mavi", "Yeşil", "Siyah", "Beyaz","Pembe","Mor","Sarı","Lacivert"};
        ArrayAdapter<String> adapter_renk = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,renk); sp_renk.setAdapter(adapter_renk);
        String[] desen = new String[]{"Kareli", "Düz", "Çizgili"};
        ArrayAdapter<String> adapter_desen = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,desen); sp_desen.setAdapter(adapter_desen);
    }
    public void getSpinner(){
        tur = sp_tur.getSelectedItem().toString();
        renk = sp_renk.getSelectedItem().toString();
        desen = sp_desen.getSelectedItem().toString();
        alinmatarihi = et_alinmatarihi.getText().toString();
        fiyat = et_fiyat.getText().toString();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == request_imageopen && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try{
                if(Build.VERSION.SDK_INT >= 28){
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),uri);
                    bitmap = ImageDecoder.decodeBitmap(source);
                    userphoto.setImageBitmap(bitmap);
                }else{
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    userphoto.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] BytetoArray(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
}