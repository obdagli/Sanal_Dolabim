package tr.edu.yildiz.omerburakdagli.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tr.edu.yildiz.omerburakdagli.EtkinlikGuncelle;
import tr.edu.yildiz.omerburakdagli.KabinOdasi;
import tr.edu.yildiz.omerburakdagli.KiyafetEkle;
import tr.edu.yildiz.omerburakdagli.R;
import tr.edu.yildiz.omerburakdagli.adapter.kombin.RecycleAdapterKombinlerim;
import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.etkinlik;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class RecycleAdapterEtkinliklerim extends RecyclerView.Adapter<RecycleAdapterEtkinliklerim.MyViewHolder>{

    private veritabani v1;
    Context context;
    Integer choice;
    public RecycleAdapterEtkinliklerim(Context context,Integer choice) {
        this.context = context;
        this.choice = choice;
    }
    @NonNull
    @Override
    public RecycleAdapterEtkinliklerim.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.etkinlik_lister,parent,false);
        return new RecycleAdapterEtkinliklerim.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterEtkinliklerim.MyViewHolder holder, int position) {
        v1 = new veritabani(context);
        System.out.println("position : "+position);
        System.out.println("etkinlik pos ad ->"+etkinlik.etkinlikler.get(position).getAd());
        holder.bt_etkinlik.setText(etkinlik.etkinlikler.get(position).getAd());
        holder.bt_etkinlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Etkinlik Bilgileri")
                        .setMessage("No: "+etkinlik.etkinlikler.get(position).getEtkinlik_no()+"\nEt. Ad: "+
                                etkinlik.etkinlikler.get(position).getAd()+"\nTürü: "+
                                etkinlik.etkinlikler.get(position).getTur()+"\nTarihi: "+
                                etkinlik.etkinlikler.get(position).getTarih()+"\nLokasyon: "+
                                etkinlik.etkinlikler.get(position).getLokasyon()+"\nKombin Adı: "+
                                etkinlik.etkinlikler.get(position).getKombin_adi())
                        .setNegativeButton("Geri", null)
                        .show();
            }
        });
        holder.bt_etkinlikguncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EtkinlikGuncelle.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        holder.bt_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setMessage("Do you want to delete?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete(etkinlik.etkinlikler.get(position).getEtkinlik_no());
                                etkinlik.etkinlikler.remove(position);
                                RecycleAdapterEtkinliklerim.super.notifyDataSetChanged();

                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return etkinlik.etkinlikler.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button bt_etkinlik,bt_etkinlikguncelle,bt_sil;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            bt_etkinlik = itemView.findViewById(R.id.bt_etkinlik);
            bt_etkinlikguncelle = itemView.findViewById(R.id.bt_etkinlikguncelle);
            bt_sil = itemView.findViewById(R.id.bt_etkinliksil);
        }
    }
    private void delete(String isim)
    {
        SQLiteDatabase db=v1.getReadableDatabase();
        if(choice == 1){
            db.delete("Kombin","kombin_adi"+"=?",new String[]{isim});
        }
        db.delete("Etkinlik","etkinlik_no"+"=?",new String[]{isim});

    }
}
