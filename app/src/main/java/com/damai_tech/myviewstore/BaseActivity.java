package com.damai_tech.myviewstore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

/**
 * Created by Administrator on 2017/2/15.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBack.attach(this, Position.LEFT).setContentView(getContentViewId()).setSwipeBackView(R.layout.swipeback_default);
    }

    protected abstract int getContentViewId();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.swipeback_stack_to_front,R.anim.swipeback_stack_right_out);
    }
}
