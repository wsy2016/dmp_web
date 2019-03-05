package com.realTime.projectA.controller;

import com.beans.MessModel;
import com.beans.thread.DataGuardThred;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.utils.hbase.HMsgHandler;
import com.realTime.projectA.controller.base.BaseController;
import com.utils.kafka.PoolKafkaProducer;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.nustaq.serialization.FSTConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Description:接收上游数据
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/2/26 14:02
 */
@Controller
public class TranMessController implements BaseController {


    private final static Logger logger = LoggerFactory.getLogger(TranMessController.class);

    /**
     * 线程池参数
     */
    private ExecutorService executorService = null;
    private int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private int KEEP_ALIVE_TIME = 1;
    private TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;


    private List<Runnable> threads = Lists.newArrayList();
    private BlockingQueue<String> cacheQueue = Queues.newArrayBlockingQueue(100000);
    private FSTConfiguration fst = FSTConfiguration.createDefaultConfiguration();

    //自带线程安全光环 在多线程处理中所有要判断的地方都要
    private AtomicBoolean shutdown = new AtomicBoolean(true);

    DataGuardThred guardThred;

    private String topic;

    private String tableName = "EVEN_MESS_XZT";

    @Autowired
    private HMsgHandler hMsgHandler;

    @Autowired
    private PoolKafkaProducer kafkaProducer;

    @Override
    public void init() {
        logger.info("开启线程池");

        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程名称-" + this.getClass().getSimpleName()).build();
        executorService = new ThreadPoolExecutor(
                NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                taskQueue,
                threadFactory);
        for (int i = 0; i < 8; i++) {
            //启动kafka-product线程
            kafkaThread thread = new kafkaThread("t" + i);
            executorService.execute(thread);
            threads.add(thread);
        }
        guardThred = new DataGuardThred("fileName", "usr/local/src/data");
        executorService.execute(guardThred);


        hMsgHandler.start();

    }

    @RequestMapping("/xzt/sb")
    public void receiveMess(String mess) {
        boolean result = cacheQueue.offer(mess);
        if (!result) {
            //100000个队列可能已经不够了
            System.out.println("cacheQueue is full.Offer data to guardQueue");

        } else {
            boolean offer = guardThred.getGuardQueue().offer(mess);
            if (!result) {
                System.out.println("GuardQueue is full.Offer data to guardQueue");

            }
        }
    }

    private class kafkaThread implements Runnable {
        private String threadName;

        public kafkaThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            //线程安全的考虑
            while (shutdown.get() || cacheQueue.size() > 0) {
                try {
                    doRun();
                    System.out.println("ThreadName: " + Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRun() throws  InterruptedException, ExecutionException {

        //取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,取不到时返回null
        String msg = cacheQueue.poll(15, KEEP_ALIVE_TIME_UNIT);
        if (null == msg) {
            return;
        }
        /***
         *
         *  MessModel notify =  MsparseMsg(msg)
         *  模拟解析报文
         */
        MessModel notify = new MessModel("1", "2", 3.1, 200, "", new Date());
        //根据交易类型发往不同topic
        String type = notify.getType();
        if ("1".equals(type)) {
            topic = "xztjy";
        } else if ("2".equals(type)) {
            topic = "xztQuery";
        }
        //重点rowkey的生成
        String messageKey = notify.createRowKey();
        hMsgHandler.saveReceiveMess(tableName, messageKey, msg);
        if (StringUtils.isNoneBlank(topic)) {

            byte[] recordByte = fst.asByteArray(notify);
            ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(
                    topic,
                    recordByte,
                    recordByte
            );

            kafkaProducer.sendSync(record);
        }


    }

    @Override
    public void close() {
        shutdown.set(true);
        executorService.shutdown();
    }
}
