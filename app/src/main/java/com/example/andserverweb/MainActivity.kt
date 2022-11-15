package com.example.andserverweb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.unionpay.UPPayAssistEx


class MainActivity : AppCompatActivity() {
    private var mServerManager: ServerManager? = null
    private var mService: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mServerManager = ServerManager(this)
        //注册服务
        mServerManager!!.register()
        //启动服务器
        mServerManager!!.startServer()
        PayService.setMainContent(applicationContext);

    }

    fun onPay(tokenId:String){
        UPPayAssistEx.startPay(applicationContext, null, null, tokenId, "00")
    }



    /**
     * Start notify.
     */
    fun onServerStart(ip: String) {
       Log.e("TAG","start ip:${ip}")
    }

    /**
     * Error notify.
     */
    fun onServerError(message: String?) {
        Log.e("TAG","start error:${message}")
    }

    /**
     * Stop notify.
     */
    fun onServerStop() {

    }
}