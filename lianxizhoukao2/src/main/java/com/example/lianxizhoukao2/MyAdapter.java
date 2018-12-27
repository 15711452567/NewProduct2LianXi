package com.example.lianxizhoukao2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.security.PublicKey;
import java.util.List;

/**
 * date:2018.12.26
 * author:赵颖冰(lenovo)
 * function:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context mContext;
    List<ResponseBean.DataBean> list;

    public MyAdapter(Context context, List<ResponseBean.DataBean> tuijian) {
        mContext = context;
        list=tuijian;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.recy_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = list.get(i).getTitle();
        String images = list.get(i).getImages();
        float price = list.get(i).getPrice();
        String[] split = images.split("\\|");
        viewHolder.simple.setImageURI(split[0]);
        viewHolder.tv_title.setText(title);
        viewHolder.tv_price.setText(price+"");
        final int pid = list.get(i).getPid();
        final int sellerid = list.get(i).getSellerid();
        ResponseBean2 responseBean2=new ResponseBean2(pid);
        EventBus.getDefault().postSticky(responseBean2);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClick.setOnClick(pid);


            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnClick!=null){
                    mOnClick.setOnLongClick(sellerid);
                }

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simple;
        TextView tv_title,tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simple=itemView.findViewById(R.id.simple);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_price=itemView.findViewById(R.id.tv_price);
        }
    }

    public interface OnClick{
        void setOnClick(int pid);
        void setOnLongClick(int sellerid);
    }

    private OnClick mOnClick;

    public void setOnClick(OnClick onClick) {
        mOnClick = onClick;
    }
}
