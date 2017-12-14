package com.bluetooth.app;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * BluetoothApplication
 *
 * @author xugang
 * @date 2017/12/14
 * @describe
 */

public class BaseActivity extends AppCompatActivity {

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void toast(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }
}
