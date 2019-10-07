package com.fed.androidschool_converter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ValuesHolder> {

private List<Conversion> conversions;
onValueItemClickListener onValueItemClickListener;

public MainAdapter(onValueItemClickListener onValueItemClickListener){
    this.onValueItemClickListener = onValueItemClickListener;
}

public void setValues(Conversion[] conversions){
   this.conversions = Arrays.asList(conversions);
    notifyDataSetChanged();
}



    @NonNull
    @Override
    public ValuesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_value, parent, false);
        return new ValuesHolder(view, onValueItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ValuesHolder holder, int position) {

        holder.onBind(conversions.get(position));
        holder.mName.setText(conversions.get(position).strRes);

    }


    @Override
    public int getItemCount() {
        return conversions.size();
    }

    static class ValuesHolder extends RecyclerView.ViewHolder{

        private final TextView mName;
        private Conversion conversion;

        public ValuesHolder(@NonNull View itemView, onValueItemClickListener onValueItemClickListener) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_value_textview);
            itemView.setOnClickListener(v->onValueItemClickListener.onClick(conversion));
        //    getAdapterPosition();
        }

        public void onBind(Conversion conversion)
        {
            this.conversion =conversion;
        }
    }
}
