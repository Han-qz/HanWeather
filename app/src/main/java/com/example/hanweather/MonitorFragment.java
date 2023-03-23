package com.example.hanweather;

import static com.qweather.sdk.view.HeContext.context;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.qweather.sdk.bean.air.AirNowBean;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.weather.WeatherDailyBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.HeConfig;
import com.qweather.sdk.view.QWeather;

import java.util.List;

public class MonitorFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_monitor, container, false);

        //getPosition(view);
        HeConfig.init("HE2303221709181158", "575bb544ba644c218791ad568648bb0c");//将上边创建项目后的id和key复制进去
        HeConfig.switchToDevService();
        setTempAndHumidity(view);//刚才上边那个函数
        return view;
    }

    public void setTempAndHumidity(View view){
        //location:查询的地区，可通过该地区ID、经纬度进行查询经纬度格式，这里以西安为例，城市编号为"CN101110101"
        //location可以填城市编号，也可以填经纬度
        QWeather.getWeatherNow(getActivity(), "CN101110101", Lang.ZH_HANS, Unit.METRIC, new QWeather.OnResultWeatherNowListener(){
            public static final String TAG="he_feng_now";
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ", e);
                System.out.println("获取天气失败");
                System.out.println("Weather Now Error:"+new Gson());
            }
            @Override
            public void onSuccess(WeatherNowBean weatherBean){
                Log.i(TAG, "getWeather onSuccess: " + new Gson().toJson(weatherBean));
                //System.out.println("获取天气成功： " + new Gson().toJson(weatherBean));
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因

                if (Code.OK == weatherBean.getCode()) {
                    WeatherNowBean.NowBaseBean now = weatherBean.getNow();
                    System.out.println(now);
                    String tianqi=now.getText();//天气
                    String wendu=now.getTemp()+"℃";//温度
                    String shidu=now.getHumidity()+"%";//湿度，百分比数值
                    String fengli=now.getWindScale();//风力
                    String fengxiang=now.getWindDir();//风向
                    String time = weatherBean.getBasic().getUpdateTime().substring(0,10); //当前API的最近更新时间
                    String time2 = now.getObsTime(); // 数据观测时间
                    String yunliang = now.getCloud(); //云量，百分比数值
                    String tubiao = now.getIcon(); //图标代码



                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            TextView tv_tianqi=view.findViewById(R.id.tv_tianqi);
                            TextView tv_wendu=view.findViewById(R.id.tv_wendu);
                            TextView tv_shidu=view.findViewById(R.id.tv_shidu);
                            TextView tv_time=view.findViewById(R.id.tv_time);

                            tv_tianqi.setText(tianqi);//显示当前天气
                            tv_wendu.setText(wendu);//显示当前温度
                            tv_shidu.setText("湿度："+shidu);//显示前湿度
                            tv_time.setText(time);//显示当前湿度
                        }
                    });
                    /*注意这里对控件显示的操作被放在getActivity()...void run(){}里了
                    这是因为我是在Fragment里操作的，如果把这些放在外边会抛出错误
                    在Activity中时可以把这些放在外边，不用带什么runOnUi...
                    参考了https://blog.csdn.net/i_nclude/article/details/105563688*/

                }
                else {
                    //在此查看返回数据失败的原因
                    Code code = weatherBean.getCode();
                    System.out.println("失败代码: " + code);
                    //Log.i(TAG, "failed code: " + code);
                }
            }
        });


        QWeather.getWeather7D(getActivity(), "CN101110101", Lang.ZH_HANS, Unit.METRIC, new QWeather.OnResultWeatherDailyListener() {
            public static final String TAG="he_feng_now";
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ", e);
                System.out.println("获取天气失败");
                System.out.println("Weather Now Error:"+new Gson());
            }

            @Override
            public void onSuccess(WeatherDailyBean weatherDailyBean) {
                Log.i(TAG, "getWeather onSuccess: " + new Gson().toJson(weatherDailyBean));
                Log.d(TAG,weatherDailyBean.toString());

//                TextView riqi =view.findViewById(R.id.date_text1);
//                TextView tianqi =view.findViewById(R.id.info_text1);
//                TextView max =view.findViewById(R.id.max_text1);
//                TextView min =view.findViewById(R.id.min_text1);
//
//                riqi.setText("日期");
//                tianqi.setText("天气");
//                max.setText("最高温");
//                min.setText("最低温");

//                if (Code.OK == weatherDailyBean.getCode()) {
//                    List<WeatherDailyBean.DailyBean> dailyBeans;
//
//
//                    getActivity().runOnUiThread(new Runnable() {
//                        public void run() {
//
//                            TextView riqi =view.findViewById(R.id.date_text1);
//                            TextView tianqi =view.findViewById(R.id.info_text1);
//                            TextView max =view.findViewById(R.id.max_text1);
//                            TextView min =view.findViewById(R.id.min_text1);
//
//                            riqi.setText("日期");
//                            tianqi.setText("天气");
//                            max.setText("最高温");
//                            min.setText("最低温");
//                        }
//                    });
//
//                }
//                else {
//                    //在此查看返回数据失败的原因
//                    Code code = weatherDailyBean.getCode();
//                    Log.i(TAG, "失败代码: " + code);
//                }
            }


        });


    }
}
