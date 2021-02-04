package com.qinwang.traffic.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.qinwang.traffic.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/3
 * @Description:com.qinwang.transportation.tools
 * @Version:1.0
 * @function:
 */
public class CurvedCharacter extends View {

    private static final String title = "智能交通信号装置的设计与实现";

    private Path path = new Path();
    private Paint path_paint = new Paint();
    private Paint text_paint = new Paint();

    public CurvedCharacter(Context context) {
        super(context);
    }

    public CurvedCharacter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CurvedCharacter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CurvedCharacter(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.addCircle(canvas.getWidth() / 2,
                canvas.getHeight() / 2,
                canvas.getHeight() * 2 / 3,
                Path.Direction.CW);
        path_paint.setColor(getResources().getColor(R.color.colorPath));
        path_paint.setAntiAlias(true);//去锯齿
        path_paint.setTextSize(24);
        canvas.translate(0, canvas.getHeight() / 3);// 画布下移
        //绘制路径
        path_paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, path_paint);
        //绘制文本
        text_paint.setColor(getResources().getColor(R.color.colorTitle));
        text_paint.setStyle(Paint.Style.FILL);
        text_paint.setTextSize(95);
        canvas.drawTextOnPath(title,
                path,
                1220f,
                0f,
                text_paint);
    }
}