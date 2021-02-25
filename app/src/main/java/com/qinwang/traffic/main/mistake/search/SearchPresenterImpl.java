package com.qinwang.traffic.main.mistake.search;

import android.content.Context;

import com.qinwang.traffic.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/25
 * @Description:com.qinwang.traffic.main.mistake.search
 * @Version:1.0
 * @function:
 */
public class SearchPresenterImpl implements SearchConstruct.SearchPresenter, SearchConstruct.SearchModel.onSearchListener {

    private SearchConstruct.SearchModel mSearchModel;
    private SearchConstruct.SearchView mSearchView;
    private Context context;

    public SearchPresenterImpl(Context context, SearchConstruct.SearchView searchView){
        this.context = context;
        this.mSearchView = searchView;
        mSearchModel = new SearchModelImpl();
    }

    @Override
    public void onDestroy() {
        mSearchView = null;
    }

    @Override
    public void validateSearch(String Msg) {
        if (mSearchView != null){
            mSearchView.showLoading("", context.getString(R.string.Loading_search));
        }
        mSearchModel.Search(context, Msg, this);
    }

    @Override
    public void onSearchMsgError() {
        if (mSearchView != null){
            mSearchView.showSearchMsgError();
        }
    }

    @Override
    public void onSuccess(String msg) {
        if (mSearchView != null){
            mSearchView.showToast(msg);
            mSearchView.showMsgView();
            mSearchView.hideLoading("");
        }
    }

    @Override
    public void onFail(String msg) {
        if (mSearchView != null){
            mSearchView.showToast(msg);
            mSearchView.hideLoading("");
        }
    }
}
