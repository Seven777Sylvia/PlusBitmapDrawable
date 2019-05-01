package com.sevenqiu.plusbitmapdrawable.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sevenqiu.plusbitmapdrawable.util.Util;

/**
 * @author Seven Qiu
 * @date 2019-04-29
 */
public class MyDrawable extends Drawable {

    private static final int INTERVAL = (int) Util.dip2Pixl(80);
    private Paint mPaint;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(Util.dip2Pixl(3));
        mPaint.setColor(Color.LTGRAY);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        for (int i = bounds.top; i < bounds.bottom; i += INTERVAL) {
            canvas.drawLine(bounds.left, i, bounds.right, i, mPaint);
        }

        for (int i = bounds.left; i < bounds.right; i += INTERVAL) {
            canvas.drawLine(i, bounds.top, i, bounds.bottom, mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return mPaint.getAlpha() == 0xff ? PixelFormat.OPAQUE :
                mPaint.getAlpha() == 0 ? PixelFormat.TRANSPARENT :
                        PixelFormat.TRANSLUCENT;
    }

}
