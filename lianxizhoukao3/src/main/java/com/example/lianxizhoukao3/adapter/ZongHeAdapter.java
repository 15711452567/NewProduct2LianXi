package com.example.lianxizhoukao3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lianxizhoukao3.R;
import com.example.lianxizhoukao3.bean.ResponseBean2;
import com.example.lianxizhoukao3.bean.ZongHeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * date:2018.12.27
 * author:赵颖冰(lenovo)
 * function:
 */
public class ZongHeAdapter extends RecyclerView.Adapter<ZongHeAdapter.ViewHolder> {
    Context mContext;
    List<ZongHeBean.DataBean> list;

    public ZongHeAdapter(Context context, List<ZongHeBean.DataBean> data) {
        mContext = context;
        list=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ZongHeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext, R.layout.recy_zonghe_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ZongHeAdapter.ViewHolder viewHolder, int i) {
        String images = list.get(i).getImages();
        String title = list.get(i).getTitle();
        int price = list.get(i).getPrice();

        String[] split = images.split("\\|");
        viewHolder.simple_zonghe.setImageURI(split[0]);
        viewHolder.title_zonghe.setText(title);
        viewHolder.price_zonghe.setText("￥"+price+"元");
        final int pid = list.get(i).getPid();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClick.setOnClick(pid);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simple_zonghe;
        TextView title_zonghe,price_zonghe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            simple_zonghe=itemView.findViewById(R.id.simple_zonghe);
            title_zonghe=itemView.findViewById(R.id.title_zonghe);
            price_zonghe=itemView.findViewById(R.id.price_zonghe);
        }
    }

    public interface OnClick{
        void setOnClick(int pid);
    }

    private OnClick mOnClick;

    public void setOnClick(OnClick onClick) {
        mOnClick = onClick;
    }
}
