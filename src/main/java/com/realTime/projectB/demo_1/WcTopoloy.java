package com.realTime.projectB.demo_1;

import backtype.storm.Config;
import backtype.storm.topology.TopologyBuilder;

import java.util.Locale;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/10 22:46
 */
public class WcTopoloy {


    private final static String spoutId = "wcSpout";
    private final static String boltId = "wcSpout";

    public static void main(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        Config conf = new Config();
        conf.setDebug(true);

        WcSpout wcSpout = new WcSpout();
        WcBolt wcBolt = new WcBolt();

        //注册spout和bolt 并发度
        builder.setSpout(spoutId, wcSpout, 5);

        /*
        * 假设: spout有1000行数据
        * shuffleGrouping:bolt的每个parallelism随机分配到1000/5 = 200个
        *
        * */
        builder.setBolt(boltId, wcBolt, 5).shuffleGrouping(spoutId);




    }
}
