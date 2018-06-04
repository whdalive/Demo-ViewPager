package com.bit.whdalive.demoviewpager.Trans.PageTransformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class AlphaPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float minAplha = DEFAULT_MIN_ALPHA;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) {
            page.setAlpha(minAplha);
        } else if (position <= 1) {
            if (position <= 0) {
                float factor = minAplha + (1 - minAplha) * (1 + position);
                page.setAlpha(factor);
            } else {
                float factor = minAplha + (1 - minAplha) * (1 - position);
                page.setAlpha(factor);
            }
        } else {
            page.setAlpha(minAplha);
        }
    }
}
