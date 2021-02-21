package com.qinwang.traffic.main.mistake;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qinwang.traffic.R;

public class MistakeFragment extends Fragment implements View.OnClickListener {

    private MistakeViewModel mViewModel;
    private NavController navController;
    int bt_sc = 0, bt_ul = 0;
    private boolean isChange = false;

    public static MistakeFragment newInstance() {
        return new MistakeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mistake_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MistakeViewModel.class);
        // TODO: Use the ViewModel

        navController = Navigation.findNavController(getActivity(),R.id.fragment2);

        getView().findViewById(R.id.button_uploading).setOnClickListener(this);
        getView().findViewById(R.id.button_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_search:
                isChange = true;
                bt_sc ++;
                bt_ul = 0;
                if (bt_sc == 1){
                    navController.navigate(R.id.action_uploadingFragment_to_searchFragment);
                }else {

                }
                break;
            case R.id.button_uploading:
                if (isChange){
                    bt_sc = 0;
                    bt_ul ++;
                    if (bt_ul == 1){
                        navController.navigate(R.id.action_searchFragment_to_uploadingFragment);
                    }else {

                    }
                } else {

                }
                break;
        }
    }
}