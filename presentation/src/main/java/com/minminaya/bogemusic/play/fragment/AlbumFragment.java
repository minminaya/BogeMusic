package com.minminaya.bogemusic.play.fragment;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;


public class AlbumFragment extends BaseFragment {


    @Bind(R.id.play_album)
    CircleImageView playAlbumView;
    ObjectAnimator anim;

    public static AlbumFragment newInstance() {
        AlbumFragment fragment = new AlbumFragment();
        return fragment;
    }


    @Override
    public void iniView(View view) {
        anim = ObjectAnimator.ofFloat(playAlbumView, "rotation", 0f, 360f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(1000);
        anim.setDuration(21000);
        anim.start();
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
        return R.layout.fragment_album;
    }

    @Override
    protected void unBind() {

    }
}
