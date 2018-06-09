package com.ycbjie.ycreddotviewlib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabWidget;


public class YCRedDotView extends AppCompatTextView {

    /**
     * 是否隐藏
     */
    private boolean mHideOnNull = true;

    public YCRedDotView(Context context) {
        this(context, null);
    }

    public YCRedDotView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public YCRedDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    /**
     * 重新父类setText方法
     * @param text                  text
     * @param type                  type
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isHideOnNull() && (text == null || text.toString().equalsIgnoreCase("0"))) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
        super.setText(text, type);
    }


    /**
     * 初始化view
     * 1.设置布局属性
     */
    private void initView() {
        setLayoutParams();
        setTextView();
        setDefaultValues();
    }

    private void setLayoutParams() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if(!(layoutParams instanceof LinearLayout.LayoutParams)){
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.END | Gravity.TOP);
            setLayoutParams(lParams);
        }
    }


    private void setTextView() {
        setTextColor(Color.WHITE);
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        setPadding(dip2Px(5), dip2Px(1), dip2Px(5), dip2Px(1));
        setBackground(9, Color.parseColor("#f14850"));
        setGravity(Gravity.CENTER);
    }


    private void setDefaultValues() {
        setHideOnNull(true);
        setBadgeCount(0);
    }

    /**
     * 设置背景颜色
     * @param dipRadius                 半径
     * @param badgeColor                颜色
     */
    public void setBackground(int dipRadius, int badgeColor) {
        int radius = dip2Px(dipRadius);
        float[] radiusArray = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        RoundRectShape roundRect = new RoundRectShape(radiusArray, null, null);
        ShapeDrawable bgDrawable = new ShapeDrawable(roundRect);
        bgDrawable.getPaint().setColor(badgeColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(bgDrawable);
        }
    }

    public void setHideOnNull(boolean hideOnNull) {
        mHideOnNull = hideOnNull;
        setText(getText());
    }

    public boolean isHideOnNull() {
        return mHideOnNull;
    }

    /**
     * 设置红点的数字
     * @param count                 数字
     */
    public void setBadgeCount(int count) {
        setBadgeCount(count > 99 ? "99+" : String.valueOf(count));
    }

    private void setBadgeCount(String count) {
        setText(count);
    }

    public void setTargetView(TabWidget target, int tabIndex) {
        View tabView = target.getChildTabViewAt(tabIndex);
        setTargetView(tabView);
    }

    /**
     * 设置支持tabLayout控件
     * @param target                TabLayout
     * @param tabIndex              索引
     */
    public void setTargetView(TabLayout target, int tabIndex) {
        TabLayout.Tab tabAt = target.getTabAt(tabIndex);
        View customView = null;
        if (tabAt != null) {
            customView = tabAt.getCustomView();
        }
        setTargetView(customView);
    }

    /**
     * 设置红点依附的view
     * @param view
     */
    public void setTargetView(View view){
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (view == null) {
            return;
        }
        if(view.getParent() instanceof FrameLayout){
            ((FrameLayout) view.getParent()).addView(this);
        }else if(view.getParent() instanceof ViewGroup){
            ViewGroup parentContainer = (ViewGroup) view.getParent();
            int groupIndex = parentContainer.indexOfChild(view);
            parentContainer.removeView(view);

            FrameLayout badgeContainer = new FrameLayout(getContext());
            ViewGroup.LayoutParams parentLayoutParams = view.getLayoutParams();
            badgeContainer.setLayoutParams(parentLayoutParams);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            parentContainer.addView(badgeContainer, groupIndex, parentLayoutParams);
            badgeContainer.addView(view);
            badgeContainer.addView(this);
        }else {
            Log.e(getClass().getSimpleName(), "ParentView is must needed");
        }
    }

    /**
     * 设置红点位置
     * @param gravity               位置
     */
    public void setRedHotViewGravity(int gravity){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.gravity = gravity;
        setLayoutParams(params);
    }


    /**
     * 设置红点属性
     * @param dipMargin             margin值
     */
    public void setBadgeMargin(int dipMargin) {
        setBadgeMargin(dipMargin, dipMargin, dipMargin, dipMargin);
    }


    public void setBadgeMargin(int leftDipMargin, int topDipMargin, int rightDipMargin, int bottomDipMargin) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.leftMargin = dip2Px(leftDipMargin);
        params.topMargin = dip2Px(topDipMargin);
        params.rightMargin = dip2Px(rightDipMargin);
        params.bottomMargin = dip2Px(bottomDipMargin);
        setLayoutParams(params);
    }


    public int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

}
