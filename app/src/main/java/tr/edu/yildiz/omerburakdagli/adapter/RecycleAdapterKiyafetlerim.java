package tr.edu.yildiz.omerburakdagli.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.R;
import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;

public class RecycleAdapterKiyafetlerim extends RecyclerView.Adapter<RecycleAdapterKiyafetlerim.MyViewHolder>{
    Context context;
    private ArrayList<kiyafet> kiyafetler;
    public RecycleAdapterKiyafetlerim(ArrayList<kiyafet> kiyafetler, Context context) {
        this.kiyafetler = kiyafetler;
        this.context = context;
    }
    @NonNull
    @Override
    public RecycleAdapterKiyafetlerim.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kiyafet_lister,parent,false);
        return new RecycleAdapterKiyafetlerim.MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterKiyafetlerim.MyViewHolder holder, int position) {

        holder.foto.setImageBitmap(kiyafetler.get(position).getFoto());
        holder.bt_bilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Kıyafet Bilgileri")
                        .setMessage("Tür: "+kiyafetler.get(position).getTur()+"\nRenk: "+
                                kiyafetler.get(position).getRenk()+"\nDesen: "+
                                kiyafetler.get(position).getDesen()+"\nAlınma Tarihi: "+
                                kiyafetler.get(position).getAlinmatarihi()+"\nFiyat: "+
                                kiyafetler.get(position).getFiyat())
                        .setNegativeButton("Geri", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return kiyafetler.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button bt_bilgi;

        ImageView foto;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            foto = itemView.findViewById(R.id.u_photo);
            bt_bilgi = itemView.findViewById(R.id.bt_pchoice);
        }
    }
}
