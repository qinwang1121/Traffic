package com.qinwang.traffic.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2020/7/18
 * @Description:com.qinwang.worklife.utils
 * @Version:1.0
 * @function:自定义ImageView（圆形外观）
 */
public class CircleImageView extends AppCompatImageView {

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *实现圆的绘制
     *
     * @param bmp
     * @param radius
     * @return
     */
    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;

        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bmp,
                    (int) (bmp.getWidth() / factor),
                    (int) (bmp.getHeight() / factor),
                    false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        //创建画笔
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);   //防止边缘的锯齿
        paint.setFilterBitmap(true);//对位图进行滤波处理
        paint.setDither(true);      //防抖动
        //设置画笔的a,r,p,g值
        canvas.drawARGB(0, 0, 0, 0);
        // 设置画笔颜色
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(radius / 2 , radius / 2 ,
                radius / 2 , paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

    /**
     * 绘制图形
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //Drawable 是一种媒介，它可以把内容绘制到 Canvas 上
        Drawable drawable = getDrawable();

        if (drawable == null) return;
        if (getWidth() == 0 || getHeight() == 0) return;

        //Drawable 转换成 Bitmap
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        int w = getWidth(), h = getHeight();
        int radius = Math.min(w, h);
        Bitmap roundBitmap = getCroppedBitmap(bitmap, radius);
        canvas.drawBitmap(roundBitmap, ( w / 2 ) - ( radius / 2 ) , 0, null);
    }
}
