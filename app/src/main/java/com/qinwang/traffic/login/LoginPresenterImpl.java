package com.qinwang.traffic.login;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/5
 * @Description:com.qinwang.traffic.login
 * @Version:1.0
 * @function:
 */
public class LoginPresenterImpl implements LoginConstract.LoginPresenter, LoginConstract.LoginModel.onLoginListener {

    private LoginConstract.LoginView mLoginView;
    private LoginConstract.LoginModel mLoginModel;

    public LoginPresenterImpl(LoginConstract.LoginView loginView){
        this.mLoginView = loginView;
        this.mLoginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String userName, String password) {
        if (mLoginView != null){
            mLoginView.showLoading("", "登陆中");
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
        }
    }

    @Override
    public void onPassWordNull() {
        if (mLoginView != null){
            mLoginView.passwordError();
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
