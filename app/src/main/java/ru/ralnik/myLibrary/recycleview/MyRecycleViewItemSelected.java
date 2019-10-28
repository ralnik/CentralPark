package ru.ralnik.myLibrary.recycleview;

import android.content.Context;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.ralnik.centralpark.R;
import ru.ralnik.centralpark.fragments.FlatDetailsFragment;
import ru.ralnik.centralpark.fragments.SettingsFragment;
import ru.ralnik.centralpark.fragments.TagsFragment;
import ru.ralnik.httpPlayer.HttpPlayerFactory;
import ru.ralnik.model.Flat;
import ru.ralnik.sqlitedb.FlatRepository;


public class MyRecycleViewItemSelected implements OnItemClickListener {

    private final FragmentActivity context;
    private static int oldPosition;
    private static MyRecycleViewAdapter.myViewHolder oldHolder;

    public MyRecycleViewItemSelected(Context context) {
        this.context = (FragmentActivity) context;
    }


    @Override
    public void onItemClick(MyRecycleViewAdapter.myViewHolder holder, int position, String ID) {
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

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        Flat flat = new FlatRepository(context).findById(ID);

        HttpPlayerFactory.getInstance(context).getCommand().setFlatInfo(flat);
        HttpPlayerFactory.getInstance(context).getCommand().selectById(8);

        FlatDetailsFragment flatDetailsFragment = new FlatDetailsFragment(flat);

        ft.add(R.id.conteiner, flatDetailsFragment, TagsFragment.TAG_5);
        ft.addToBackStack(null);
        ft.commit();
    }
}
