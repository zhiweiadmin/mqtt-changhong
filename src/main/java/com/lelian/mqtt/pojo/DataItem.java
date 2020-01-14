package com.lelian.mqtt.pojo;

import com.google.common.collect.Maps;

import java.util.Map;

public class DataItem {

    public static Map<String,Integer> ITEMMAP = Maps.newHashMap();

    {
        ITEMMAP.put("开关机",1);
        ITEMMAP.put("模式",2);
        ITEMMAP.put("设定温度整数",3);
        ITEMMAP.put("室内环境温度",4);
        ITEMMAP.put("室外环境温度",5);
        ITEMMAP.put("室内相对湿度",6);
        ITEMMAP.put("室温1",7);
        ITEMMAP.put("线控器温度",8);
        ITEMMAP.put("内盘温度",9);
        ITEMMAP.put("内机冷媒入口温度",10);
        ITEMMAP.put("内机冷媒出口温度",11);
        ITEMMAP.put("回水温度",12);
        ITEMMAP.put("出水温度",13);
        ITEMMAP.put("水箱温度",14);
        ITEMMAP.put("排气温度1",15);
        ITEMMAP.put("排气温度2",16);
        ITEMMAP.put("除霜状态",17);
        ITEMMAP.put("压机1运行状态",18);
        ITEMMAP.put("室内风机运行状态",19);
        ITEMMAP.put("补水阀状态",20);
        ITEMMAP.put("回水阀状态",21);
        ITEMMAP.put("供水泵状态",22);
        ITEMMAP.put("循环水泵状态",23);
        ITEMMAP.put("电加热状态",24);
        ITEMMAP.put("室外风机运行状态",25);
        ITEMMAP.put("控器温度传感器故障",26);
        ITEMMAP.put("室温1温度传感器故障",27);
        ITEMMAP.put("内环温度传感器故障",28);
        ITEMMAP.put("内盘温度传感器故障",29);
        ITEMMAP.put("回管温度传感器故障",30);
        ITEMMAP.put("水管温度传感器故障",31);
        ITEMMAP.put("水箱温度传感器故障",32);
        ITEMMAP.put("冷媒进口温传器故障",33);
        ITEMMAP.put("机冷媒口度传器故障",34);
        ITEMMAP.put("排气温度传感器故障",35);
        ITEMMAP.put("环境温度传感器故障",36);
        ITEMMAP.put("外盘温度传感器故障",37);
        ITEMMAP.put("外机温度传感器故障",38);
        ITEMMAP.put("外温度传感器故障",39);
        ITEMMAP.put("经济器温度传感故障",40);
        ITEMMAP.put("经济器出口传感故障",41);
        ITEMMAP.put("外风机过载",42);
        ITEMMAP.put("内风机过载",43);
        ITEMMAP.put("循环泵过载",44);
        ITEMMAP.put("供水泵过载",45);
        ITEMMAP.put("压机气温度过高故障",46);
        ITEMMAP.put("与外机通讯故障",47);
        ITEMMAP.put("电源开关",48);
        ITEMMAP.put("温度设定",49);
        ITEMMAP.put("模式控制",50);
    }

}
