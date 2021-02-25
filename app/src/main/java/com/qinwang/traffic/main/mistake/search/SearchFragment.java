package com.qinwang.traffic.main.mistake.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qinwang.traffic.R;
import com.qinwang.traffic.tools.LoadingDialog;

public class SearchFragment extends Fragment implements SearchConstruct.SearchView, View.OnClickListener {

    private EditText SearchMsg;
    private LinearLayout layoutSearch;

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
        layoutSearch = (LinearLayout) getView().findViewById(R.id.layoutSearch);
        getView().findViewById(R.id.searchBut)
                .setOnClickListener(this);
        getView().findViewById(R.id.msgButBack)
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
    public void showMsgView() {
        getView().findViewById(R.id.layoutMsg).setVisibility(View.VISIBLE);
        layoutSearch.setVisibility(View.GONE);
    }

    @Override
    public void hideMsgView() {
        getView().findViewById(R.id.layoutMsg).setVisibility(View.GONE);
        layoutSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchBut:
                mSearchPresenter.validateSearch(SearchMsg.getText().toString());
                break;
            case R.id.msgButBack:
                hideMsgView();
                break;
        }
    }
}