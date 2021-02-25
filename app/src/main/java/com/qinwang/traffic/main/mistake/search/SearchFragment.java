package com.qinwang.traffic.main.mistake.search;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.qinwang.traffic.R;
import com.qinwang.traffic.tools.LoadingDialog;

public class SearchFragment extends Fragment implements SearchConstruct.SearchView, View.OnClickListener {

    private EditText SearchMsg;

    private LoadingDialog mLoadingDialog = null;

    private SearchConstruct.SearchPresenter mSearchPresenter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel

        init();
    }

    public void init(){
        SearchMsg = (EditText) getView().findViewById(R.id.searchMsg);
        getView().findViewById(R.id.searchBut)
                .setOnClickListener(this);
        mSearchPresenter = new SearchPresenterImpl(getActivity(), this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSearchPresenter.onDestroy();
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
    public void showSearchMsgError() {
        SearchMsg.setError(getString(R.string.searchMsgError));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),
                msg,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.searchBut) {
            mSearchPresenter.validateSearch(SearchMsg.getText().toString());
        }
    }
}