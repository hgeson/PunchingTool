package com.hjq.punching.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hjq.punching.MyApplication;
import com.hjq.punching.weight.Config;
import com.hjq.punching.weight.view.MyDateView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * @Describe：
 * @Date：2019-04-02
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(setLayout());
        ButterKnife.bind(this);
        initView();
        setListener();
        EventBus.getDefault().register(this);
    }

    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void setListener();


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvent event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public String getPunch_record() {
        return MyApplication.getSp().getString(Config.PUNCH_RECORD, "");
    }

    public String getPunch_detail(String name) {
        return MyApplication.getSp().getString(name, "");
    }
}
