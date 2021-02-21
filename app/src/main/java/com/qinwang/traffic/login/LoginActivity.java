package com.qinwang.traffic.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qinwang.traffic.R;
import com.qinwang.traffic.forget.ForgetActivity;
import com.qinwang.traffic.main.MainActivity;
import com.qinwang.traffic.tools.LoadingDialog;
import com.qinwang.traffic.tools.WindowAssistant;

public class LoginActivity extends AppCompatActivity implements LoginConstract.LoginView, View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private LoadingDialog mLoadingDialog = null;
    private LoginConstract.LoginPresenter mLoginPresenter;

    private WindowAssistant windowAssistant = new WindowAssistant();
    public int width, height;

    private Button loginButton;
    private TextView amnesia_text;
    private EditText userName_editText, password_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        width = windowAssistant.getWindowWidth(this);
        height = windowAssistant.getWindowHeight(this);
        Log.i(TAG, "onCreate: width=" + width + ",height=" + height);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    void init(){
        amnesia_text = (TextView)findViewById(R.id.amnesia);
        loginButton = (Button)findViewById(R.id.enter);
        password_editText = (EditText)findViewById(R.id.input_passwork);
        userName_editText = (EditText)findViewById(R.id.input_userName);

        ViewGroup.LayoutParams layoutParams = loginButton.getLayoutParams();
        layoutParams.width = (int)(width / 2);

        mLoginPresenter = new LoginPresenterImpl(this, this);

        loginButton.setOnClickListener(this);
        amnesia_text.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        mLoginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void usernameError() {
        userName_editText.setError(getString(R.string.usernameError));
    }

    @Override
    public void passwordError() {
        password_editText.setError(getString(R.string.passwordError));
    }

    @Override
    public void showLoading(String tag, String msg) {
        if (this.isFinishing()){
            return;
        }
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this,
                    R.style.Loading_Dialog);
            mLoadingDialog.setTitle(msg);
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideLoading(String tag) {
        if (mLoadingDialog != null){
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,
                msg,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this,
                MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.enter:
                mLoginPresenter.validateCredentials(userName_editText.getText().toString(),
                        password_editText.getText().toString());
                break;
            case  R.id.amnesia:
                startActivity(new Intent(this,
                        ForgetActivity.class));
                break;
        }
    }
}