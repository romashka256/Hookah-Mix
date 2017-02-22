package com.hookah.roma.hookahmix.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.models.objects.Tabak;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private LayoutInflater inflater;
    private List<Tabak> listData;
    private Context context;

    private ItemClickCallback itemClickCallback;

    public RecyclerViewAdapter(List<Tabak> listData, Context c) {
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    @Override
    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tabak_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Tabak tabak = listData.get(position);
        holder.tabakFamily.setText("asdasd");
        holder.tabakRating.setText(tabak.getFamily());
        holder.tabakName.setText(tabak.getName());

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public void setListData(ArrayList<Tabak> tabakList) {
        this.listData.clear();
        this.listData.addAll(tabakList);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View container;
        TextView tabakName;
        TextView tabakFamily;
        TextView tabakRating;
        CheckBox checkBox;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tabakFamily = (TextView) itemView.findViewById(R.id.family_textview_item);
            tabakRating = (TextView) itemView.findViewById(R.id.rating_int_item);
            tabakName = (TextView) itemView.findViewById(R.id.name_textview_item);
            container = itemView.findViewById(R.id.cont_tabak_list);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickCallback.onItemClick(getAdapterPosition());
        }
    }
}
