package com.bwie.yinzhiyou.yinzhiyou20181221.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class CumotView extends RelativeLayout {
    private int childMaxHeight;
    private int childWidth=20;
    private int childHeight=20;

    public CumotView(Context context) {
        super(context);
    }

    public CumotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int sizeWith = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        findHeightChild();
        int left=0,top=0;
        int childCount = getChildCount();

        for (int i=0;i<childCount;i++) {
            View view = getChildAt(i);
            if (left!=0){
                if((left+view.getMeasuredWidth())>sizeWith){
                    top+=childMaxHeight+childHeight;
                    left=0;
                }
            }
            left+=view.getMeasuredWidth()+childWidth;
        }
        setMeasuredDimension(sizeWith,(top+childMaxHeight)>sizeHeight?sizeHeight:childHeight);
    }

    private void findHeightChild() {
        childMaxHeight=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if (view.getMeasuredHeight()>childMaxHeight){
                childMaxHeight=view.getMeasuredHeight();
            }

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findHeightChild();
        int left=0,top=0;
        int childCount = getChildCount();

        for (int i=0;i<childCount;i++) {
            View view = getChildAt(i);
            if (left!=0){
                if((left+view.getMeasuredWidth())>getWidth()){
                    top+=childMaxHeight+childHeight;
                    left=0;
                }
            }
            view.layout(left,top,left+view.getMeasuredWidth(),top+view.getMeasuredHeight());
            left+=view.getMeasuredWidth()+childMaxHeight;
        }}
}
