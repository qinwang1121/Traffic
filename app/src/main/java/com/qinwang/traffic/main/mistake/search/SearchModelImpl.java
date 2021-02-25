package com.qinwang.traffic.main.mistake.search;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.qinwang.traffic.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/2/25
 * @Description:com.qinwang.traffic.main.mistake.search
 * @Version:1.0
 * @function:
 */
public class SearchModelImpl implements SearchConstruct.SearchModel {
    @Override
    public void Search(Context context, final String Msg, final onSearchListener listener) {

        boolean error = false;
        if (TextUtils.isEmpty(Msg)){
            error = true;
            listener.onSearchMsgError();
        }
        if (!error){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Msg.equals("11")){
                        listener.onSuccess("msg");
                    }else {
                        listener.onFail("fail");
                    }
                }
            }, 2000);
        }else {
            listener.onFail(context.getString(R.string.searchFail));
        }
    }
}
