/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.mymvp.view.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * @author xuexiang
 * @date 2018/1/11 上午10:37
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        attachView();
        initArgs();
        initViews();
        initListener();
    }

    protected abstract void attachView();

    /**
     * 初始化参数
     */
    protected void initArgs() {

    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    protected <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    protected void startActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }


    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}