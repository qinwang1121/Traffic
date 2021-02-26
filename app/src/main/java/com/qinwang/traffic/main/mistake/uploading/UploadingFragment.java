package com.qinwang.traffic.main.mistake.uploading;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mylhyl.circledialog.CircleDialog;
import com.qinwang.traffic.R;
import com.qinwang.traffic.tools.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadingFragment extends Fragment implements UploadingConstruct.UploadingView{

    private final String TAG = "UploadingFragment";

    private EditText carNumber, carColor, otherType,
            mistakeTime, mistakePlace,
            mistakeDescribe, policeName;
    private LinearLayout layoutOtherType;
    private CheckBox carBig, carSmall, truckBig, truckSmall,
            motorcycle, other,
            colorBlue, colorYellow, colorGreen, colorWhite, colorBlack;
    private TextView spaceType, spaceColor;

    private LoadingDialog mLoadingDialog = null;
    private UploadingConstruct.UploadingPresenter mUploadingPresenter;

    private String carType = null, boardColor = null;

    private boolean isClick = false;

    private Handler handler;

    public static UploadingFragment newInstance() {
        return new UploadingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.uploading_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
        getTime();
        carTypeListen();
        boardColorListen();

        mUploadingPresenter = new UploadingPresenterImpl(getActivity(), this);
    }

    public void init(){
        carNumber = getView().findViewById(R.id.carNumber);
        carColor = getView().findViewById(R.id.carColor);
        otherType = getView().findViewById(R.id.otherType);
        mistakeTime = getView().findViewById(R.id.mistakeTime);
        mistakePlace = getView().findViewById(R.id.mistake_place);
        mistakeDescribe = getView().findViewById(R.id.mistake_describe);
        policeName = getView().findViewById(R.id.policeName);

        spaceType = getView().findViewById(R.id.spaceType);
        spaceColor = getView().findViewById(R.id.spaceColor);

        layoutOtherType = getView().findViewById(R.id.layout0therType);

        carBig = getView().findViewById(R.id.carBig);
        carSmall = getView().findViewById(R.id.carSmall);
        truckBig = getView().findViewById(R.id.truckBig);
        truckSmall = getView().findViewById(R.id.truckSmall);
        motorcycle = getView().findViewById(R.id.motorcycle);
        other = getView().findViewById(R.id.other);

        colorBlue = getView().findViewById(R.id.colorBlue);
        colorYellow = getView().findViewById(R.id.colorYellow);
        colorGreen = getView().findViewById(R.id.colorGreen);
        colorWhite = getView().findViewById(R.id.colorWhite);
        colorBlack = getView().findViewById(R.id.colorBlack);

        mistakeTime.setFocusable(false);//设置为不可编辑
        mistakePlace.setFocusable(false);
        policeName.setFocusable(false);

        getView().findViewById(R.id.button_uploading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isClick){
                            mUploadingPresenter.validateUploading(carNumber.getText().toString(),
                                    carColor.getText().toString(),
                                    otherType.getText().toString(),
                                    boardColor,
                                    mistakeTime.getText().toString(),
                                    mistakePlace.getText().toString(),
                                    mistakeDescribe.getText().toString(),
                                    policeName.getText().toString());
                        }else {
                            mUploadingPresenter.validateUploading(carNumber.getText().toString(),
                                    carColor.getText().toString(),
                                    carType,
                                    boardColor,
                                    mistakeTime.getText().toString(),
                                    mistakePlace.getText().toString(),
                                    mistakeDescribe.getText().toString(),
                                    policeName.getText().toString());
                        }
                        Log.d(TAG, mistakeTime.getText().toString());
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUploadingPresenter.onDestroy();
    }

    @Override
    public void showLoading(String tag, String msg) {
        if (getActivity().isFinishing()){
            return;
        }
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(getActivity(),
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
    public void showCarNumberError() {
        carNumber.setError(getString(R.string.errorCarNumber));
    }

    @Override
    public void showCarColorError() {
        carColor.setError(getString(R.string.errorCarColor));
    }

    @Override
    public void showTypeError() {
        spaceType.setError(getString(R.string.errorType));
    }

    @Override
    public void hideTypeError() {
        spaceType.setError(null);
    }

    @Override
    public void showBoardColorError() {
        spaceColor.setError(getString(R.string.errorBoardColor));
    }

    @Override
    public void hideBoardColorError() {
        spaceColor.setError(null);
    }

    @Override
    public void showDescribeError() {
        mistakeDescribe.setError(getString(R.string.errorDescribe));
    }

    @Override
    public void showLayoutOtherType() {
        layoutOtherType.setVisibility(View.VISIBLE);
        getCarType(otherType.getText().toString());
    }

    @Override
    public void hideLayoutOtherType() {
        layoutOtherType.setVisibility(View.GONE);
        otherType.setText(null);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),
                msg,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showHintLog(String msg, View.OnClickListener listener) {
        new CircleDialog.Builder(getActivity())
                .setTitle(getString(R.string.Dialog_title))
                .setText(msg)
                .setPositive(getString(R.string.Dialog_true), listener)
                .show();
    }

    @Override
    public void empty() {
        carNumber.setText(null);
        carColor.setText(null);

        carBig.setChecked(false);
        carSmall.setChecked(false);
        truckBig.setChecked(false);
        truckSmall.setChecked(false);
        motorcycle.setChecked(false);
        other.setChecked(false);

        colorBlue.setChecked(false);
        colorYellow.setChecked(false);
        colorGreen.setChecked(false);
        colorWhite.setChecked(false);
        colorBlack.setChecked(false);

        mistakeDescribe.setText(null);

        getCarType("");
        getBoardColor("");
    }

    public void getCarType(String type) {
        carType = type;
        Log.d(TAG, carType);
    }

    public void getBoardColor(String color) {
        boardColor = color;
        Log.d(TAG, boardColor);
    }

    public void carTypeListen(){
        carBig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carSmall.setChecked(false);
                    truckBig.setChecked(false);
                    truckSmall.setChecked(false);
                    motorcycle.setChecked(false);
                    other.setChecked(false);
                    getCarType(carBig.getText().toString());
                }
            }
        });
        carSmall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    truckBig.setChecked(false);
                    truckSmall.setChecked(false);
                    motorcycle.setChecked(false);
                    other.setChecked(false);
                    getCarType(carSmall.getText().toString());
                }
            }
        });
        truckBig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    carSmall.setChecked(false);
                    truckSmall.setChecked(false);
                    motorcycle.setChecked(false);
                    other.setChecked(false);
                    getCarType(truckBig.getText().toString());
                }
            }
        });
        truckSmall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    carSmall.setChecked(false);
                    truckBig.setChecked(false);
                    motorcycle.setChecked(false);
                    other.setChecked(false);
                    getCarType(truckSmall.getText().toString());
                }
            }
        });
        motorcycle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    carSmall.setChecked(false);
                    truckBig.setChecked(false);
                    truckSmall.setChecked(false);
                    other.setChecked(false);
                    getCarType(motorcycle.getText().toString());
                }
            }
        });
        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    carSmall.setChecked(false);
                    truckBig.setChecked(false);
                    truckSmall.setChecked(false);
                    motorcycle.setChecked(false);
                    showLayoutOtherType();
                    isClick = true;
                }else {
                    hideLayoutOtherType();
                }
            }
        });
    }

    public void boardColorListen(){
        colorBlue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorYellow.setChecked(false);
                    colorGreen.setChecked(false);
                    colorWhite.setChecked(false);
                    colorBlack.setChecked(false);
                    getBoardColor(colorBlue.getText().toString());
                }
            }
        });
        colorYellow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorBlue.setChecked(false);
                    colorGreen.setChecked(false);
                    colorWhite.setChecked(false);
                    colorBlack.setChecked(false);
                    getBoardColor(colorYellow.getText().toString());
                }
            }
        });
        colorGreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorBlue.setChecked(false);
                    colorYellow.setChecked(false);
                    colorWhite.setChecked(false);
                    colorBlack.setChecked(false);
                    getBoardColor(colorGreen.getText().toString());
                }
            }
        });
        colorWhite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorBlue.setChecked(false);
                    colorYellow.setChecked(false);
                    colorGreen.setChecked(false);
                    colorBlack.setChecked(false);
                    getBoardColor(colorWhite.getText().toString());
                }
            }
        });
        colorBlack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorBlue.setChecked(false);
                    colorYellow.setChecked(false);
                    colorGreen.setChecked(false);
                    colorWhite.setChecked(false);
                    getBoardColor(colorBlack.getText().toString());
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    public void getTime(){
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                mistakeTime.setText(String.valueOf(msg.obj));
            }
        };
        Threads mThreads = new Threads();
        mThreads.start();
    }

    class Threads extends Thread{
        @Override
        public void run() {
            try {
                while (true){
                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat simpleDateFormat =
                            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// HH:mm:ss
                    handler.sendMessage(handler.obtainMessage(100,
                            simpleDateFormat.format(new Date())));
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}