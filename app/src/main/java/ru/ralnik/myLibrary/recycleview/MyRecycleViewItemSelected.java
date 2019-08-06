package ru.ralnik.myLibrary.recycleview;

import android.content.Context;
import android.widget.TextView;

import ru.ralnik.centralpark.R;


public class MyRecycleViewItemSelected implements OnItemClickListener {

    private final Context context;
    private static int oldPosition;
    private static MyRecycleViewAdapter.myViewHolder oldHolder;

    public MyRecycleViewItemSelected(Context context) {
        this.context = context;
    }


    @Override
    public void onItemClick(MyRecycleViewAdapter.myViewHolder holder, int position) {
        if(oldHolder != null) {
            for (TextView item : oldHolder.listView) {
                if (oldPosition % 2 == 0) {
                    item.setBackgroundColor(context.getResources().getColor(R.color.color_itemFirstBG_table));
                    item.setTextColor(context.getResources().getColor(R.color.color_text_row_of_table));
                    oldHolder.imageBG.setImageLevel(1);
                } else {
                    item.setBackgroundColor(context.getResources().getColor(R.color.color_itemSecondBG_table));
                    item.setTextColor(context.getResources().getColor(R.color.color_text_row_of_table));
                    oldHolder.imageBG.setImageLevel(1);
                }
            }
        }
        for (TextView item : holder.listView){
            item.setBackgroundColor(context.getResources().getColor(R.color.color_itemSelected));
            holder.imageBG.setImageLevel(1);
        }
        this.oldHolder = holder;
        this.oldPosition = position;
    }
}
