package com.miracle.fabpop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author miracle
 * @date 2019-10-22
 * @email ruanyandongai@gmail.com
 * @blog https://ruanyandong.github.io
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private List<String> mList;

    public RvAdapter(List<String> list) {
        mList = list;
    }

    /**
     * 创建ViewHolder
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        MyViewHolder holder = new MyViewHolder(item);
        return holder;
    }

    /**
     * 通过ViewHolder对item中的控件进行控制（如：显示数据等等）
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        String data = mList.get(i);
        holder.tv.setText(data);
    }

    /**
     * 返回列表长度
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 创建ViewHolder类，用来缓存item中的子控件，避免不必要的findViewById
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item);
        }
    }
}