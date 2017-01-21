package com.marvel.android.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Character;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by RUPESH on 1/21/2017.
 */

public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListAdapter.DataObjectHolder> {

    private List<Character> mCreatorList;
    private Context mContext;

    public CharactersListAdapter(List<Character> creatorList,Context context) {
        mCreatorList = creatorList;
        mContext=context;
    }

    public void setCreatorList(List<Character> movieReviewListData) {
        this.mCreatorList.clear();
        this.mCreatorList.addAll(movieReviewListData);
        this.notifyDataSetChanged();
    }


    @Override
    public CharactersListAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comic_character_item, parent, false);

        CharactersListAdapter.DataObjectHolder dataObjectHolder = new CharactersListAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(CharactersListAdapter.DataObjectHolder holder, int position) {
        holder.bindHolderItem(mCreatorList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCreatorList.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.charecter_image)
        CircleImageView characterImg;
        @BindView(R.id.character_name)
        TextView characterName;
        @BindView(R.id.character_desc)
        TextView CharacterDesc;
        @BindView(R.id.role)
        TextView role;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHolderItem(Character character) {
            characterName.setText(character.getName());
            CharacterDesc.setText(character.getDescription());
            role.setText(character.getRole());
            if(character.getThumbnail() !=null){
                Picasso.with(mContext).load(character.getThumbnail().getImageUrl()).placeholder(R.mipmap.ic_launcher).into(characterImg);
            }
        }
    }
}

