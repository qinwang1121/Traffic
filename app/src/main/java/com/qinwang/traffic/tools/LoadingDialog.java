package com.qinwang.traffic.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.qinwang.traffic.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/4
 * @Description:com.qinwang.base
 * @Version:1.0
 * @function:
 */
public class LoadingDialog extends ProgressDialog {

    private String mTitleStr;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context)
    {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.loading_dialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

        TextView mTitle = (TextView)this.findViewById(R.id.message);
        if(mTitleStr != null) {
            mTitle.setText(mTitleStr);
        }else {
            mTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 当窗口焦点改变时调用
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        // 获取ImageView上的动画背景
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        // 开始动画
        spinner.start();
    }

    @Override
    public void show()
    {
        super.show();
    }

    public String getTitle() {
        return mTitleStr;
    }

    public LoadingDialog setTitle(String mTitle) {
        this.mTitleStr = mTitle;
        return this;
    }
}
