package ru.ralnik.myLibrary.recycleview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import ru.ralnik.centralpark.R;
import ru.ralnik.model.Flat;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.myViewHolder>{

    private final Context context;
    private List<Flat> listFlat;
    private OnItemClickListener itemClickListener;

    public MyRecycleViewAdapter(Context context, List<Flat> listFlat) {
        this.context = context;
        this.listFlat = listFlat;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_of_table, parent, false);
        final myViewHolder holderView = new myViewHolder(view);
        return holderView;
    }


    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {

        holder.column_1.setText(listFlat.get(position).getCorpus() + "");
        holder.column_2.setText(listFlat.get(position).getNom_kv() + "");
        holder.column_3.setText(listFlat.get(position).getEtag() + "");
        holder.column_4.setText(listFlat.get(position).getComnat() + "");
        holder.column_5.setText(listFlat.get(position).getPloshad() + "");
        holder.column_6.setText(listFlat.get(position).getPrice() + "");
        holder.column_7.setText(listFlat.get(position).getStatus() + "");

        for(TextView item: holder.listView) {
            if (position % 2 == 0) {
                item.setBackgroundColor(context.getResources().getColor(R.color.color_itemFirstBG_table));
                item.setTextColor(context.getResources().getColor(R.color.color_text_row_of_table));
            } else {
                item.setBackgroundColor(context.getResources().getColor(R.color.color_itemSecondBG_table));
                item.setTextColor(context.getResources().getColor(R.color.color_text_row_of_table));
            }
        }
        //нужно для чтобы был цвет границ между ячейками таблицы
        holder.imageBG.setImageLevel(1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(holder, position, listFlat.get(position).getId());
            }
        });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return listFlat.size();
    }


    public void setOnClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView column_1;
        TextView column_2;
        TextView column_3;
        TextView column_4;
        TextView column_5;
        TextView column_6;
        TextView column_7;
        List<TextView> listView = new ArrayList<>();
        ImageView imageBG;

        public myViewHolder(View itemView) {
            super(itemView);
            column_1 = itemView.findViewById(R.id.column_1);
            column_2 = itemView.findViewById(R.id.column_2);
            column_3 = itemView.findViewById(R.id.column_3);
            column_4 = itemView.findViewById(R.id.column_4);
            column_5 = itemView.findViewById(R.id.column_5);
            column_6 = itemView.findViewById(R.id.column_6);
            column_7 = itemView.findViewById(R.id.column_7);
            imageBG = itemView.findViewById(R.id.imageBG);
            listView.add(column_1);
            listView.add(column_2);
            listView.add(column_3);
            listView.add(column_4);
            listView.add(column_5);
            listView.add(column_6);
            listView.add(column_7);
        }
    }
}
