package com.beans.thread;

import com.google.common.collect.Queues;
import com.realTime.controller.TranMessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Description: 容灾备份线程
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/1 11:09
 */
public class DataGuardThred extends Thread {

    private final static Logger logger = LoggerFactory.getLogger(TranMessController.class);

    //自带线程安全光环 在多线程处理中所有要判断的地方都要
    private AtomicBoolean shutdown = new AtomicBoolean(true);

    private BlockingQueue<String> guardQueue = Queues.newArrayBlockingQueue(100000);

    private final int FLUSH_SIZE = 500;

    private String fileName;

    private String localPath;

    private String filePath;

    public DataGuardThred(String fileName, String localPath) {
        this.fileName = fileName;
        this.localPath = localPath;
    }

    @Override
    public void run() {
        doRun();
    }

    private void doRun() {

        if (shutdown.get() || guardQueue.size() > 0) {
            filePath = localPath + fileName + "/时间20190201";
            doFlush();
        }

    }

    private void doFlush() {
        int count = 0;
        List<String> flushList = null;
        while (guardQueue.size() > 0 && count < FLUSH_SIZE) {
            if (flushList == null) {
                flushList = new ArrayList<>();
            }
            flushList.add(guardQueue.poll());
            count++;
        }
        if(flushList.size()>0){
            //flushDateToDisk(flushList,filePath);
            System.out.println("写到磁盘上面");
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public AtomicBoolean getShutdown() {
        return shutdown;
    }

    public void setShutdown(AtomicBoolean shutdown) {
        this.shutdown = shutdown;
    }

    public BlockingQueue<String> getGuardQueue() {
        return guardQueue;
    }

    public void setGuardQueue(BlockingQueue<String> guardQueue) {
        this.guardQueue = guardQueue;
    }

    public int getFLUSH_SIZE() {
        return FLUSH_SIZE;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
