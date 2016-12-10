package com.itheima.rickoschina.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itheima.rickoschina.R;

import butterknife.Bind;

/**
 * Created by Rick Ge on 2016/12/10.
 */

public abstract class BaseViewPagerFragment extends BaseTitleFragment {
    @Bind(R.id.tab_nav)
    protected TabLayout mTabNav;
    @Bind(R.id.base_viewPager)
    protected ViewPager mBaseViewPager;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_base_viewpager;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getChildFragmentManager(), getPagers());
        mBaseViewPager.setAdapter(adapter);
        mTabNav.setupWithViewPager(mBaseViewPager);
        mBaseViewPager.setCurrentItem(0,true);
    }

    protected abstract PagerInfo[] getPagers();

    public static class PagerInfo{
        private String title;
        private Class<?> clx;
        private Bundle args;

        public PagerInfo(String title, Class<?> clx, Bundle args) {
            this.title = title;
            this.clx = clx;
            this.args = args;
        }
    }


    public class BaseViewPagerAdapter extends FragmentPagerAdapter{
        private PagerInfo[] mInfoList;
        private Fragment mCurrentFragment;

        public BaseViewPagerAdapter(FragmentManager fm, PagerInfo[] infoList) {
            super(fm);
            this.mInfoList = infoList;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            if (object instanceof Fragment) {
                mCurrentFragment = (Fragment) object;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mInfoList[position].title;
        }

        @Override
        public Fragment getItem(int position) {
            PagerInfo pagerInfo = mInfoList[position];
            Fragment fragment = Fragment.instantiate(getContext(), pagerInfo.clx.getName(), pagerInfo.args);
            Toast.makeText(getContext(), fragment.toString(), Toast.LENGTH_SHORT).show();
            return fragment;
        }

        @Override
        public int getCount() {
            return mInfoList.length;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        public Fragment getCurrentFragment() {
            return mCurrentFragment;
        }
    }


}
