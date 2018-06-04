package com.bit.whdalive.demoviewpager.Banner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bit.whdalive.demoviewpager.R;
import com.bit.whdalive.demoviewpager.Trans.PageTransformer.*;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

    private static final int MSG_WHAT = 0;

    private int[] imgs;
    private ViewPager mViewPager;
    private List<ImageView> mList = new ArrayList<>();
    private String[] titles;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);//无限轮播时
            mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % mList.size());
            this.sendEmptyMessageDelayed(MSG_WHAT, 2000);
        }
    };
    private LinearLayout mLinearLayout;
    private ArrayList<ImageView> dotsList;

    private TextView bannerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        imgs = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.e, R.drawable.f};
        titles = new String[]{"To think as great minds, to do as idiots","One Step Closer To The Hell","Knowing Everything of Something","Nothing For Nothing","No Royal Road To Anything"};
        bannerTitle = findViewById(R.id.banner_title);
        mLinearLayout = findViewById(R.id.indicator);
        init();
        initDots();

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(3);//设置缓存页面数量

        mViewPager.setPageTransformer(true,new DepthPageTransformer());
        //mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        //mViewPager.setPageTransformer(true,new AlphaPageTransformer());
        //mViewPager.setPageTransformer(true,new RotateDownPageTransformer());
        mViewPager.setPageTransformer(true, new BannerPageTransformer());


        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mList.get(position));
                return mList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(mList.get(position));
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsList.size(); i++) {
                    if (position % dotsList.size() == i) {
                        dotsList.get(i).setImageResource(R.drawable.indicator_focus);

                    } else {
                        dotsList.get(i).setImageResource(R.drawable.indicator_normal);
                    }
                }
                bannerTitle.setText(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mHandler.sendEmptyMessageDelayed(MSG_WHAT, 2000);
    }

    private void init() {
        for (int img : imgs) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setImageResource(imgid);
            Glide.with(getApplicationContext()).load(img).into(imageView);
            mList.add(imageView);
        }
    }

    private void initDots() {
        dotsList = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            if (i == 0) {
                imageView.setImageResource(R.drawable.indicator_focus);
            } else {
                imageView.setImageResource(R.drawable.indicator_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);

            params.setMargins(5, 0, 5, 0);
            mLinearLayout.addView(imageView, params);
            dotsList.add(imageView);
        }
    }
}
