package tr.edu.yildiz.omerburakdagli.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class veritabani extends SQLiteOpenHelper {

    private static final String androidprogramming = "mobileapp_sanaldolap.sqlite3";
    private static final int SURUM = 1;
    public veritabani(Context c){
        super(c, androidprogramming,null,SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Cekmece (cekmece_id INTEGER PRIMARY KEY AUTOINCREMENT, ad Text);");
        db.execSQL("CREATE TABLE Kiyafet (kiyafet_id INTEGER PRIMARY KEY AUTOINCREMENT,tur Text, renk Text, desen Text, alinmatarihi Text, fiyat Text, foto Blob, cekmece_id Integer," +
                "FOREIGN KEY(cekmece_id) REFERENCES Cekmece(cekmece_id));");
        db.execSQL("CREATE TABLE Kombin (kombin_id INTEGER PRIMARY KEY AUTOINCREMENT,kombin_adi Text,foto1 Blob,foto2 Blob,foto3 Blob,foto4 Blob,foto5 Blob);");
        db.execSQL("CREATE TABLE Etkinlik (etkinlik_id INTEGER PRIMARY KEY AUTOINCREMENT,etkinlik_no Text,ad Text,tur Text,tarih Text,lokasyon Text,kombin_adi Text," +
                "FOREIGN KEY(kombin_adi) REFERENCES Kombin(kombin_adi));");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cekmece");
        db.execSQL("DROP TABLE IF EXISTS Kiyafet");
        db.execSQL("DROP TABLE IF EXISTS Kombin");
        db.execSQL("DROP TABLE IF EXISTS Etkinlik");
        onCreate(db);
    }
}
