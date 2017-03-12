package com.bsl.yulin.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by bsl52840 on 2017/3/8.
 * 双击按钮
 */

public class DoubleClickButton extends Button {//封装好的双击按钮
    Context context;
    onDoubleClickListener mListener;

    public DoubleClickButton(Context context) {
        super(context);
        this.context = context;
    }

    public DoubleClickButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoubleClickButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    long downTime;//按下时间
    long upTime;//松开时间
    long T;//记录按下和松开的时间差
    long firstTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {//触摸事件管理器
        long currentTime = System.currentTimeMillis();//这里的获取的currentTime时间类似于系统启动时间

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downTime = currentTime;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            upTime = currentTime;
            T = upTime - downTime;
            if (T <= onDoubleClickListener.JUDGE_TIME) {//这里当T的值大于500时这次点击就是无效的
                JudgeClick(upTime);//这是一次有效的点击，进入判断点击动作的方法判断
            }
        }

        return super.onTouchEvent(event);
    }

    private void JudgeClick(long upTime) {//判断点击动作的方法
        if (upTime - firstTime < onDoubleClickListener.JUDGE_TIME) {
            //松开的时间减去 firstTime = 0； 的时间差如果小于500说明这是一次有效点击而且是双击
            //这次点击是双击
            if (mListener != null) {//判断当mListener不为空时设置
                mListener.DoubleClick(this);
            }
        } else {//当松开的时间减去 firstTime = 0； 的时间差大于500，说明这是第一次点击
            //这次点击是第一次点击
            firstTime = upTime;
        }
    }

    public void setOnDoubleClickListener(onDoubleClickListener l) {//设置监听器的方法
        this.mListener = l;//DoubleClickButton的设置监听器的方法
    }
    public interface onDoubleClickListener {//双击监听器接口
        int JUDGE_TIME = 500;//用来判断点击间隔时间的常量
//        int CLICK_TIME = 500;//用来判断单击的时间

        void DoubleClick(View v);//双击抽象方法
    }
}
