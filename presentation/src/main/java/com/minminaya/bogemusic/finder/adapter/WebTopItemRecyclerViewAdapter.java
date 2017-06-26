package com.minminaya.bogemusic.finder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.data.model.apimodel.SongList;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/** 排行榜中的list列表的adapter
 * Created by Niwa on 2017/6/26.
 */

public class WebTopItemRecyclerViewAdapter extends RecyclerView.Adapter<WebTopItemRecyclerViewAdapter.ViewHolderAA> {

    private List<SongList> mSongList;


    public void setmSongList(List<SongList> mSongList) {
        this.mSongList = mSongList;
    }

    @Override
    public ViewHolderAA onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getINSTANCE()).inflate(R.layout.item_recycle_view, parent, false);
        ViewHolderAA holderAA = new ViewHolderAA(view);
        return holderAA;
    }

    @Override
    public void onBindViewHolder(ViewHolderAA holder, int position) {
        holder.textItemArtist.setText(mSongList.get(position).getArtistName());
        holder.textItemSongTitle.setText(mSongList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }

    class ViewHolderAA extends RecyclerView.ViewHolder {
        @Bind(R.id.text_item_song_title)
        TextView textItemSongTitle;
        @Bind(R.id.text_item_artist)
        TextView textItemArtist;
        @Bind(R.id.layout_song_list_item)
        RelativeLayout layoutSongListItem;

        public ViewHolderAA(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
