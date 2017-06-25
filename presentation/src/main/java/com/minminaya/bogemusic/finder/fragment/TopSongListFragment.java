package com.minminaya.bogemusic.finder.fragment;

import android.os.Bundle;
import android.view.View;

import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;

/**
 * Created by Niwa on 2017/6/25.
 */

public class TopSongListFragment extends BaseFragment {

    public static TopSongListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TopSongListFragment fragment = new TopSongListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void iniView(View view) {

    }

    @Override
    public void bind() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public int setContView() {
        return R.layout.fragment_top_song_list;
    }

    @Override
    protected void unBind() {

    }
}
