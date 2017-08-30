package com.yanlongrivenk.yltextview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by yanlongRivenK on 2017/8/27.
 */

public class TextViewYL extends AppCompatTextView {
    protected Paint mOriginPaint;
    protected Paint mChangePaint;

    private float percent;
    private int mOriginTvColor = Color.BLACK;
    private int mChangeTvColor = Color.BLUE;
    private Directionn mDirectionn = Directionn.DIRECTION_LEFT;

    private String mString = "TrackTextView";
    protected float mChangePoint;
    protected int textX;
    protected float baseLine;

    public int getOriginTvColor() {
        return mOriginTvColor;
    }

    public TextViewYL setOriginTvColor(int originTvColor) {
        mOriginTvColor = originTvColor;
        return this;
    }

    public int getChangeTvColor() {
        return mChangeTvColor;
    }

    public TextViewYL setChangeTvColor(int changeTvColor) {
        mChangeTvColor = changeTvColor;
        return this;
    }

    public Directionn getDirectionn() {
        return mDirectionn;
    }

    public TextViewYL setDirectionn(Directionn directionn) {
        mDirectionn = directionn;
        return this;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }


    public enum Directionn {
        DIRECTION_LEFT(0), DIRECTION_RIGHT(1);

        int id;

        Directionn(int id){
            this.id = id;
        }

        static Directionn parseId(int id){
            for (Directionn directionn : values()) {
                if (directionn.id == id) return directionn;
            }

            throw new IllegalStateException("冒得勒个枚举...check it");
        }


    }

    public TextViewYL(Context context) {
        this(context, null);
    }

    public TextViewYL(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewYL(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取xml中的自定义配置
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewYL);

        if (typedArray != null) {
            percent = typedArray.getFloat(R.styleable.TextViewYL_percent, 0);
            mChangeTvColor = typedArray.getColor(R.styleable.TextViewYL_changeTvColor, mChangeTvColor);
            mOriginTvColor = typedArray.getColor(R.styleable.TextViewYL_originTvColor, mOriginTvColor);
            mDirectionn = Directionn.parseId(typedArray.getInteger(R.styleable.TextViewYL_Directionn, 0));
        }
        initPaint();
        mString = getText().toString();
    }

    private void initPaint() {
        mOriginPaint = getPaintByColor(mOriginTvColor);
        mChangePaint = getPaintByColor(mChangeTvColor);
    }

    private Paint getPaintByColor(int tvColor) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        paint.setColor(tvColor);

        return paint;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //判断方向
        switch (mDirectionn) {
            case DIRECTION_LEFT:
                mChangePoint = getWidth() * percent;
                break;
            case DIRECTION_RIGHT:
                mChangePoint = (1 - percent) * getWidth();
                break;
        }

        drawText(canvas);


    }

    private void drawText(Canvas canvas) {

        canvas.save();
        canvas.clipRect(0, 0, mChangePoint, getHeight());
        Rect bound = new Rect();
        mOriginPaint.getTextBounds(mString, 0, mString.length(), bound);
        textX = (getWidth() - bound.width()) / 2;
        Paint.FontMetrics fontMetrics = mOriginPaint.getFontMetrics();
        baseLine = (getHeight() + (fontMetrics.bottom - fontMetrics.top)) / 2 - fontMetrics.bottom;
        canvas.drawText(mString, textX, baseLine, mChangePaint);
        canvas.restore();

        canvas.clipRect(mChangePoint, 0, getWidth(), getHeight());
        canvas.drawText(mString, textX, baseLine, mOriginPaint);

    }

    //执行动画
    public void startAnimator(int time) {
        ValueAnimator animator = ValueAnimator.ofInt(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fra = valueAnimator.getAnimatedFraction();
                setPercent(fra);
            }
        });
        animator.setDuration(time);
        animator.start();
    }
}
