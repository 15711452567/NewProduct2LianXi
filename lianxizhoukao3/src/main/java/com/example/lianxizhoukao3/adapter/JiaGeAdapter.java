package com.example.lianxizhoukao3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * date:2018.12.27
 * author:赵颖冰(lenovo)
 * function:
 */
public class JiaGeAdapter extends RecyclerView.Adapter<JiaGeAdapter.ViewHolder> {
    Context mContext;

    @NonNull
    @Override
    public JiaGeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
