package com.realTime.projectB.kafka;

import backtype.storm.command.list;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/13 22:47
 */
public class KafkaBolt extends BaseRichBolt {

    List<String> list;
    OutputCollector collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

        list = new ArrayList<String>();
        this.collector = collector;

    }

    @Override
    public void execute(Tuple input) {
        String str = (String) input.getValue(0);
        //将str加入到list
        list.add(str);
        //发送ack
        collector.ack(input);
        //发送该str
        collector.emit(new Values(str));
    }

    /**
     * topology被killed时调用
     */
    @Override
    public void cleanup() {
        //将list的值写入到文件
        try {
            FileOutputStream outputStream = new FileOutputStream("C://" + this + ".txt");
            PrintStream p = new PrintStream(outputStream);
            p.println("begin!");
            p.println(list.size());
            for (String tmp : list) {
                p.println(tmp);
            }
            p.println("end!");
            try {
                p.close();
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        declarer.declare(new Fields("message"));

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }
}
