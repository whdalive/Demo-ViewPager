package com.bit.whdalive.demoviewpager.Trans.PageTransformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateDownPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float maxRotate = DEFAULT_MAX_ROTATE;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) {
            page.setRotation(maxRotate * -1);
            page.setPivotX(page.getWidth());
            page.setPivotY(page.getHeight());
        } else if (position <= 1) {
            if (position < 0) {
                page.setPivotX((float) (page.getWidth() * (0.5 + 0.5f * (-position))));
                page.setPivotY(page.getHeight());
                page.setRotation(position * maxRotate);
            } else {
                page.setPivotX(page.getWidth() * 0.5f * (1 - position));
                page.setPivotY(page.getHeight());
                page.setRotation(maxRotate * position);
            }
        } else {
            page.setRotation(maxRotate);
            page.setPivotX(page.getWidth() * 0);
            page.setPivotY(page.getHeight());
        }
    }
}
