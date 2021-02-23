package com.qinwang.traffic.main.mistake.uploading;

import android.content.Context;
import android.view.View;

import com.qinwang.traffic.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/24
 * @Description:com.qinwang.traffic.main.mistake.uploading
 * @Version:1.0
 * @function:
 */
public class UploadingPresenterImpl implements UploadingConstract.UploadingPresenter, UploadingConstract.UploadingModel.onUploadingListener {

    private Context context;
    private UploadingConstract.UploadingView uploadingView;
    private UploadingConstract.UploadingModel uploadingModel;

    public UploadingPresenterImpl(Context context, UploadingConstract.UploadingView uploadingView){
        this.context = context;
        this.uploadingView = uploadingView;
        uploadingModel = new UploadingModeImpl();
    }

    @Override
    public void onDestroy() {
        uploadingView = null;
    }

    @Override
    public void validateUploading(String carNumber, String carColor, String carType, String boardColor, String mistakeTime, String mistakePlace, String mistakeDescribe, String policeName) {
        if (uploadingView != null){
            uploadingView.showLoading("", context.getString(R.string.Loading_upload));
        }
        uploadingModel.uploading(context, carNumber, carColor, carType, boardColor,
                mistakeTime, mistakePlace, mistakeDescribe, policeName, this);
    }

    @Override
    public void onCarNumberError() {
        if (uploadingView != null){
            uploadingView.showCarNumberError();
            uploadingView.hideLoading("");
        }
    }

    @Override
    public void onCarColorError() {
        if (uploadingView != null){
            uploadingView.showCarColorError();
            uploadingView.hideLoading("");
        }
    }

    @Override
    public void onCarTypeError() {
        if (uploadingView != null){
            uploadingView.showTypeError();
            uploadingView.hideLoading("");
        }
    }

    @Override
    public void onCarType() {
        if (uploadingView != null){
            uploadingView.hideTypeError();
            uploadingView.hideLoading("");
        }
    }

    @Override
    public void onBoardColorError() {
        if (uploadingView != null){
            uploadingView.showBoardColorError();
            uploadingView.hideLoading("");
        }
    }

    @Override
    public void onBoardColor() {
        if (uploadingView != null){
            uploadingView.hideBoardColorError();
            uploadingView.hideLoading("");
        }
    }

    @Override
    public void onDescribeError() {
        if (uploadingView != null){
            uploadingView.showDescribeError();
            uploadingView.hideLoading("");
        }
    }

    @Override
    public void onSuccess(String msg) {
        if (uploadingView != null){
            uploadingView.hideLoading("");
            uploadingView.showHintLog(msg,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            uploadingView.empty();
                        }
                    });
        }
    }

    @Override
    public void onFail(String msg) {
        if (uploadingView != null){
            uploadingView.showToast(msg);
            uploadingView.hideLoading("");
        }
    }
}
