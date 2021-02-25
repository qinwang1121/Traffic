package com.qinwang.traffic.main.mistake.search;

import android.content.Context;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/24
 * @Description:com.qinwang.traffic.main.mistake.search
 * @Version:1.0
 * @function:
 */
public interface SearchConstruct {

    interface SearchView{

        void showLoading(String tag, String msg);//显示加载动画

        void hideLoading(String tag);//关闭加载动画

        void showSearchMsgError();

        void showToast(String msg);

        void showMsgView();

        void hideMsgView();
    }

    interface SearchModel{

        interface onSearchListener{

            void onSearchMsgError();

            void onSuccess(String msg);

            void onFail(String msg);
        }
        void Search(Context context, String Msg, onSearchListener listener);
    }

    interface SearchPresenter{

        void onDestroy();

        void validateSearch(String Msg);
    }
}
