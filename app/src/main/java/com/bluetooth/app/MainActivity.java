package com.bluetooth.app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.bluetooth.app.utils.CommonAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private RecyclerView recyclerView;
    private Switch aSwitch;
    private BluetoothAdapter mBluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 4 << 2;

    private List<BluetoothDevice> pairedDevices = new ArrayList<>();
    private List<BluetoothDevice> newFindDevices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        aSwitch = (Switch) findViewById(R.id.switch1);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            toast("设备不支持蓝牙");
            return;
        }
        aSwitch.setChecked(mBluetoothAdapter.isEnabled());
        aSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            openBluetooth();
        } else {
            closeBluetooth();
        }
    }

    private void openBluetooth() {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private void closeBluetooth() {
        if (mBluetoothAdapter.isEnabled()) {

        }
    }

    private void searchBluetoothDevice() {

    }

    private void stopSearch() {
        mBluetoothAdapter.cancelDiscovery();
    }

    /**
     * 获取曾经配对过得设备
     */
    private void findBondedDevices() {
        pairedDevices.clear();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices != null && pairedDevices.size() > 0) {
            pairedDevices.addAll(pairedDevices);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                aSwitch.setChecked(resultCode == RESULT_OK ? true : false);
                if (resultCode == RESULT_OK) {
                    searchBluetoothDevice();
                }
                break;
            default:
                break;
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    newFindDevices.add(device);
                    break;
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                    switch (state) {
                        case BluetoothAdapter.STATE_TURNING_ON:
                            Log.d("==============>", "正在打开");
                            break;
                        case BluetoothAdapter.STATE_ON:
                            Log.d("==============>", "打开");
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            Log.d("==============>", "正在关闭");
                            break;
                        case BluetoothAdapter.STATE_OFF:
                            Log.d("==============>", "关闭");
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
