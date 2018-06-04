package com.bit.whdalive.demoviewpager.Banner;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class BannerPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        int width = page.getWidth();

        if (position < -1) {
            page.setScrollX((int) (width * 0.75 * -1));
        } else if (position <= 1) {
            page.setScrollX((int) (width * 0.75 * position));
        } else {
            page.setScrollX((int) (width * 0.75));
        }
    }
}
