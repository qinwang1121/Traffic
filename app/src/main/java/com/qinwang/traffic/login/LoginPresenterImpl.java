package com.qinwang.traffic.login;

import android.content.Context;

import com.qinwang.traffic.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/5
 * @Description:com.qinwang.traffic.login
 * @Version:1.0
 * @function:
 */
public class LoginPresenterImpl implements LoginConstruct.LoginPresenter, LoginConstruct.LoginModel.onLoginListener {

    private LoginConstruct.LoginView mLoginView;
    private LoginConstruct.LoginModel mLoginModel;
    private Context context;

    public LoginPresenterImpl(Context context, LoginConstruct.LoginView loginView){
        this.context = context;
        this.mLoginView = loginView;
        this.mLoginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String userName, String password) {
        if (mLoginView != null){
            mLoginView.showLoading("", context.getString(R.string.Loading_login));
        }
        mLoginModel.Login(userName, password, this);
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }

    @Override
    public void onUserNameNUll() {
        if (mLoginView != null){
            mLoginView.usernameError();
            mLoginView.hideLoading("");
        }
    }

    @Override
    public void onPassWordNull() {
        if (mLoginView != null){
            mLoginView.passwordError();
            mLoginView.hideLoading("");
        }
    }

    @Override
    public void onSuccess() {
        if (mLoginView != null){
            mLoginView.navigateToHome();
            mLoginView.hideLoading("");
        }
    }

    @Override
    public void onFail() {
        if (mLoginView != null){
            mLoginView.showToast("登录失败，请检查网络");
            mLoginView.hideLoading("");
        }
    }
}
