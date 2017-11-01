package com.kxs.mydrawableview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/31.
 */

public class MyDrawableView extends android.support.v7.widget.AppCompatImageView {

    private MyRender mRender;
    private Paint mPaint;

    public MyDrawableView(Context context) {
        super(context);
    }

    public MyDrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAttrs(context, attrs);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(12);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyDrawableView);
        int anInt = ta.getInt(R.styleable.MyDrawableView_mydrawable, 0);
        setDrawable(anInt);
        ta.recycle();
    }

    public void setDrawable(int anInt) {
        if (anInt == 0) {
            mRender = new MyDrawableRender0(mPaint);
            setImageDrawable(mRender);
        }else if (anInt == 1){
            mRender = new MyDrawableRender1(mPaint);
            setImageDrawable(mRender);
        }else if (anInt == 2){
            mRender = new MyDrawableRender2(mPaint);
            setImageDrawable(mRender);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mRender.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRender.stop();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE) {
            mRender.start();
        } else if (visibility == View.INVISIBLE) {
            mRender.stop();
        }
    }

}
