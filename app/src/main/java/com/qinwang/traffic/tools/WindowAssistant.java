package com.qinwang.traffic.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class WindowAssistant {

    public static int[] getWindowHW(Context context){
        WindowManager windowManager =
                (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        /*
        getMetrics()替换为getRealMetrics()
        原因：getMetrics()获取到的屏幕信息会忽略底部的虚拟按键的高度
         */
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int[] HW = new int[]{width, height};
        return HW;
    }

    /**
     * 获取窗口的宽
     * @param context
     * @return
     */
    public int getWindowWidth(Context context){
        return getWindowHW(context)[0];
    }

    /**
     * 获取窗口的高
     * @param context
     * @return
     */
    public int getWindowHeight(Context context){
        return getWindowHW(context)[1];
    }
}
