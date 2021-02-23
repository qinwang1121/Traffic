package com.qinwang.traffic.main.mistake.uploading;

import android.content.Context;
import android.view.View;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/23
 * @Description:com.qinwang.traffic.main.mistake.uploading
 * @Version:1.0
 * @function:
 */
public interface UploadingConstract {

    interface UploadingView{
        void showLoading(String tag, String msg);//显示加载动画

        void hideLoading(String tag);//关闭加载动画

        void showCarNumberError();

        void showCarColorError();

        void showTypeError();

        void hideTypeError();

        void showBoardColorError();

        void hideBoardColorError();

        void showDescribeError();

        void showLayoutOtherType();

        void hideLayoutOtherType();

        void showToast(String msg);

        void showHintLog(String msg, View.OnClickListener listener);

        void empty();

    }

    interface UploadingModel{
        interface onUploadingListener{
            void onCarNumberError();

            void onCarColorError();

            void onCarTypeError();

            void onCarType();

            void onBoardColorError();

            void onBoardColor();

            void onDescribeError();

            void onSuccess(String msg);

            void onFail(String msg);
        }
        void uploading(Context context, String carNumber, String carColor, String carType,
                      String boardColor, String mistakeTime, String mistakePlace,
                      String mistakeDescribe, String policeName, onUploadingListener listener);
    }

    interface UploadingPresenter{
        void onDestroy();

        void validateUploading(String carNumber, String carColor, String carType,
                               String boardColor, String mistakeTime, String mistakePlace,
                               String mistakeDescribe, String policeName);
    }
}
