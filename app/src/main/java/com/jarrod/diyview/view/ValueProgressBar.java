package com.jarrod.diyview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

import com.jarrod.diyview.R;

/**
 * Created by jarrod on 2016/10/20.
 */

public class ValueProgressBar extends ProgressBar {
    private String text;
    private Paint mPaint;
    private Rect mRect;

    public ValueProgressBar(Context context) {
        super(context, null);
    }

    public ValueProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ValueProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ValueProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ValueProgressBar);
        int textColor = a.getColor(R.styleable.ValueProgressBar_textColor, 0X00000000);
        float textSize = a.getDimension(R.styleable.ValueProgressBar_textSize, 36);
        this.mPaint = new Paint();
        this.mPaint.setTextSize(textSize);
        this.mPaint.setColor(textColor);
        a.recycle();
        mRect = new Rect();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("ValueProgressBar", this.text);
        if (mRect == null) {
            mRect = new Rect();
        }
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), mRect);
        int realWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int x = getProgress() * realWidth / getMax() + getPaddingLeft();      //这里的数值在进度后面
        if ((realWidth + getPaddingLeft() - x) < mRect.width()) {
            x = realWidth - mRect.width() + getPaddingLeft();
        }
        int y = (getHeight() / 2) - mRect.centerY();
//        int x = (getWidth() / 2) - rect.centerX();        //这里的数值在进度调中间
        canvas.drawText(this.text, x, y, this.mPaint);
    }

    @Override
    public synchronized void setProgress(int progress) {
        setText(progress);
        Log.i("ValueProgressBar", progress + "");
        super.setProgress(progress);
    }


    //设置文字内容
    private void setText(int progress){
        int i = (progress * 100)/this.getMax();
        this.text = String.valueOf(i) + "%";
    }
}
