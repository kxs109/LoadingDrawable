package com.kxs.mydrawableview;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/80/31.
 */

public class MyDrawableRender2 extends MyRender {
    private Paint mPaint;
    public static final int ALPHA = 255;
    int[] alphas = new int[]{ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA};
    private int x;
    private int padding;

    public MyDrawableRender2(Paint paint) {
        this.mPaint = paint;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        x = (drawBounds.width() / 2);
        padding = x / 10;
    }

    public ArrayList<ValueAnimator> setupAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        int[] delays = {0, 87 * 1, 87 * 2,
                87 * 3, 87 * 4, 87 * 5, 87 * 6, 87 * 7,
                87 * 8, 87 * 9, 87 * 10, 87 * 11
        };
        for (int i = 0; i < 12; i++) {
            final int index = i;
            ValueAnimator alphaAnim = ValueAnimator.ofInt(30, 220);
            alphaAnim.setDuration(87 * 12);
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay(delays[i]);
            addUpdateListener(alphaAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    alphas[index] = (int) animation.getAnimatedValue();
                    invalidateSelf();
                }
            });
            animators.add(alphaAnim);
        }
        return animators;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        float x = (drawBounds.width() / 2);
        float y = (drawBounds.height() / 2);
        mPaint.setStrokeWidth((x - padding) / 12);
        canvas.save();
        canvas.translate(x, y);
        for (int i = 0; i < 12; i++) {
            mPaint.setAlpha(alphas[i]);
            canvas.drawLine((x - padding) / 3, 0, (x - padding) * 2 / 3, 0, mPaint);
            canvas.rotate(30);
        }
        canvas.restore();
    }
}
