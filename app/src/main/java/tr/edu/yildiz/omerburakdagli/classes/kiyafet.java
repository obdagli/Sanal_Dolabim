package tr.edu.yildiz.omerburakdagli.classes;

import android.graphics.Bitmap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class kiyafet {
    String tur;
    String renk;
    String desen;
    String alinmatarihi;
    String fiyat;
    Bitmap foto;
    Integer cekmece_id;
    String kombin_ad;

    public static ArrayList<kiyafet> Kiyafet_test = new ArrayList<>();
    public static HashMap<String,kiyafet> kombin = new HashMap<>();
    public static HashMap<String,ArrayList<Bitmap>> kombinler = new HashMap();

    public kiyafet(String tur, String renk, String desen, String alinmatarihi, String fiyat, Bitmap foto, Integer cekmece_id) {
        this.tur = tur;
        this.renk = renk;
        this.desen = desen;
        this.alinmatarihi = alinmatarihi;
        this.fiyat = fiyat;
        this.foto = foto;
        this.cekmece_id = cekmece_id;
    }

    public String getKombin_ad() {
        return kombin_ad;
    }

    public void setKombin_ad(String kombin_ad) {
        this.kombin_ad = kombin_ad;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getDesen() {
        return desen;
    }

    public void setDesen(String desen) {
        this.desen = desen;
    }

    public String getAlinmatarihi() {
        return alinmatarihi;
    }

    public void setAlinmatarihi(String alinmatarihi) {
        this.alinmatarihi = alinmatarihi;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Integer getCekmece_id() {
        return cekmece_id;
    }

    public void setCekmece_id(Integer cekmece_id) {
        this.cekmece_id = cekmece_id;
    }

    public static ArrayList<kiyafet> getKiyafet_test() {
        return Kiyafet_test;
    }

    public static void setKiyafet_test(ArrayList<kiyafet> kiyafet_test) {
        Kiyafet_test = kiyafet_test;
    }

    public static HashMap<String, kiyafet> getKombin() {
        return kombin;
    }

    public static void setKombin(HashMap<String, kiyafet> kombin) {
        kiyafet.kombin = kombin;
    }

    public static HashMap<String, ArrayList<Bitmap>> getKombinler() {
        return kombinler;
    }

    public static void setKombinler(HashMap<String, ArrayList<Bitmap>> kombinler) {
        kiyafet.kombinler = kombinler;
    }
}
