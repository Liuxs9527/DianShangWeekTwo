package com.example.administrator.dianshangweektwo.ui.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dianshangweektwo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    @BindView(R.id.my_image)
    ImageView myImage;
    @BindView(R.id.my_text)
    TextView myText;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.my_text)
    public void onViewClicked() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(myImage, "rotationX", 0, 180);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(2000);
        animator.start();

        AnimatorSet animatorSetsuofang = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(myImage, "scaleX", 1, 2);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(myImage, "scaleY", 1, 2);

        animatorSetsuofang.setDuration(4000);
        animatorSetsuofang.setInterpolator(new DecelerateInterpolator());
        animatorSetsuofang.play(scaleX).with(scaleY);//两个动画同时开始
        animatorSetsuofang.start();
    }
}
