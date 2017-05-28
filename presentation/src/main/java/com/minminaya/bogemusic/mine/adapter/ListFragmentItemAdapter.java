package com.minminaya.bogemusic.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.minminaya.bogemusic.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Niwa on 2017/5/28.
 */

public class ListFragmentItemAdapter extends RecyclerView.Adapter<ListFragmentItemAdapter.ViewHolderA> {
    private List<String> list = new ArrayList<>();
    public ListFragmentItemAdapter() {
        for (int i = 0; i < 20; i++) {
            list.add("item" + i);
        }
    }




    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view, parent, false);
        ViewHolderA holderA = new ViewHolderA(itemView);
        return holderA;
    }

    @Override
    public void onBindViewHolder(ViewHolderA holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderA extends RecyclerView.ViewHolder {
        @Bind(R.id.text_item_song_title)
        TextView textItemSongTitle;
        @Bind(R.id.text_item_artist)
        TextView textItemArtist;

        public ViewHolderA(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
