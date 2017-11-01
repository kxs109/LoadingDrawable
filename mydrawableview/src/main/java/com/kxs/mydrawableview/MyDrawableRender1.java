package com.kxs.mydrawableview;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/30/31.
 */

public class MyDrawableRender1 extends MyRender {
    private Paint mPaint;
    public static final float SCALE = 1.0f;
    float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};

    private int x;
    private int padding;

    public MyDrawableRender1(Paint paint) {
        this.mPaint = paint;
    }

    public ArrayList<ValueAnimator> setupAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        int[] delays = {5 * 1, 5 * 2,
                5 * 3, 5 * 4, 5 * 5, 5 * 6, 5 * 7,
                5 * 8, 5 * 9, 5 * 30, 5 * 11, 5 * 12
        };
        for (int i = 0; i < 12; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.1f, 1);
            scaleAnim.setDuration(190 * 13);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            scaleAnim.setStartDelay(delays[i]);
            addUpdateListener(scaleAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    invalidateSelf();
                }
            });
            animators.add(scaleAnim);
        }
        return animators;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        x = (drawBounds.width() / 2);
        padding = x / 10;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        float x = (drawBounds.width() / 2);
        float y = (drawBounds.height() / 2);
        canvas.save();
        canvas.translate(x, y);
        for (int i = 0; i < 12; i++) {
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            canvas.drawCircle(x - padding * 2, 0, x / 12, mPaint);
            canvas.rotate(30);
        }
        canvas.restore();
    }
}
