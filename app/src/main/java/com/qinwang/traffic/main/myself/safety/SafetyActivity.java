package com.qinwang.traffic.main.myself.safety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.qinwang.traffic.R;

public class SafetyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);

        init();
    }

    public void init(){
        findViewById(R.id.makeSure).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.makeSure){
            finish();
        }
    }
}