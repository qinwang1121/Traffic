package com.qinwang.traffic.login;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/5
 * @Description:com.qinwang.traffic.login
 * @Version:1.0
 * @function:
 */
public interface LoginConstract {

    interface LoginView{

        void usernameError();//用户名为空

        void passwordError();//密码为空

        void showLoading(String tag, String msg);//显示加载动画

        void hideLoading(String tag);//关闭加载动画

        void showToast(String msg);//显示提示

        void navigateToHome();//跳转到Home
    }

    interface LoginModel{

        interface onLoginListener{

            void onUserNameNUll();//用户名为空

            void onPassWordNull();//密码为空

            void onSuccess();//登录成功

            void onFail();//登录失败
        }

        void Login(String userName, String passWord, onLoginListener listener);
    }

    interface LoginPresenter{

        void validateCredentials(String userName, String password);

        void onDestroy();
    }
}
