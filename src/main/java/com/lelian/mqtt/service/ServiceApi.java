package com.lelian.mqtt.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lelian.mqtt.util.Constant;
import com.lelian.mqtt.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class ServiceApi {

    private static Logger logger = LoggerFactory.getLogger(ServiceApi.class.getName());

    //token接口
    private static final String TOKEN_API = "/user/getToken";

    //接口
    private static final String REALTIME_API = "/currentdata/getDeviceCurrentData";

    @Value("${api.tenantEname}")
    private String tenantEname;

    @Value("${api.name}")
    private String name;

    @Value("${api.password}")
    private String password;

    @Value("${api.host}")
    private String HOST;


    @Autowired
    SLRemoteService slRemoteService;

    /**
     * 定时刷新访问token
     */
    public void refreshAccessToken(){
        logger.info("刷新token开始...");
        String link = HOST+TOKEN_API;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tenantEname",tenantEname);
        jsonObject.put("name",name);
        jsonObject.put("password",password);
        jsonObject.put("hash","test");
        String result = HttpUtil.post(link,jsonObject.toJSONString());
        if(StringUtils.isNotBlank(result)){
            JSONObject resultObject = JSON.parseObject(result);
            Constant.accessToken = resultObject.getString("data");
        }
    }

    /**
     * 获取实时数据
     */
    public void getRealTimeData(){
        try{
            String jsonString = getConfigContent();
            JSONObject devicesObject = JSONObject.parseObject(jsonString);
            JSONArray deviceArray = devicesObject.getJSONArray("devices");
            String deviceIds = "";
            for(int i=0;i<deviceArray.size();i++){
                if(i == deviceArray.size()-1){
                    deviceIds = deviceIds + deviceArray.get(i).toString().split(",")[0];
                }else{
                    deviceIds = deviceIds + deviceArray.get(i).toString().split(",")[0] + ",";
                }
            }
            String link = HOST+REALTIME_API+"?token="+Constant.accessToken+"&hash=test&deviceIds="+deviceIds;
            String result = HttpUtil.get(link);
            if(StringUtils.isBlank(result)){
                return;
            }
            JSONObject jsonObject = JSONObject.parseObject(result);
            slRemoteService.handle(jsonObject);
        }catch (Exception e){
            logger.error("getRealTimeData error !",e);
        }
    }

    private static String getConfigContent() throws IOException {
        InputStream inputStream = SLRemoteService.class.getResourceAsStream("/config.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

}
