package ru.ralnik.myLibrary.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collection;
import java.util.List;

import ru.ralnik.centralpark.R;
import ru.ralnik.model.Flat;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.myViewHolder> {

    private List<Flat> flatList;

    public MyRecycleViewAdapter(List<Flat> flatList) {
        this.flatList = flatList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_of_table, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.column_1.setText(flatList.get(position).getCorpus()+"");
        holder.column_2.setText(flatList.get(position).getNom_kv()+"");
        holder.column_3.setText(flatList.get(position).getEtag()+"");
        holder.column_4.setText(flatList.get(position).getComnat()+"");
        holder.column_5.setText(flatList.get(position).getPloshad().toString());
        holder.column_6.setText(flatList.get(position).getPrice().toString());
        holder.column_7.setText(flatList.get(position).getStatus()+"");
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return flatList.size();
    }

    public void setItems(Collection<Flat> flats) {
        flatList.addAll(flats);
        notifyDataSetChanged();
    }

    public void clearItems() {
        flatList.clear();
        notifyDataSetChanged();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        // ViewHolder должен содержать переменные для всех
        // View-компонентов, которым хотим задавать какие-либо свойства
        // в процессе работы пользователя со списком
        private TextView column_1;
        private TextView column_2;
        private TextView column_3;
        private TextView column_4;
        private TextView column_5;
        private TextView column_6;
        private TextView column_7;

        public myViewHolder(View itemView) {
            super(itemView);
            column_1 = itemView.findViewById(R.id.column_1);
            column_2 = itemView.findViewById(R.id.column_2);
            column_3 = itemView.findViewById(R.id.column_3);
            column_4 = itemView.findViewById(R.id.column_4);
            column_5 = itemView.findViewById(R.id.column_5);
            column_6 = itemView.findViewById(R.id.column_6);
            column_7 = itemView.findViewById(R.id.column_7);
        }

    }
}
