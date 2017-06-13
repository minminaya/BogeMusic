package com.minminaya.bogemusic.mine.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.mine.presenter.ListFragmentPresenter;
import com.minminaya.bogemusic.play.activity.MusicPlayActivity;
import com.minminaya.bogemusic.utils.DataSetUtil;
import com.minminaya.data.model.LocalMusicModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Niwa on 2017/5/28.
 */

public class ListFragmentItemAdapter extends RecyclerView.Adapter<ListFragmentItemAdapter.ViewHolderA> {

    public ListFragmentItemAdapter() {
    }
    private ListFragmentPresenter mPresenter;

    public void setmPresenter(ListFragmentPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    private List<LocalMusicModel> songList;


    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view, parent, false);
        ViewHolderA holderA = new ViewHolderA(itemView);
        return holderA;
    }

    @Override
    public void onBindViewHolder(ViewHolderA holder, int position) {
        if (songList != null) {
            holder.textItemArtist.setText(songList.get(position).getSongArtist());
            holder.textItemSongTitle.setText(songList.get(position).getSongTitle());
            setSongItemListener(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }


    public class ViewHolderA extends RecyclerView.ViewHolder {
        @Bind(R.id.layout_song_list_item)
        RelativeLayout layoutSongListItem;
        @Bind(R.id.text_item_song_title)
        TextView textItemSongTitle;
        @Bind(R.id.text_item_artist)
        TextView textItemArtist;

        public ViewHolderA(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setSongList(List<LocalMusicModel> songList) {
        this.songList = songList;
    }


    private void setSongItemListener(ViewHolderA holder, final int position) {
        holder.layoutSongListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mPresenter.getMvpView().getContext(), MusicPlayActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putInt("position", position);

                mPresenter.getMvpView().startActivity(intent);
                mPresenter.seekSong(position);
            }
        });
    }
}
