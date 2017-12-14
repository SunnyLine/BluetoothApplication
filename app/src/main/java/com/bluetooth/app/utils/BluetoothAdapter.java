package com.bluetooth.app.utils;

import android.bluetooth.BluetoothDevice;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BluetoothApplication
 *
 * @author xugang
 * @date 2017/12/14
 * @describe
 */

public class BluetoothAdapter extends android.support.v7.widget.RecyclerView.Adapter<CommonViewHolder> {
    private Map<Integer,List<BluetoothDevice>> mData = new HashMap<>();

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
