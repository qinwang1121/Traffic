package com.qinwang.traffic.main.myself.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mylhyl.circledialog.CircleDialog;
import com.qinwang.traffic.R;
import com.qinwang.traffic.tools.LoadingDialog;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener, FeedbackConstract.FeedbackView {

    private static final int MAX_NUM = 200;
    private TextView feedback_size, toolbar_title;
    private EditText feedback_text, feedback_email;
    private FeedbackConstract.FeedbackPresenter mFeedbackPresenter;
    private LoadingDialog mLoadingDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        init();
    }

    public void init(){
        feedback_text = (EditText) findViewById(R.id.feedback_text_feedback);
        feedback_email = (EditText)findViewById(R.id.feedback_text_Email);
        feedback_size = (TextView)findViewById(R.id.feedback_text_size);
        toolbar_title = (TextView)findViewById(R.id.toolber_titlle);

        findViewById(R.id.feedback_button).setOnClickListener(this);

        toolbar_title.setText(getString(R.string.myself_feedback));
        toolbar_title.setGravity(Gravity.CENTER);

        mFeedbackPresenter = new FeedbackPresenterImpl(this, this);

        showTextSize(0);
        feedback_text.addTextChangedListener(watcher);
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //只要编辑框内容有变化就会调用该方法，charSequence为编辑框变化后的内容
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //编辑框内容变化之前会调用该方法，charSequence为编辑框内容变化之前的内容
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
            if (editable.length() > MAX_NUM){
                editable.delete(MAX_NUM, editable.length());
                showHintDialog(getString(R.string.Dialog_feedback_number_error),
                        null);
            }
            showTextSize(editable.length());
        }
    };

    @Override
    protected void onDestroy() {
        mFeedbackPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.feedback_button) {
            mFeedbackPresenter.validateFeedback(feedback_email.getText().toString(),
                    feedback_text.getText().toString());
        }
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
        Toast.makeText(FeedbackActivity.this,
                msg,
                Toast.LENGTH_LONG).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showTextSize(int num) {
        feedback_size.setText(getString(R.string.feedback_number) + num);
    }

    @Override
    public void showDescribeError() {
        feedback_text.setError(getString(R.string.feedback_describe_error));
    }

    @Override
    public void showHintDialog(String msg, View.OnClickListener listener) {
        new CircleDialog.Builder(FeedbackActivity.this)
                .setTitle(getString(R.string.Dialog_title))
                .setText(msg)
                .setPositive(getString(R.string.Dialog_true), listener)
                .show();
    }

    @Override
    public void showEmailError(String msg) {
        feedback_email.setError(msg);
    }

    @Override
    public void closeActivity() {
        finish();
    }
}