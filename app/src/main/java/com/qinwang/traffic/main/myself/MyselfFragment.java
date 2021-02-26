package com.qinwang.traffic.main.myself;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylhyl.circledialog.CircleDialog;
import com.qinwang.traffic.R;
import com.qinwang.traffic.login.LoginActivity;
import com.qinwang.traffic.main.myself.feedback.FeedbackActivity;
import com.qinwang.traffic.main.myself.safety.SafetyActivity;

public class MyselfFragment extends Fragment implements View.OnClickListener, MyselfView {

    public static MyselfFragment newInstance() {
        return new MyselfFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myself_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel

        getView().findViewById(R.id.myself_safety)
                .setOnClickListener(this);
        getView().findViewById(R.id.myself_feedback)
                .setOnClickListener(this);
        getView().findViewById(R.id.myself_examine)
                .setOnClickListener(this);
        getView().findViewById(R.id.myself_out)
                .setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.myself_safety:
                startActivity(new Intent(getActivity(),
                        SafetyActivity.class));
                break;
            case R.id.myself_feedback:
                startActivity(new Intent(getActivity(),
                        FeedbackActivity.class));
                break;
            case R.id.myself_examine:
                showExamineDialog();
                break;
            case R.id.myself_out:
                showExitDialog();
                break;
        }
    }

    @Override
    public void showExamineDialog() {
        new CircleDialog.Builder(getActivity())
                .setTitle(getString(R.string.Dialog_title))
                .setText(getString(R.string.Dialog_Examine_text))
                .setPositive(getString(R.string.Dialog_true), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    @Override
    public void showExitDialog() {
        new CircleDialog.Builder(getActivity())
                .setTitle(getString(R.string.Dialog_title))
                .setText(getString(R.string.Dialog_text))
                .setPositive(getString(R.string.Dialog_true), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().finish();
                        startActivity(new Intent(getActivity(),
                                LoginActivity.class));
                    }
                })
                .setNegative(getString(R.string.Dialog_false),null)
                .show();
    }
}