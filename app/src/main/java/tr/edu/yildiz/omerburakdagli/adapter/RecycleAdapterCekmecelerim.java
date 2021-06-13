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

import java.util.ArrayList;

import tr.edu.yildiz.omerburakdagli.KiyafetEkle;
import tr.edu.yildiz.omerburakdagli.R;
import tr.edu.yildiz.omerburakdagli.classes.cekmece;
import tr.edu.yildiz.omerburakdagli.classes.kiyafet;
import tr.edu.yildiz.omerburakdagli.database.veritabani;

public class RecycleAdapterCekmecelerim extends RecyclerView.Adapter<RecycleAdapterCekmecelerim.MyViewHolder>{


    Context context;
    private veritabani v1;
    private ArrayList<cekmece> cekmeceler;
    public RecycleAdapterCekmecelerim(ArrayList<cekmece> cekmeceler, Context context) {
        this.cekmeceler = cekmeceler;
        this.context = context;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cekmece_lister,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterCekmecelerim.MyViewHolder holder, int position) {
        holder.bt_cekmece.setText("Çekmece -> "+(position+1));
        v1 = new veritabani(context);
        holder.bt_kiyafetekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KiyafetEkle.class);
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
                                delete(position);
                                cekmeceler.remove(position);
                                RecycleAdapterCekmecelerim.super.notifyDataSetChanged();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cekmeceler.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button bt_cekmece,bt_kiyafetekle,bt_sil;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            bt_cekmece = itemView.findViewById(R.id.bt_cekmece);
            bt_kiyafetekle = itemView.findViewById(R.id.bt_kiyafetekle);
            bt_sil = itemView.findViewById(R.id.bt_sil);
        }
    }
    private void delete(Integer pos)
    {
        SQLiteDatabase db=v1.getReadableDatabase();
        db.delete("Kiyafet","cekmece_id"+"=?",new String[]{Integer.toString(pos+1)});
        db.delete("Cekmece","cekmece_id"+"=?",new String[]{Integer.toString(pos+1)});
    }
}
