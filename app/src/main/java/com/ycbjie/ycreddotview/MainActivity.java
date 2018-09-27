package com.ycbjie.ycreddotview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ycbjie.ycreddotviewlib.YCRedDotView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private TextView tv13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String num1 = toFloat(10.00f);
        String num2 = toFloat(10.01f);
        String num3 = toFloat(10.10f);
        Log.e("打印值-------",num1+"----"+num2+"-----"+num3);

        initView();
        initSetView();
    }

    private void initView() {
        TextView tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
        tv5 = findViewById(R.id.tv_5);
        tv6 = findViewById(R.id.tv_6);
        tv7 = findViewById(R.id.tv_7);
        tv8 = findViewById(R.id.tv_8);
        tv9 = findViewById(R.id.tv_9);
        tv10 = findViewById(R.id.tv_10);
        tv11 = findViewById(R.id.tv_11);
        tv12 = findViewById(R.id.tv_12);
        tv13 = findViewById(R.id.tv_13);

        YCRedDotView ycRedDotView = new YCRedDotView(this);
        ycRedDotView.setTargetView(tv1);
        ycRedDotView.setBadgeCount(10);
        ycRedDotView.setRedHotViewGravity(Gravity.RIGHT);


        YCRedDotView ycRedDotView2 = new YCRedDotView(this);
        ycRedDotView2.setTargetView(tv2);
        ycRedDotView2.setBadgeCount(1);
        ycRedDotView2.setRedHotViewGravity(Gravity.RIGHT);
    }


    private void initSetView() {
        mode1();
        mode2();
        mode3();
        mode4();
        mode5();
        mode6();
        mode8();
        mode9();
        mode10();
        mode11();
        mode12();
        mode13();
    }


    public String toFloat(float num){
        String backNum = "";
        String strNum = String.valueOf(num);
        Log.e("打印值fNum----","------------------"+strNum);
        if(strNum.contains(".")){
            String[] split = strNum.split("\\.");
            String s1 = split[0];
            String s2 = split[1];
            int i = Integer.parseInt(s2);
            if(i>0){
                StringBuffer sb1 = new StringBuffer();
                sb1.append(s1);
                sb1.append(".");
                sb1.append(s2);
                float fNum = Float.parseFloat(sb1.toString());
                //构造方法的字符格式这里如果小数不足2位,会以0补足.
                DecimalFormat decimalFormat=new DecimalFormat(".00");
                //format 返回的是字符串
                backNum = decimalFormat.format(fNum);
            }else {
                StringBuffer sb2 = new StringBuffer();
                sb2.append(s1);
                //sb2.append(".");
                //sb2.append("00");
                backNum = sb2.toString();
            }
        }
        return backNum;
    }

    /**
     * 使用SpannableString设置样式——字体颜色
     */
    private void mode1() {
        SpannableString spannableString = new SpannableString("暗影IV已经开始暴走了");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv2.setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——字体颜色
     */
    private void mode2() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV");
        spannableString.append("已经开始暴走了");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv3.setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——背景颜色
     */
    private void mode3() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(bgColorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv4.setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——字体大小
     */
    private void mode4() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(20);
        spannableString.setSpan(absoluteSizeSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv5.setText(spannableString);
    }
    /**
     * 使用SpannableStringBuilder设置样式——粗体\斜体
     */
    private void mode5() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        //setSpan可多次使用
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);//粗体
        spannableString.setSpan(styleSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.ITALIC);//斜体
        spannableString.setSpan(styleSpan2, 3, 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        StyleSpan styleSpan3 = new StyleSpan(Typeface.BOLD_ITALIC);//粗斜体
        spannableString.setSpan(styleSpan3, 6, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv6.setText(spannableString);
    }


    /**
     * 使用SpannableStringBuilder设置点击事件
     */
    private void mode6() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "请不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv7.setText(spannableString);
        tv7.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 使用SpannableStringBuilder设置样式——图片
     */
    private void mode8() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher);
        //也可以这样
        //Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        //drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //ImageSpan imageSpan1 = new ImageSpan(drawable);
        //将index为6、7的字符用图片替代
        spannableString.setSpan(imageSpan, 6, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv8.setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——下划线
     */
    private void mode9() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv9.setText(spannableString);
    }


    private void mode10() {
        String name = "name";
        String title = "title";
        SpannableString spannableString= new SpannableString(name+title);
        spannableString.setSpan(new RoundBackgroundColorSpan(
                Color.parseColor("#12DBD1"),Color.parseColor("#FFFFFF")),
                0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv10.setText(spannableString);
    }


    private void mode11() {
        String[] tags = {"精华", "活动","推荐"};

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        for (String tag : tags) {
            String thisTag = " " + tag + " ";
            stringBuilder.append(thisTag);
            RoundedBackgroundSpan span;
            if("活动".equals(tag)){
                span= new RoundedBackgroundSpan(ContextCompat.getColor(this,R.color.colorAccent), Color.WHITE);
            }else if ("推荐".equals(tag)){
                span= new RoundedBackgroundSpan(ContextCompat.getColor(this,R.color.colorPrimary), Color.WHITE);
            }else{
                span= new RoundedBackgroundSpan(ContextCompat.getColor(this,R.color.colorPrimaryDark), Color.WHITE);
            }
            stringBuilder.setSpan(span, stringBuilder.length() - thisTag.length(), stringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.append(" ");
        }
        stringBuilder.append("王宝强凌晨宣布离婚，妻子劈腿经纪人。");
        tv11.setText(stringBuilder);
    }

    private void mode12() {
        SpannableStringBuilder string = SpannableUtils.appendString(
                this, "名字", "放假啊打扫房间");
        tv12.setText(string);
    }


    private void mode13() {
        SpannableStringBuilder string = appendString(
                this, "名字", "放假啊打扫房间放假啊打扫房间放假啊打扫房间");
        tv13.setText(string);
    }



    /**
     * 字符串拼接
     * @param context                   上下文
     * @param beforeText                前面标签文件
     * @param afterText                 后面内容
     * @return
     */
    public SpannableStringBuilder appendString(
            Context context , String beforeText , String afterText){
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        String thisTag = " " + beforeText + " ";
        stringBuilder.append(thisTag);
        stringBuilder.append("  ");
        stringBuilder.append(afterText);
        RoundedBackgroundSpan span;

        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.8f);
        stringBuilder.setSpan(sizeSpan,0, thisTag.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span= new RoundedBackgroundSpan(ContextCompat.getColor(context,
                R.color.color_ffe935), R.color.colorAccent);
        stringBuilder.setSpan(span, 0, thisTag.length(), Spanned.SPAN_USER_SHIFT);

        stringBuilder.append(" ");
        return stringBuilder;
    }

}
