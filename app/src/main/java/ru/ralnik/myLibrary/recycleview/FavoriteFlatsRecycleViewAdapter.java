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
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;
import ru.ralnik.sqlitedb.FlatRepository;

public class FavoriteFlatsRecycleViewAdapter extends RecyclerView.Adapter<FavoriteFlatsRecycleViewAdapter.myViewHolder>{

    private final Context context;
    private List<Flat> listFlat;
    private OnItemClickListener itemClickListener;

    public FavoriteFlatsRecycleViewAdapter(Context context, List<Flat> listFlat) {
        this.context = context;
        this.listFlat = listFlat;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item_layout, parent, false);
        final myViewHolder holderView = new myViewHolder(view);
        return holderView;
    }


    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
        switch (listFlat.get(position).getComnat()){
            case 1:
                holder.typeOfFlat.setText(R.string.one_bedroom);
                break;
            case 2:
                holder.typeOfFlat.setText(R.string.two_bedroom);
                break;
            case 3:
                holder.typeOfFlat.setText(R.string.three_bedroom);
                break;
            case 4:
                holder.typeOfFlat.setText(R.string.four_bedroom);
                break;
        }
        holder.cost.setText(listFlat.get(position).getPrice() + "");
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FlatRepository(context).deleteFavoriteById(listFlat.get(position).getId());
                listFlat.remove(position);
                notifyDataSetChanged();
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


    class myViewHolder extends RecyclerView.ViewHolder {
        DemonsrationButton checkbox;
        ImageView imagePlan;
        TextView typeOfFlat;
        TextView cost;
        ImageView buttonDelete;

        public myViewHolder(View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            imagePlan = itemView.findViewById(R.id.imagePlan);
            typeOfFlat = itemView.findViewById(R.id.typeOfFlat);
            cost = itemView.findViewById(R.id.cost);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
