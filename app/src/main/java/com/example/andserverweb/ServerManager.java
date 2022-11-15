package com.example.andserverweb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by Zhenjie Yan on 2018/6/9.
 */
public class ServerManager extends BroadcastReceiver {
    private static final String ACTION = "com.andserver.receiver";
    private static final String CMD_KEY = "CMD_KEY";
    private static final String MESSAGE_KEY = "MESSAGE_KEY";
    private static final int CMD_VALUE_START = 1;
    private static final int CMD_VALUE_ERROR = 2;
    private static final int CMD_VALUE_STOP = 4;
    private static final int CMD_VALUE_PAY = 10;

    /**
     * Notify serverStart.
     *
     * @param context context.
     */
    public static void onServerStart(Context context, String hostAddress) {
        sendBroadcast(context, CMD_VALUE_START, hostAddress);
    }

    /**
     * Notify serverStop.
     *
     * @param context context.
     */
    public static void onServerError(Context context, String error) {
        sendBroadcast(context, CMD_VALUE_ERROR, error);
    }

    public static void pay(Context context,String tokenId){
        sendBroadcast(context, CMD_VALUE_PAY, tokenId);
    }
    /**
     * Notify serverStop.
     *
     * @param context context.
     */
    public static void onServerStop(Context context) {
        sendBroadcast(context, CMD_VALUE_STOP);
    }

    private static void sendBroadcast(Context context, int cmd) {
        sendBroadcast(context, cmd, null);
    }

    public static void sendBroadcast(Context context, int cmd, String message) {
        Intent broadcast = new Intent(ACTION);
        broadcast.putExtra(CMD_KEY, cmd);
        broadcast.putExtra(MESSAGE_KEY, message);
        context.sendBroadcast(broadcast);
    }

    private MainActivity mActivity;
    private Intent mService;

    public ServerManager(MainActivity activity) {
        this.mActivity = activity;
        mService = new Intent(activity, CoreService.class);
        Log.d("TAG","CreateCoreService");

    }

    /**
     * Register broadcast.
     */
    public void register() {
        IntentFilter filter = new IntentFilter(ACTION);
        mActivity.registerReceiver(this, filter);
    }

    /**
     * UnRegister broadcast.
     */
    public void unRegister() {
        mActivity.unregisterReceiver(this);
    }

    public void startServer() {
        //创建服务即启动服务器
        mActivity.startService(mService);
        Log.d("TAG","startCreateCoreService");

    }

    public void stopServer() {
        mActivity.stopService(mService);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION.equals(action)) {
            int cmd = intent.getIntExtra(CMD_KEY, 0);
            switch (cmd) {
                case CMD_VALUE_PAY:{
                    String tokenId = intent.getStringExtra(MESSAGE_KEY);
                    mActivity.onPay(tokenId);
                    break;
                }
                case CMD_VALUE_START: {
                    String ip = intent.getStringExtra(MESSAGE_KEY);
                    mActivity.onServerStart(ip);
                    break;
                }
                case CMD_VALUE_ERROR: {
                    String error = intent.getStringExtra(MESSAGE_KEY);
                    mActivity.onServerError(error);
                    break;
                }
                case CMD_VALUE_STOP: {
                    mActivity.onServerStop();
                    break;
                }
            }
        }
    }
}