package com.itheima.rickoschina.base;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewStub;

import com.itheima.rickoschina.R;
import com.itheima.rickoschina.widget.TitleBar;

/**
 * Created by Rick Ge on 2016/12/7.
 */

public abstract class BaseTitleFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_title;
    }

    @Override
    protected void onBindViewBefore(View root) {
        super.onBindViewBefore(root);
        ViewStub viewStub = (ViewStub) root.findViewById(R.id.lay_content);
        viewStub.setLayoutResource(getContentLayoutId());
        viewStub.inflate();
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        TitleBar titleBar = (TitleBar) root.findViewById(R.id.nav_title_bar);
        titleBar.setTitle(getTitleRes());
        titleBar.setIcon(getIconRes());
        titleBar.setIconOnClickListener(getIconOnClickListener());
    }

    protected View.OnClickListener getIconOnClickListener() {
        return null;
    }

    protected @DrawableRes int getIconRes() {
        return 0;
    }

    protected abstract @StringRes int getTitleRes();

    protected abstract @LayoutRes int getContentLayoutId();
}
