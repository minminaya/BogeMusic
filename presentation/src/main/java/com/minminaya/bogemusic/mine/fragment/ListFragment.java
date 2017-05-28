package com.minminaya.bogemusic.mine.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.mine.adapter.ListFragmentItemAdapter;

import butterknife.Bind;

/**
 * Created by Niwa on 2017/5/28.
 */

public class ListFragment extends BaseFragment {

    @Bind(R.id.recycle_view)
    XRecyclerView recycleView;


    public static ListFragment newInstance() {

        Bundle args = new Bundle();

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void iniView(View view) {
        ListFragmentItemAdapter listFragmentItemAdapter = new ListFragmentItemAdapter();
        recycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recycleView.setLayoutManager(new LinearLayoutManager(App.getINSTANCE()));
        recycleView.setAdapter(listFragmentItemAdapter);
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
        return R.layout.fragment_list;
    }

    @Override
    protected void unBind() {

    }

}
