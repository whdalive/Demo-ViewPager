package com.bit.whdalive.demoviewpager.Welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bit.whdalive.demoviewpager.MainActivity;
import com.bit.whdalive.demoviewpager.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private AppCompatButton btn_got;
    private AppCompatButton btn_skip;
    private LinearLayout mLinearLayout;
    private ArrayList<ImageView> dotsList;

    private int[] imgs;
    private List<ImageView> mList = new ArrayList<>();

    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        imgs = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.e, R.drawable.f};
        if (mPreferences.getBoolean("FirstLaunch", true)) {
            setContentView(R.layout.activity_welcome);
            mLinearLayout = findViewById(R.id.indicator_welcome);
            initView();
            initDots();
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
                    btn_got.setVisibility(position == mList.size()-1?View.VISIBLE:View.GONE);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            btn_got.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recordFirstLaunch();
                    notFirstLaunch();
                }
            });
            btn_skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recordFirstLaunch();
                    notFirstLaunch();
                }
            });

        } else {
            notFirstLaunch();
            finish();
        }
    }

    private void recordFirstLaunch() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("FirstLaunch", false);
        editor.apply();
        notFirstLaunch();
    }

    private void notFirstLaunch() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        btn_got = findViewById(R.id.btn_got);
        btn_skip = findViewById(R.id.skip);
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
