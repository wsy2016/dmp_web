package com.realTime.projectB.kafka;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import com.sun.scenario.effect.Offset;
import kafka.api.OffsetRequest;
import org.apache.kafka.common.requests.OffsetCommitRequest;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/13 21:33
 */
public class KafkaSpoutTopo {

    /**
     * topic
     */
    private static final String MY_TOPIC = "test_topic";

    /**
     * group
     * 一个topic里面的分区只能被消费组里面的一个消费者消费
     * topic(P0,P1,P2)
     * group(c1,c2)
     * c1消费(P0) c2消费(P1,P2)
     */
    private static final String GROUP_ID = "myTrack";

    /**
     * zookeeper的nodes
     */
    private static final String ZK_HOSTS = "master:2181,slave1:2181,slave2:2181";

    /**
     * 长连接时间(ms)
     */
    private final static int SOCK_TIME = 60 * 10000;

    private static final String SPOUT_ID = "mySpout";
    private static final String SBOLT_ID = "myBolt";
    private static final String TOPO_NAME = "kafkaTopo";


    public static void main(String[] args) throws InterruptedException, AlreadyAliveException, InvalidTopologyException {

        /**
         *
         *  创建&配置--SpoutConfig
         * */
        ZkHosts zkHosts = new ZkHosts(ZK_HOSTS);

        /**
         *   zkRoot -- zook记录offset的路径(/counsumers/${GROUP_ID}/offsets/log/${kafka.paralleid})
         *   topic的paralltion只能配该组的一个消费者消费
         *
         * */
        SpoutConfig spoutConfig = new SpoutConfig(zkHosts, MY_TOPIC, "", GROUP_ID);

        //设置如何处理kafka消息队列输入流{生成者的格式保持一致}
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

        //配置zook信息
        List<String> zkHostList = new ArrayList<>();
        Integer zkPort = null;
        for (String zkHost : zkHosts.brokerZkStr.split(",")) {
            zkHostList.add(zkHost.split(":")[0]);
            zkPort = Integer.valueOf(zkHost.split(":")[1]);
        }
        spoutConfig.zkServers = zkHostList;
        spoutConfig.zkPort = zkPort;
        spoutConfig.socketTimeoutMs = SOCK_TIME;

        /**
        *    默认 EarliestTime() 每次重启都是重最早的开始
         *       现在设置最新的offset
         * */
        spoutConfig.startOffsetTime = OffsetRequest.LatestTime();


        TopologyBuilder builder = new TopologyBuilder();
        Config conf = new Config();
        Map<String, String> map = new HashMap<String, String>();
        // 配置Kafka broker地址
        map.put("metadata.broker.list", "master:9092,slave1:9092,slave2:9092");
        // serializer.class为消息的序列化类
        map.put("serializer.class", "kafka.serializer.StringEncoder");
        conf.put("kafka.broker.properties", map);
        // 配置KafkaBolt生成的topic
        conf.put("topic", "receiver");
        conf.setDebug(false);
        //设置一个spout task中处于pending状态的最大的tuples数量
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);

        builder.setSpout(SPOUT_ID, new KafkaSpout(spoutConfig));
        builder.setBolt(SBOLT_ID, new KafkaBolt(), 3).shuffleGrouping(SPOUT_ID);
        if (args.length == 0) {
            LocalCluster cluster = new LocalCluster();
            //提交本地集群
            cluster.submitTopology(TOPO_NAME, conf, builder.createTopology());
            //等待6s之后关闭集群
            Thread.sleep(6000);
            //关闭集群
            cluster.shutdown();
        }
        StormSubmitter.submitTopology(TOPO_NAME, conf, builder.createTopology());

    }
}
