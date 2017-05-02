package com.gws.android.tips.ui.recycleview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/3/20.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public ViewHolder(Context context, View itemView, ViewGroup parent)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    public static ViewHolder get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        ViewHolder holder = new ViewHolder(context, itemView, parent);
        return holder;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewId, String text){
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int color){
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    public ViewHolder setHint(int viewId, String text){
        TextView view = getView(viewId);
        view.setHint(text);
        return this;
    }
    public ViewHolder setImage(Context context, int viewId, String path, int errorImageRes){
        ImageView view = getView(viewId);
        Glide.with(context).load(path).into(view);
        return this;
    }
    public ViewHolder setImageDrawable(int viewId, Drawable drawable){
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }
    public ViewHolder setImageResource(int viewId, int imageRes){
        ImageView view = getView(viewId);
        view.setImageResource(imageRes);
        return this;
    }
    public ViewHolder setVisibility(int viewId, int visibility){
        getView(viewId).setVisibility(visibility);
        return this;
    }
    public ViewHolder setBackgroundColor(int viewId, int color){
        getView(viewId).setBackgroundColor(color);
        return this;
    }
    public ViewHolder setBackgroundResource(int viewId, int res){
        getView(viewId).setBackgroundResource(res);
        return this;
    }
    public ViewHolder hideViews(int...viewIds){
        for(int id:viewIds){
            getView(id).setVisibility(View.INVISIBLE);
        }
        return this;
    }
}
