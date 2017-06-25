package com.minminaya.bogemusic.finder.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.data.model.apimodel.SongList;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Niwa on 2017/6/25.
 */

public class FinderRecycleViewAdapter extends RecyclerView.Adapter<FinderRecycleViewAdapter.ViewHolderA> {

    private List<SongList> songList = new ArrayList<>();

    public List<SongList> getSongList() {
        return songList;
    }

    public void setSongList(List<SongList> songList) {
        this.songList = songList;
    }

    public FinderRecycleViewAdapter() {
//        songList.add(new SongList("1"));
//        songList.add(new SongList("2"));
//        songList.add(new SongList("3"));
//        songList.add(new SongList("4"));
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finder_recycle_view_item, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }

    @Override
    public void onBindViewHolder(ViewHolderA holder, int position) {
            Glide.with(App.getINSTANCE()).load(songList.get(position).getPicBig()).into(holder.songImg);
        holder.songTitle.setText(songList.get(position).getTitle());
//        Log.e("observer", "onBindViewHolder" + songList.get(0).getTitle());

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class ViewHolderA extends RecyclerView.ViewHolder {
        @Bind(R.id.song_img)
        ImageView songImg;
        @Bind(R.id.song_title)
        TextView songTitle;

        ViewHolderA(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
