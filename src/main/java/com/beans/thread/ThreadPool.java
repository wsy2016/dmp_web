package com.beans.thread;

import java.util.List;
import java.util.Vector;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/2/26 16:28
 */
public class ThreadPool {

    private static ThreadPool instance = null;

    /**
     * 线程总数
     */
    private int threadCounter;

    /**
     * 是否停止
     */
    private Boolean isShutDown = false;

    /**
     * 空闲的线程队列
     */
    private List<PThread> idleThreads;


    private ThreadPool() {

        threadCounter = 0;

        this.idleThreads = new Vector<>(5);


    }

    public int getCreatedThreadCounter() {
        return this.threadCounter;
    }


    /**
     * 获取单例线程池对象
     */
    public synchronized ThreadPool getInstance() {
        if (null == instance) {
            instance = new ThreadPool();
        }
        return instance;
    }

    /**
     * 放入线程池
     */
    public synchronized void repool(PThread pThread) {
        if (!isShutDown) {

            idleThreads.add(pThread);

        } else {

            pThread.shutDown();
        }
    }

    public synchronized void shutDown() {
        isShutDown = true;
        for (PThread pThread : idleThreads) {
            pThread.shutDown();
        }
    }

    public synchronized void start(Runnable target) {

        int idleSize = idleThreads.size();
        PThread pThread = null;
        if (idleSize > 0) {
            pThread = idleThreads.get(idleSize - 1);
            idleThreads.remove(pThread);
        } else {
            //没有空闲的线程了
            pThread = new PThread();
            threadCounter++;
        }
        pThread.start();
    }
}
