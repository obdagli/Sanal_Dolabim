package tr.edu.yildiz.omerburakdagli.adapter.kombin;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.EtkinlikEkle;
import tr.edu.yildiz.omerburakdagli.KabinOdasi;
import tr.edu.yildiz.omerburakdagli.R;
import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterCekmecelerim;
import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class RecycleAdapterKombinlerim extends RecyclerView.Adapter<RecycleAdapterKombinlerim.MyViewHolder>{
    Context context;
    Integer choice;
    private veritabani v1;
    //private ArrayList<ArrayList<Bitmap>> kombinler;
    public RecycleAdapterKombinlerim(Context context,Integer choice) {
        //this.kombinler = kombinler;
        this.context = context;
        this.choice = choice;
    }
    @NonNull
    @Override
    public RecycleAdapterKombinlerim.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kombin_lister,parent,false);
        return new RecycleAdapterKombinlerim.MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterKombinlerim.MyViewHolder holder, int position) {
        v1 = new veritabani(context);
        holder.tb_text.setText(KabinOdasi.kombinisimler.get(position));
        holder.photo1.setImageBitmap(kiyafet.kombinler.get(KabinOdasi.kombinisimler.get(position)).get(0));
        holder.photo2.setImageBitmap(kiyafet.kombinler.get(KabinOdasi.kombinisimler.get(position)).get(1));
        holder.photo3.setImageBitmap(kiyafet.kombinler.get(KabinOdasi.kombinisimler.get(position)).get(2));
        holder.photo4.setImageBitmap(kiyafet.kombinler.get(KabinOdasi.kombinisimler.get(position)).get(3));
        holder.photo5.setImageBitmap(kiyafet.kombinler.get(KabinOdasi.kombinisimler.get(position)).get(4));
        if(choice == 1){
            holder.cb_kaydet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        EtkinlikEkle.etkinlik_kombinkayit = KabinOdasi.kombinisimler.get(position);
                    }else{
                        EtkinlikEkle.etkinlik_kombinkayit = "";
                    }
                }
            });

        }
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
                                kiyafet.kombinler.remove(KabinOdasi.kombinisimler.get(position));
                                delete(KabinOdasi.kombinisimler.get(position));
                                KabinOdasi.kombinisimler.remove(position);
                                RecycleAdapterKombinlerim.super.notifyDataSetChanged();
                            }
                        })
                        .show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return kiyafet.kombinler.size();
    }
    private void delete(String isim)
    {
        SQLiteDatabase db=v1.getReadableDatabase();
        if(choice == 1){
            db.delete("Etkinlik","kombin_adi"+"=?",new String[]{isim});
        }
        db.delete("Kombin","kombin_adi"+"=?",new String[]{isim});

    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tb_text;
        CheckBox cb_kaydet;
        Button bt_sil;

        ImageView photo1,photo2,photo3,photo4,photo5;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);


            photo1 = itemView.findViewById(R.id.photo1);
            photo2 = itemView.findViewById(R.id.photo2);
            photo3 = itemView.findViewById(R.id.photo3);
            photo4 = itemView.findViewById(R.id.photo4);
            photo5 = itemView.findViewById(R.id.photo5);
            tb_text = itemView.findViewById(R.id.tb_kombin);
            cb_kaydet = itemView.findViewById(R.id.cb_etkinlik_kombinkaydet);
            bt_sil = itemView.findViewById(R.id.bt_kombinsil);
        }
    }
}
