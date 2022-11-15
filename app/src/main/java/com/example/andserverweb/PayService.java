package com.example.andserverweb;

import android.content.Context;

public class PayService {
    private static Context mainContent;

    public static void setMainContent(Context mainContent){
        PayService.mainContent = mainContent;
    }

    public static Context getMainContext(){
        return PayService.mainContent;
    }



}
