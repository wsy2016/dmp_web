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

    private final String msg = "apple apple hello";

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
        collector.emit(new Values(msg));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("msg"));
    }
}
