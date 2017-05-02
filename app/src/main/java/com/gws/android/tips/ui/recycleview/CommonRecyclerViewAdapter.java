package com.gws.android.tips.ui.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder>  {

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T item, int position);
    }

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected final int mItemLayoutId;
    public OnItemClickListener mOnItemClickListener;


    public CommonRecyclerViewAdapter(Context context,List<T> dates,int layoutId){
        this.mContext = context;
        if(null == dates){
            mDatas = new ArrayList<>();
        }else{
            this.mDatas = dates;
        }
        this.mItemLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(mContext);
    }
    public CommonRecyclerViewAdapter(Context context,int layoutId){
        this.mContext = context;
        mDatas = new ArrayList<>();
        this.mItemLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.get(mContext, parent, mItemLayoutId);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,mDatas.get(position), position);
                }
            });
        }
        convert(holder, mDatas.get(position),position);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public List<T> getDatas(){
        return mDatas;
    }
    public void setDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }



    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }




    public abstract void convert(ViewHolder helper, T item, int position);

}
