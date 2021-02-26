package com.qinwang.traffic.main.myself.feedback;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.qinwang.traffic.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/21
 * @Description:com.qinwang.traffic.main.myself.feedback
 * @Version:1.0
 * @function:
 */
public class FeedbackModelImpl implements FeedbackConstruct.FeedbackModel {
    @Override
    public void Feedback(final Context context, final String Email, final String message, final onFeedbackListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(message)){
                    listener.onDescribeError(context.getString(R.string.feedback_describe_error));
                    error = true;
                }
                if (TextUtils.isEmpty(Email)){
                    listener.onEmailError(context.getString(R.string.feedback_mail_null));
                    error = true;
                }
                if (! TextUtils.isEmpty(Email) & !isEmail(Email)){
                    listener.onEmailError(context.getString(R.string.feedback_mail_error));
                    error = true;
                }
                if (! error){
                    listener.onSuccess(context.getString(R.string.feedback_success));
                }else {
                    listener.onFail(context.getString(R.string.feedback_fail));
                }
            }
        }, 2000);
    }

    /**
     * 正则表达式 判断邮箱格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
