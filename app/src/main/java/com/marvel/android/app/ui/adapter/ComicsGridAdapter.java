package com.marvel.android.app.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicsGridAdapter extends ArrayAdapter<Comic> {
    private Context mContext;
    private int layoutResourceId;
    private List<Comic> mGridData;

    public ComicsGridAdapter(Context mContext, int layoutResourceId, List<Comic> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public void setGridData(List<Comic> mGridData) {
        this.mGridData.clear();
        this.mGridData.addAll(mGridData);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.thumbnail = (ImageView) row.findViewById(R.id.thumbnail);
            holder.name=(TextView)row.findViewById(R.id.name);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        Comic item = mGridData.get(position);
        if (item.getThumbnail() != null && item.getThumbnail().getImageUrl()!=null) {
            Picasso.with(mContext).load(item.getThumbnail().getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.thumbnail);
        } else {
            holder.thumbnail.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher));
        }
        holder.name.setText(item.getTitle());
        return row;
    }

    static class ViewHolder {
        ImageView thumbnail;
        TextView name;
    }
}
