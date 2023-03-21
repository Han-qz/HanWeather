package com.example.hanweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.example.hanweather.location.BDLocationUtils;
import com.example.hanweather.location.MyLocationListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  LocationClient mLocationClient;
    private TextView positionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //positionText = (TextView)findViewById(R.id.testposition);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        /**定位
        LocationClient.setAgreePrivacy(true);
        BDLocationUtils bdLocationUtils = new BDLocationUtils(MainActivity.this);
        try {
            bdLocationUtils.doLocation();//开启定位
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        bdLocationUtils.mLocationClient.start();//开始定位
        Log.d("TAG", "获取经度:"+MyLocationListener.LONGITUDE);
        Log.d("TAG", "获取纬度:"+MyLocationListener.LATITUDE);
        //bdLocationUtils.mLocationClient.stop();//停止定位
         **/







        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString("weather",null)!=null){
            Intent intent = new Intent(this,WeatherAcitvity.class);
            startActivity(intent);
            finish();
        }


    }



}