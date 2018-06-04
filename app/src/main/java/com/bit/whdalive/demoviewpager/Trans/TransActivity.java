package com.bit.whdalive.demoviewpager.Trans;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bit.whdalive.demoviewpager.R;
import com.bit.whdalive.demoviewpager.Trans.PageTransformer.*;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TransActivity extends AppCompatActivity {


    private int[] img;
    private ViewPager mViewPager;
    private List<ImageView> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        img = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.e, R.drawable.f};

        init();
        mViewPager = findViewById(R.id.view_pager_trans);
        mViewPager.setPageMargin(20);//设置page间距
        mViewPager.setOffscreenPageLimit(3);//设置缓存页面数量

        //mViewPager.setPageTransformer(true, new DepthPageTransformer());
        //mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        //mViewPager.setPageTransformer(true,new AlphaPageTransformer());
        mViewPager.setPageTransformer(true,new RotateDownPageTransformer());


        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return img.length;
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
    }

    private void init() {
        for (int imgid : img) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setImageResource(imgid);
            Glide.with(getApplicationContext()).load(imgid).into(imageView);
            mList.add(imageView);
        }
    }
}
