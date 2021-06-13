package tr.edu.yildiz.omerburakdagli.classes;

import java.util.ArrayList;

public class kabin {
    String bolum;
    public static ArrayList<ArrayList<String>> kombin = new ArrayList<>();

    public kabin(String bolum) {
        this.bolum = bolum;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public static ArrayList<ArrayList<String>> getKombin() {
        return kombin;
    }

    public static void setKombin(ArrayList<ArrayList<String>> kombin) {
        kabin.kombin = kombin;
    }
}
