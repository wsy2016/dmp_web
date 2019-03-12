package com.realTime.projectB.demo_1;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/10 22:47
 */
public class WcSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;

    private Integer spoutCount = 1;


    private String str =
                   "“The Forsyte Saga” was the title originally destined for that part of it which " +
                    "is called “The Man of Property”; and to adopt it for the collected chronicles " +
                    "of the Forsyte family has indulged the Forsytean tenacity that is in all of us. " +
                    "The word Saga might be objected to on the ground that it connotes the heroic " +
                    "“Let the dead Past bury its dead” would be a better saying if the Past ever died. " +
                    "The persistence of the Past is one of those tragi-comic blessings which each new age denies, " +
                    "coming cocksure on to the stage to mouth its claim to a perfect novelty" +
                    "But no Age is so new as that! Human Nature, under its changing pretensions " +
                    "and clothes, is and ever will be very much of a Forsyte, and might, after all, " +
                    "be a much worse animal";

    /**
     * Description: 初始化启动一次
     * Author: wsy
     * Date: 2019/3/10 22:49
     * Param: [map] 配置参数
     * Param: [topologyContext] 上下文
     * Param: [spoutOutputCollector]
     * Return: void
     */
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        String conf1 = (String) map.get("conf1");
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        long timeMillis = System.currentTimeMillis();
        System.out.println("---spoutCount:" + spoutCount + "---");
        String[] strs = str.split(" ");
        try {
            for (String msg : strs) {
                collector.emit(new Values(msg));
            }
            spoutCount++;
            long timeMillis2 = System.currentTimeMillis();
            System.out.println("user time" + (timeMillis2 - timeMillis) + "毫秒");
            Thread.sleep(Long.MAX_VALUE);
            super.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("msg"));
    }
}
