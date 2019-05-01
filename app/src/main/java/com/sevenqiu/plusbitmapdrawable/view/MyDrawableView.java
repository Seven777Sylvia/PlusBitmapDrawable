package com.sevenqiu.plusbitmapdrawable.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Seven Qiu
 * @date 2019-04-29
 */
public class MyDrawableView extends View {
    private MyDrawable mMyDrawable;

    public MyDrawableView(Context context) {
        super(context);
    }

    public MyDrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mMyDrawable = new MyDrawable();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mMyDrawable.setBounds(200, 200, getWidth(), getHeight());
        mMyDrawable.draw(canvas);
    }
}
