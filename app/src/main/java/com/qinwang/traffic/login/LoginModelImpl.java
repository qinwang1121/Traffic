package com.qinwang.traffic.login;

import android.os.Handler;
import android.text.TextUtils;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/5
 * @Description:com.qinwang.traffic.login
 * @Version:1.0
 * @function:
 */
public class LoginModelImpl implements LoginConstruct.LoginModel {
    @Override
    public void Login(final String userName, final String passWord, final onLoginListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(userName)){
                    listener.onUserNameNUll();
                    error = true;
                }
                if (TextUtils.isEmpty(passWord)){
                    listener.onPassWordNull();
                    error = true;
                }
                if (!userName.equals("11")){
                    listener.onFail();
                    error = true;
                }
                if (!error){
                    listener.onSuccess();
                }
            }
        }, 2000);
    }
}
