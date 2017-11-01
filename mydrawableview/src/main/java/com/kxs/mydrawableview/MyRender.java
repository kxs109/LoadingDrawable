package com.kxs.mydrawableview;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */

public abstract class MyRender extends Drawable implements Animatable {
    private List<ValueAnimator> mAnimators;
    private HashMap<ValueAnimator, ValueAnimator.AnimatorUpdateListener> mUpdateListeners = new HashMap<>();
    protected Rect drawBounds = null;

    @Override
    public void start() {
        startAnimators();
    }

    @Override
    public void stop() {
        stopAnimators();
    }

    @Override
    public boolean isRunning() {
        for (ValueAnimator animator : mAnimators) {
            return animator.isRunning();
        }
        return false;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.drawBounds = new Rect(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    private void startAnimators() {
        mAnimators = setupAnimators();
        for (int i = 0; i < mAnimators.size(); i++) {
            ValueAnimator animator = mAnimators.get(i);
            //when the animator restart , add the updateListener again because they
            // was removed by animator stop .
            ValueAnimator.AnimatorUpdateListener updateListener = mUpdateListeners.get(animator);
            if (updateListener != null) {
                animator.addUpdateListener(updateListener);
            }
            animator.start();
        }
    }

    protected abstract List<ValueAnimator> setupAnimators();

    private void stopAnimators() {
        if (mAnimators != null) {
            for (ValueAnimator animator : mAnimators) {
                if (animator != null && animator.isStarted()) {
                    animator.removeAllUpdateListeners();
                    animator.end();
                }
            }
        }
    }

    public void addUpdateListener(ValueAnimator animator, ValueAnimator.AnimatorUpdateListener updateListener) {
        mUpdateListeners.put(animator, updateListener);
    }
}
