package com.ycbjie.ycreddotview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ycbjie.ycreddotviewlib.YCRedDotView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String num1 = toFloat(10.00f);
        String num2 = toFloat(10.01f);
        String num3 = toFloat(10.10f);
        Log.e("打印值-------",num1+"----"+num2+"-----"+num3);

        initView();
    }

    private void initView() {
        TextView tv1 = findViewById(R.id.tv_1);
        YCRedDotView ycRedDotView = new YCRedDotView(this);
        ycRedDotView.setTargetView(tv1);
        ycRedDotView.setBadgeCount(10);
        ycRedDotView.setRedHotViewGravity(Gravity.RIGHT);
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

}
