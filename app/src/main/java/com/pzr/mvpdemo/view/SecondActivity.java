package com.pzr.mvpdemo.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pzr.mvpdemo.R;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        /**
         * 开启一个 fragment
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.second_container, new SecondFragment()).commit();
    }
}
