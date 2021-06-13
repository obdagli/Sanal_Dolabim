package tr.edu.yildiz.omerburakdagli.classes;

import java.util.ArrayList;

public class etkinlik {
    String etkinlik_no;
    String ad;
    String tur;
    String tarih;
    String lokasyon;
    String kombin_adi;
    public static ArrayList<etkinlik> etkinlikler = new ArrayList<>();

    public etkinlik(String etkinlik_no, String ad, String tur, String tarih, String lokasyon, String kombin_adi) {
        this.etkinlik_no = etkinlik_no;
        this.ad = ad;
        this.tur = tur;
        this.tarih = tarih;
        this.lokasyon = lokasyon;
        this.kombin_adi = kombin_adi;
    }

    public String getEtkinlik_no() {
        return etkinlik_no;
    }

    public void setEtkinlik_no(String etkinlik_no) {
        this.etkinlik_no = etkinlik_no;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(String lokasyon) {
        this.lokasyon = lokasyon;
    }

    public String getKombin_adi() {
        return kombin_adi;
    }

    public void setKombin_adi(String kombin_adi) {
        this.kombin_adi = kombin_adi;
    }

    public static ArrayList<etkinlik> getEtkinlikler() {
        return etkinlikler;
    }

    public static void setEtkinlikler(ArrayList<etkinlik> etkinlikler) {
        etkinlik.etkinlikler = etkinlikler;
    }
}
