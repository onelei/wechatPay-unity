package com.onelei.wechatpay.unityplugin;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import org.json.JSONObject;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by onelei on 2017/1/6.
 */

public class WeChatPay extends UnityPlayerActivity {

    public static String APPID = "112233";
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //创建一个Activity
        super.onCreate(savedInstanceState);

    }

    public void weChatPay(String appId,String partnerId,String prepayId,String nonceStr,
                          String timeStamp,String packageValue,String sign,String extData)
    {
        PayReq req = new PayReq();
        req.appId			= appId;
        req.partnerId		= partnerId;
        req.prepayId		= prepayId;
        req.nonceStr		= nonceStr;
        req.timeStamp		= timeStamp;
        req.packageValue	= packageValue;
        req.sign			= sign;
        req.extData			= extData;
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api = WXAPIFactory.createWXAPI(this, APPID,false);
        api.registerApp(APPID);
        api.sendReq(req);
    }

    public boolean isPaySupported(){
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        return isPaySupported;
    }

}
