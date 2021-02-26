package com.qinwang.traffic.main.myself.feedback;

import android.content.Context;
import android.view.View;

import com.qinwang.traffic.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/22
 * @Description:com.qinwang.traffic.main.myself.feedback
 * @Version:1.0
 * @function:
 */
public class FeedbackPresenterImpl implements FeedbackConstruct.FeedbackPresenter, FeedbackConstruct.FeedbackModel.onFeedbackListener {

    private FeedbackConstruct.FeedbackView mFeedbackView;
    private FeedbackConstruct.FeedbackModel mFeedbackModel;
    private Context context;

    public FeedbackPresenterImpl( Context context, FeedbackConstruct.FeedbackView mFeedbackView){
        this.context = context;
        this.mFeedbackView = mFeedbackView;
        this.mFeedbackModel = new FeedbackModelImpl();
    }

    @Override
    public void onDestroy() {
        mFeedbackView = null;
    }

    @Override
    public void validateFeedback(String Email, String message) {
        if (mFeedbackView != null){
            mFeedbackView.showLoading("", context.getString(R.string.Loading_upload));
        }
        mFeedbackModel.Feedback(context ,Email, message, this);
    }

    @Override
    public void onDescribeError(String msg) {
        if (mFeedbackView != null){
            mFeedbackView.showDescribeError();
            mFeedbackView.hideLoading("");
        }
    }

    @Override
    public void onEmailError(String msg) {
        if (mFeedbackView != null){
            mFeedbackView.showEmailError(msg);
            mFeedbackView.hideLoading("");
        }
    }

    @Override
    public void onSuccess(String msg) {
        if (mFeedbackView != null){
            mFeedbackView.showToast(msg);
            mFeedbackView.hideLoading("");
            mFeedbackView.showHintDialog(msg,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mFeedbackView.closeActivity();
                        }
                    });
        }
    }

    @Override
    public void onFail(String msg) {
        if (mFeedbackView != null){
            mFeedbackView.showToast(msg);
            mFeedbackView.hideLoading("");
        }
    }
}
