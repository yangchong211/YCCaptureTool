package com.yc.toolapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yc.appcommoninter.IMonitorToggle
import com.yc.appencryptlib.AesEncryptUtils
import com.yc.eventuploadlib.LoggerReporter
import com.yc.notcapturelib.helper.CaptureConfig
import com.yc.notcapturelib.helper.EncryptDecryptListener
import com.yc.notcapturelib.helper.NotCaptureHelper
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun init(){
        NotCaptureHelper.getInstance().config = CaptureConfig.builder()
            //设置debug模式
            .setDebug(true)
            //设置是否禁用代理
            .setProxy(false)
            //设置是否进行数据加密和解密，
            .setEncrypt(true)
            //设置cer证书路径
            .setCerPath("")
            //设置是否进行CA证书校验
            .setCaVerify(false)
            //设置加密和解密key
            .setEncryptKey("key")
            .setMonitorToggle(object : IMonitorToggle {
                override fun isOpen(): Boolean {
                    //todo 是否降级，如果降级，则不使用该功能。留给AB测试开关
                    return false
                }
            })
            .build()
        NotCaptureHelper.getInstance().encryptDecryptListener = object : EncryptDecryptListener {
            /**
             * 外部实现自定义加密数据
             */
            override fun encryptData(key: String, data: String): String {
                LoggerReporter.report("NotCaptureHelper", "encryptData data : $data")
                val str = AesEncryptUtils.encrypt(data, key)
                LoggerReporter.report("NotCaptureHelper", "encryptData str : $str")
                return str
            }
            /**
             * 外部实现自定义解密数据
             */
            override fun decryptData(key: String, data: String): String {
                LoggerReporter.report("NotCaptureHelper", "decryptData data : $data")
                val str = AesEncryptUtils.decrypt(data, key)
                LoggerReporter.report("NotCaptureHelper", "decryptData str : $str")
                return str
            }
        }

    }

    private fun setOkHttp() {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
        NotCaptureHelper.getInstance().setOkHttp(this,builder)
    }
}