package com.yourdomain.yourapp.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.InputStream;
import java.util.UUID;

public class BluetoothManager {
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket socket;
    private InputStream inputStream;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public BluetoothManager() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void connect(String macAddress, DataCallback callback) {
        new Thread(() -> {
            try {
                BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);
                socket = device.createRfcommSocketToServiceRecord(MY_UUID);
                socket.connect();
                inputStream = socket.getInputStream();
                readData(callback);
            } catch (Exception e) {
                Log.e("BluetoothManager", "Connection failed", e);
            }
        }).start();
    }

    private void readData(DataCallback callback) {
        byte[] buffer = new byte[1024];
        int bytes;
        try {
            while ((bytes = inputStream.read(buffer)) > 0) {
                String data = new String(buffer, 0, bytes);
                Log.d("BluetoothManager", "Received: " + data);
                if (callback != null) callback.onDataReceived(data);
            }
        } catch (Exception e) {
            Log.e("BluetoothManager", "Read failed", e);
        }
    }

    public interface DataCallback {
        void onDataReceived(String data);
    }
}