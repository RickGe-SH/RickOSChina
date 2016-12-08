package com.itheima.rickoschina.main.nav;

import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.view.View;

import com.itheima.rickoschina.R;
import com.itheima.rickoschina.base.BaseFragment;
import com.itheima.rickoschina.main.tabs.DynamicFragment;
import com.itheima.rickoschina.main.tabs.ExploreFragment;
import com.itheima.rickoschina.main.tabs.TweetViewPagerFragment;
import com.itheima.rickoschina.main.tabs.UserInfoFragment;

import net.qiujuer.genius.ui.drawable.shape.BorderShape;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Rick Ge on 2016/12/6.
 */

public class NavFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.nav_item_news)
    NavigationButton mNavNews;
    @Bind(R.id.nav_item_tweet)
    NavigationButton mNavTweet;
    @Bind(R.id.nav_item_explore)
    NavigationButton mNavExplore;
    @Bind(R.id.nav_item_me)
    NavigationButton mNavMe;

    private NavigationButton mCurrentNavButton;
    private FragmentManager mFragmentManager;
    private Context mContext;
    private int mContainerId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        ShapeDrawable lineDrawable = new ShapeDrawable(new BorderShape(new RectF(0, 1, 0, 0)));
        lineDrawable.getPaint().setColor(getResources().getColor(R.color.list_divider_color));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{
                new ColorDrawable(getResources().getColor(R.color.white)),
                lineDrawable
        });
        root.setBackground(layerDrawable);

        mNavNews.init(R.drawable.tab_icon_news, R.string.main_tab_name_news, DynamicFragment.class);
        mNavTweet.init(R.drawable.tab_icon_tweet, R.string.main_tab_name_tweet, TweetViewPagerFragment.class);
        mNavExplore.init(R.drawable.tab_icon_discover, R.string.main_tab_name_explore, ExploreFragment.class);
        mNavMe.init(R.drawable.tab_icon_my, R.string.main_tab_name_my, UserInfoFragment.class);
    }
    
    @OnClick({R.id.nav_item_news, R.id.nav_item_tweet, R.id.nav_item_explore, R.id.nav_item_me})

    @Override
    public void onClick(View v) {
        if(v instanceof NavigationButton){
            NavigationButton nav = (NavigationButton) v;
            doSelect(nav);
        }
    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(mNavNews);
    }

    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if(transaction == null || fragments == null || fragments.size() == 0){
            return;
        }

        boolean doCommit = false;
        for(Fragment fragment : fragments){
            if(fragment != this){
                transaction.remove(fragment);
                doCommit = true;
            }
        }

        if(doCommit){
            transaction.commitNow();
        }
    }

    private void doSelect(NavigationButton newNavButton) {
        NavigationButton oldNavButton = null;
        if(mCurrentNavButton != null){
            oldNavButton = mCurrentNavButton;
            if(oldNavButton == newNavButton){
                return;
            }
            oldNavButton.setSelected(false);
        }

        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if(oldNavButton != null){
            if(oldNavButton.getFragment() != null){
                ft.detach(oldNavButton.getFragment());
            }
        }
        if(newNavButton != null){
            if(newNavButton.getFragment() == null){
                Fragment fragment = Fragment.instantiate(mContext, newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            }
            else{
                ft.attach(newNavButton.getFragment());
            }
        }

        ft.commit();
    }

}
