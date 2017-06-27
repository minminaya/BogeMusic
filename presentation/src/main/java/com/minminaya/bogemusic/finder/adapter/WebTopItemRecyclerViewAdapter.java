package com.minminaya.bogemusic.finder.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.finder.activity.WebMusicPlayActivity;
import com.minminaya.data.api.ApiMethodString;
import com.minminaya.data.http.NetWork;
import com.minminaya.data.model.apimodel.Bitrate;
import com.minminaya.data.model.apimodel.SongAllInfo;
import com.minminaya.data.model.apimodel.SongList;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/** 排行榜中的list列表的adapter
 * Created by Niwa on 2017/6/26.
 */

public class WebTopItemRecyclerViewAdapter extends RecyclerView.Adapter<WebTopItemRecyclerViewAdapter.ViewHolderAA> {

    private List<SongList> mSongList;
    private Bitrate mBitrate;

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
    public void onBindViewHolder(ViewHolderAA holder, final int position) {
        holder.textItemArtist.setText(mSongList.get(position).getArtistName());
        holder.textItemSongTitle.setText(mSongList.get(position).getTitle());
        holder.layoutSongListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSongInfo(mSongList.get(position).getSongId());
            }
        });
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


    /**
     *  请求网络获取歌曲数据信息
     *
     * */
    private void getSongInfo(String songId) {
        NetWork.getMusicApi()
                .loadSongInfo(ApiMethodString.SONG_INFO_FLAG, songId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    Observer<SongAllInfo> observer = new Observer<SongAllInfo>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(SongAllInfo value) {
            mBitrate = value.getBitrate();
            Log.e("FinderAdapter", "" + value.getSonginfo().getTitle());
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            Intent intent = new Intent(App.getINSTANCE(), WebMusicPlayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("fileLink", mBitrate.getFileLink());
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getINSTANCE().startActivity(intent);
        }
    };





}
