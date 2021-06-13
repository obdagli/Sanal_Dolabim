package tr.edu.yildiz.omerburakdagli.adapter.kabin_turleri;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.KabinOdasi;
import tr.edu.yildiz.omerburakdagli.KiyafetEkle;
import tr.edu.yildiz.omerburakdagli.R;
import tr.edu.yildiz.omerburakdagli.adapter.RecycleAdapterKiyafetlerim;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;

import static tr.edu.yildiz.omerburakdagli.KabinOdasi.photo1;
import static tr.edu.yildiz.omerburakdagli.KabinOdasi.photo2;
import static tr.edu.yildiz.omerburakdagli.KabinOdasi.photo3;
import static tr.edu.yildiz.omerburakdagli.KabinOdasi.photo4;
import static tr.edu.yildiz.omerburakdagli.KabinOdasi.photo5;

public class RecycleAdapterKabin extends RecyclerView.Adapter<RecycleAdapterKabin.MyViewHolder>{
    Context context;
    private ArrayList<kiyafet> kiyafetler;
    String tur;
    public RecycleAdapterKabin(ArrayList<kiyafet> kiyafetler,Context context) {
        this.kiyafetler = kiyafetler;
        this.context = context;
    }
    @NonNull
    @Override
    public RecycleAdapterKabin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kabin_lister,parent,false);
        return new RecycleAdapterKabin.MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterKabin.MyViewHolder holder, int position) {
        holder.foto.setImageBitmap(kiyafetler.get(position).getFoto());
        holder.bt_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tur = kiyafetler.get(position).getTur();
                switch (tur){
                    case "1":
                        kiyafet.kombin.put("1",kiyafetler.get(position));
                        break;
                    case "2":
                        kiyafet.kombin.put("2",kiyafetler.get(position));
                        break;
                    case "3":
                        kiyafet.kombin.put("3",kiyafetler.get(position));
                        break;
                    case "4":
                        kiyafet.kombin.put("4",kiyafetler.get(position));
                        break;
                    case "5":
                        kiyafet.kombin.put("5",kiyafetler.get(position));
                        break;
                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tur);
                ((KabinKiyafetSecim)context).setResult(Activity.RESULT_OK,returnIntent);
                ((KabinKiyafetSecim)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {return kiyafetler.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button bt_choice;
        ImageView foto;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            foto = itemView.findViewById(R.id.u_photo);
            bt_choice = itemView.findViewById(R.id.bt_pchoice);
        }
    }
}
