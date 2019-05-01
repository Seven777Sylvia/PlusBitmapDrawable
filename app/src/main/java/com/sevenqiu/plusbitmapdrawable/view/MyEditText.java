package com.sevenqiu.plusbitmapdrawable.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.sevenqiu.plusbitmapdrawable.util.Util;


/**
 * @author Seven Qiu
 * @date 2019-04-29
 */
public class MyEditText extends AppCompatEditText {
    private static final String TAG = MyEditText.class.getSimpleName();

    private static final float TEXT_SIZE = Util.dip2Pixl(12);
    private static final float TEXT_MARGIN = Util.dip2Pixl(8);
    private static final float VERTICAL_OFFSET = Util.dip2Pixl(38);
    private static final float HORIZONTAL_OFFSET = Util.dip2Pixl(5);
    private static final float VERTICAL_OFFSET_EXTRA = Util.dip2Pixl(16);

    private Paint mPaint;
    private Rect mBounds = new Rect();
    private ObjectAnimator tipAni;
    private boolean mHasText;
    private float fraction;

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(TEXT_SIZE);
        mPaint.setColor(Color.MAGENTA);

        getBackground().getPadding(mBounds);
        setPadding(mBounds.left, (int) (mBounds.top + TEXT_SIZE + TEXT_MARGIN), mBounds.right, mBounds.bottom);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && !mHasText) {
                    mHasText = true;
                    getTextAnimation().start();
                } else if (TextUtils.isEmpty(s) && mHasText) {
                    mHasText = false;
                    getTextAnimation().reverse();
                }
            }
        });
    }

    private ObjectAnimator getTextAnimation() {
        if (null == tipAni) {
            tipAni = ObjectAnimator.ofFloat(MyEditText.this, "fraction", 0, 1);
        }
        //   tipAni.setDuration(2000);
        return tipAni;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw:  fraction: " + fraction + " ,vertical: " + (VERTICAL_OFFSET - fraction * VERTICAL_OFFSET_EXTRA));
        mPaint.setAlpha((int) (fraction * 0xff));
        canvas.drawText(getHint().toString(), HORIZONTAL_OFFSET,
                VERTICAL_OFFSET - fraction * VERTICAL_OFFSET_EXTRA, mPaint);

    }

    private float getFraction() {
        return fraction;
    }

    private void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }

}
