package com.chen.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.chen.coolweather.db.City;
import com.chen.coolweather.db.County;
import com.chen.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Admin on 2017/3/15.
 */

public class Utility {

    private static final String TAG = "Utility";

    /**
     * 解析和处理服务器返回的省级数据
     *
     * @param response
     * @return
     */
    public static boolean handleProvincesResponse(String response) {

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();//将数据存储到数据库中
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "解析数据异常: " + e.getMessage());
            }
        }
        return false;
    }


    /**
     * 解析和处理服务器返回的市级数据
     *
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "解析数据异常: " + e.getMessage());
            }
        }
        return false;
    }


    /**
     * 解析和处理服务器返回的县级数据
     */

    public static boolean handleCountResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "解析数据异常: " + e.getMessage());
            }
        }
        return false;

    }


}
