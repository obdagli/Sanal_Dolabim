package tr.edu.yildiz.omerburakdagli.classes;

import java.util.ArrayList;

public class cekmece {
    String ad;
    public static ArrayList<cekmece> Cekmece_test = new ArrayList<>();

    public cekmece(String ad) {
        this.ad = ad;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public static ArrayList<cekmece> getCekmece_test() {
        return Cekmece_test;
    }

    public static void setCekmece_test(ArrayList<cekmece> cekmece_test) {
        Cekmece_test = cekmece_test;
    }
}
