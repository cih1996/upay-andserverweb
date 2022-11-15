package com.example.andserverweb.controller;

import android.content.Intent;
import android.util.Log;

import com.example.andserverweb.MainActivity;
import com.example.andserverweb.PayService;
import com.example.andserverweb.ServerManager;
import com.unionpay.UPPayAssistEx;
import com.yanzhenjie.andserver.Server;
import com.yanzhenjie.andserver.annotation.*;


@RestController
@RequestMapping(path = "/upay")
class UPayController {

    @GetMapping(value = "/pay")
    public String upayPay(@RequestParam(name = "token_id") String tokenId) {
        ServerManager.pay(PayService.getMainContext(),tokenId);
        return "SUCCESS";
    }
}
