package com.kxs.mydrawableview;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class MyDrawableRender0 extends MyRender {
    private float scaleFloat;
    private float degrees;
    private Paint mPaint;
    private float padding;
    private float x;
    private float y;

    public MyDrawableRender0(Paint paint) {
        this.mPaint = paint;
    }

    protected List<ValueAnimator> setupAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.5f, 1);
        scaleAnim.setDuration(1200);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleFloat = (float) animation.getAnimatedValue();
                invalidateSelf();
            }
        });
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(0, 180, 360);
        rotateAnim.setDuration(1200);
        rotateAnim.setRepeatCount(-1);
        addUpdateListener(rotateAnim, new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (float) animation.getAnimatedValue();
                invalidateSelf();
            }
        });
        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        x = (drawBounds.width() / 2);
        y = (drawBounds.height() / 2);
        padding = x/10;
    }
    @Override
    public void draw(@NonNull Canvas canvas) {

        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(degrees);
        RectF rectF = new RectF(-x + padding, -y + padding, 0 + x - padding, 0 + y - padding);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, 0, 70, false, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 120, 70, false, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF, 240, 70, false, mPaint);

        canvas.restore();
    }
}
