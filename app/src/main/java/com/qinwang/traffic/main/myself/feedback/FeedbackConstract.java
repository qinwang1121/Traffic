package com.qinwang.traffic.main.myself.feedback;

import android.content.Context;
import android.view.View;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/21
 * @Description:com.qinwang.traffic.main.myself.feedback
 * @Version:1.0
 * @function:
 */
public interface FeedbackConstract {

    interface FeedbackView{

        void showLoading(String tag, String msg);//显示加载动画

        void hideLoading(String tag);//关闭加载动画

        void showToast(String msg);

        void showTextSize(int num);

        void showDescribeError();

        void showHintDialog(String msg, View.OnClickListener listener);

        void showEmailError(String msg);

        void closeActivity();

    }

    interface FeedbackModel{

        interface onFeedbackListener{

            void onDescribeError(String msg);

            void onEmailError(String msg);

            void onSuccess(String msg);

            void onFail(String msg);
        }

        void Feedback(Context context, String Email, String message, onFeedbackListener listener);
    }

    interface FeedbackPresenter{

        void onDestroy();

        void validateFeedback(String Email, String message);
    }
}
