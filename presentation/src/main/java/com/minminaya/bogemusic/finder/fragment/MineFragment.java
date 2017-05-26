package com.minminaya.bogemusic.finder.fragment;

import android.os.Bundle;
import android.view.View;

import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;

/**
 * Created by Niwa on 2017/5/26.
 */

public class MineFragment extends BaseFragment {

    public static MineFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MineFragment fragment = new MineFragment();
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
        return R.layout.fragment_mine;
    }

    @Override
    protected void unBind() {

    }
}
