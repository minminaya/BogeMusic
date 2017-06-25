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
import com.minminaya.data.api.ApiMethodString;
import com.minminaya.data.http.NetWork;
import com.minminaya.data.model.apimodel.SongAllInfo;
import com.minminaya.data.model.apimodel.SongList;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finder_recycle_view_item, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }

    @Override
    public void onBindViewHolder(ViewHolderA holder, final int position) {
        Glide.with(App.getINSTANCE()).load(songList.get(position).getPicBig()).into(holder.songImg);
        holder.songTitle.setText(songList.get(position).getTitle());
        holder.songImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSongInfo(songList.get(position).getSongId());
            }
        });
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


    Observer<SongAllInfo> observer = new Observer<SongAllInfo>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(SongAllInfo value) {
            Log.e("FinderAdapter", "" + value.getSonginfo().getTitle());
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {

        }
    };

}
