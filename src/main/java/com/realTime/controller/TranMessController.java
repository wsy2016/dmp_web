package com.realTime.controller;

import com.beans.MessModel;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.realTime.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


import java.util.Date;
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

    private BlockingQueue<String> cacheQueue = Queues.newArrayBlockingQueue(100000);

    private AtomicBoolean shutdown = new AtomicBoolean(false);

    private String topic;

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
            //启动kafka生成线程
            executorService.execute(new kafkaThread("t" + i));
        }

    }


    private class kafkaThread implements Runnable {
        private String threadName;

        public kafkaThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
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

    private void doRun() throws InterruptedException {

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

    }

    @Override
    public void close() {
        shutdown.set(true);
        executorService.shutdown();
    }
}
