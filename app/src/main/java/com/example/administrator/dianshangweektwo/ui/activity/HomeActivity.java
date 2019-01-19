package com.example.administrator.dianshangweektwo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.dianshangweektwo.R;
import com.example.administrator.dianshangweektwo.ui.fragment.MyFragment;
import com.example.administrator.dianshangweektwo.ui.fragment.ShoppingCarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.rad1)
    RadioButton rad1;
    @BindView(R.id.rad2)
    RadioButton rad2;
    @BindView(R.id.rads)
    RadioGroup rads;
    private MyFragment myFragment;
    private ShoppingCarFragment shoppingCarFragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        rads.check(rads.getChildAt(0).getId());

        manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        myFragment = new MyFragment();
        shoppingCarFragment = new ShoppingCarFragment();

        transaction.add(R.id.frame,myFragment);
        transaction.add(R.id.frame,shoppingCarFragment);

        transaction.hide(myFragment).show(shoppingCarFragment);
        transaction.commit();


        rads.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.rad1:
                        fragmentTransaction.hide(myFragment).show(shoppingCarFragment);
                        break;
                    case R.id.rad2:
                        fragmentTransaction.show(myFragment).hide(shoppingCarFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }
}
