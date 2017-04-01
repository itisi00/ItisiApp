package com.itisi.itisiapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.itisi.itisiapp.R;

/**
 * author: itisi---
 * created by Administrator on 2017/3/30.
 * desc:
 */

public class TimeLineMarker extends View {

    private int mMarkerSize=24;
    private int mLineSize=12;
    private Drawable mBeginLine;
    private Drawable mEndLine;
    private Drawable mMarkerDrawable;


    public TimeLineMarker(Context context) {
        this(context,null);
    }

    public TimeLineMarker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimeLineMarker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    /**
     * 初始化属性
     * @param attrs
     */
    private void initAttr(AttributeSet attrs) {
        TypedArray array=getContext().obtainStyledAttributes(attrs, R.styleable.TimeLineMarker,0,0);
        mMarkerSize=array.getDimensionPixelSize(R.styleable.TimeLineMarker_markerSize,mLineSize);
        mLineSize=array.getDimensionPixelSize(R.styleable.TimeLineMarker_lineSize,mLineSize);
        mBeginLine=array.getDrawable(R.styleable.TimeLineMarker_beginLine);
        mEndLine=array.getDrawable(R.styleable.TimeLineMarker_endLine);
        array.recycle();

        if (mBeginLine!=null){
            mBeginLine.setCallback(this);
        }
        if (mEndLine!=null){
            mEndLine.setCallback(this);
        }
        if (mMarkerDrawable!=null){
            mMarkerDrawable.setCallback(this);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initDrawableSize();
    }

    /**
     * 大小与位置初始化
     */
    private void initDrawableSize() {
        int pLeft=getPaddingLeft();
        int pRight=getPaddingRight();
        int pTop=getPaddingTop();
        int pBottom=getPaddingBottom();

        int width=getWidth();
        int height=getHeight();

        int cWidth=width-pLeft-pRight;
        int cHeight=height-pTop-pBottom;
        Rect bounds;
        if (mMarkerDrawable!=null){
            //Size
            int markerSize=Math.min(mMarkerSize,Math.min(cWidth,cHeight));
            mMarkerDrawable.setBounds(pLeft,pTop,pRight+markerSize,pBottom+markerSize);
            bounds=mMarkerDrawable.getBounds();
        }
        else{
            bounds = new Rect(pLeft, pTop, pLeft + cWidth, pTop + cHeight);
        }
        int halfLineSize=mLineSize>>1;//这就搞不懂了 ^_^
        int lineLeft=bounds.centerX()-halfLineSize;

        if (mBeginLine!=null){
            mBeginLine.setBounds(lineLeft, 0, lineLeft + mLineSize, bounds.top);
        }
        if (mEndLine != null) {
            mEndLine.setBounds(lineLeft, bounds.bottom, lineLeft + mLineSize, height);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBeginLine!=null){
            mBeginLine.draw(canvas);
        }
        if (mEndLine!=null){
            mEndLine.draw(canvas);
        }
        if (mMarkerDrawable!=null){
            mMarkerDrawable.draw(canvas);
        }
        super.onDraw(canvas);

    }

    public void setLineSize(int lineSize) {
        if (mLineSize != lineSize) {
            this.mLineSize = lineSize;
            initDrawableSize();
            invalidate();
        }
    }

    public void setMarkerSize(int markerSize) {
        if (this.mMarkerSize != markerSize) {
            mMarkerSize = markerSize;
            initDrawableSize();
            invalidate();
        }
    }

    public void setBeginLine(Drawable beginLine) {
        if (this.mBeginLine != beginLine) {
            this.mBeginLine = beginLine;
            if (mBeginLine != null) {
                mBeginLine.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    public void setEndLine(Drawable endLine) {
        if (this.mEndLine != endLine) {
            this.mEndLine = endLine;
            if (mEndLine != null) {
                mEndLine.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    public void setMarkerDrawable(Drawable markerDrawable) {
        if (this.mMarkerDrawable != markerDrawable) {
            this.mMarkerDrawable = markerDrawable;
            if (mMarkerDrawable != null) {
                mMarkerDrawable.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

}
