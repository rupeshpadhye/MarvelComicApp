package com.marvel.android.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Creator;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by RUPESH on 1/21/2017.
 */

public class CreatorListAdapter extends RecyclerView.Adapter<CreatorListAdapter.DataObjectHolder> {

    private List<Creator> mCreatorList;
    private Context mContext;

    public CreatorListAdapter(List<Creator> creatorList,Context context) {
        mCreatorList = creatorList;
        mContext=context;
    }

    public void setCreatorList(List<Creator> movieReviewListData) {
        this.mCreatorList.clear();
        this.mCreatorList.addAll(movieReviewListData);
        this.notifyDataSetChanged();
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comic_creator_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.bindHolderItem(mCreatorList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCreatorList.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profile_image)
        CircleImageView profileImg;
        @BindView(R.id.name)
        TextView fullName;
        @BindView(R.id.role)
        TextView role;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHolderItem(Creator creator) {
            fullName.setText(creator.getFullName());
            role.setText(creator.getRole());
            if(creator.getThumbnail() !=null){
                Picasso.with(mContext).load(creator.getThumbnail().getImageUrl()).placeholder(R.mipmap.ic_launcher).into(profileImg);
            }
        }
    }
}
