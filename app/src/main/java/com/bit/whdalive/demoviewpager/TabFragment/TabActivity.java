package com.bit.whdalive.demoviewpager.TabFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bit.whdalive.demoviewpager.R;

public class TabActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabfragment);

        mViewPager = findViewById(R.id.view_pager_tab);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            private String[] titles = new String[]{"Deemo", "Cytus", "兰空", "万向物语", "绝地求生", "魔女之泉"};

            @Override
            public Fragment getItem(int position) {
                return PageFragment.newinstance(position);
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //设置标签摆放方式
        //默认为MODE_FIXED，固定模式
        //mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        //滑动模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
