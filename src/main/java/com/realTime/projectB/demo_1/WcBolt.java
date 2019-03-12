package com.realTime.projectB.demo_1;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/10 22:47
 */
public class WcBolt extends BaseRichBolt {


    private Integer boltCount = 1;

    Map<String, Integer> countMap = new HashMap<>();
    long timeMillis = 0;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {
        timeMillis = System.currentTimeMillis();
    }

    @Override
    public void execute(Tuple tuple) {
        String msg = tuple.getStringByField("msg");
        if (countMap.containsKey(msg)) {
            Integer count = countMap.get(msg);
            count++;
            countMap.put(msg, count);
        } else {
            countMap.put(msg, 1);
        }
        System.out.println("-------------------" + boltCount + "--------------------");
        for (String key : countMap.keySet()) {
            System.out.println("key: " + key + "<<<<<>>>>>" + " count: " + countMap.get(key));
        }
        System.out.println("bolt user time:"+(System.currentTimeMillis()-timeMillis+"毫秒"));
        System.out.println("-------------------" + boltCount + "--------------------");
        boltCount++;


    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
