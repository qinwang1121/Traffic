package com.qinwang.traffic.main.mistake.uploading;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.qinwang.traffic.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/24
 * @Description:com.qinwang.traffic.main.mistake.uploading
 * @Version:1.0
 * @function:
 */
public class UploadingModeImpl implements UploadingConstruct.UploadingModel {
    @Override
    public void uploading(final Context context, final String carNumber, final String carColor, final String carType, final String boardColor,
                          String mistakeTime, String mistakePlace, final String mistakeDescribe, String policeName, final onUploadingListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(carNumber)){
                    listener.onCarNumberError();
                    error = true;
                }
                if (TextUtils.isEmpty(carColor)){
                    listener.onCarColorError();
                    error = true;
                }
                if (TextUtils.isEmpty(carType)){
                    listener.onCarTypeError();
                    error = true;
                }else {
                    listener.onCarType();
                }
                if (TextUtils.isEmpty(boardColor)){
                    listener.onBoardColorError();
                    error = true;
                }else {
                    listener.onBoardColor();
                }
                if (TextUtils.isEmpty(mistakeDescribe)){
                    listener.onDescribeError();
                    error = true;
                }
                if (!error){
                    listener.onSuccess(context.getString(R.string.uploading_success));
                }else {
                    listener.onFail(context.getString(R.string.uploading_fail));
                }
            }
        }, 2000);
    }
}
