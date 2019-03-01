package com.handler;

import com.beans.GlobalConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.realTime.controller.TranMessController;
import com.utils.hbase.HbaseHelp;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.yarn.logaggregation.AggregatedLogFormat;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Max;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/2/27 10:33
 */
public class HMsgHandler {

    private final static Logger logger = LoggerFactory.getLogger(HMsgHandler.class);

    private ConcurrentMap<String, List<Put>> vector = Maps.newConcurrentMap();

    private static final String LOCK_DATA = "LOCK_DATA";

    private HbaseHelp hbaseHelp;


    public void saveReceiveMess(String tableName, String messageKey, String msg) {

        Put put = new Put(Bytes.toBytes(messageKey));
        put.add(GlobalConstants.DEFAULT_FAMILY_NAME, Bytes.toBytes("RECEIVER_MESS"), Bytes.toBytes(msg));
        save(tableName, put);

    }

    public void saveErroreMess(String tableName, String messageKey, String msg) {

        Put put = new Put(Bytes.toBytes(messageKey));
        put.add(GlobalConstants.DEFAULT_FAMILY_NAME, Bytes.toBytes("ERROR_MESS"), Bytes.toBytes(msg));
        save(tableName, put);

    }


    public void flush() throws IOException {
        synchronized (LOCK_DATA) {
            for (String tableName : vector.keySet()) {
                List<Put> puts = vector.remove(tableName);
                if (puts != null && puts.size() > 0) {
                    hbaseHelp.put(tableName, puts);
                }
            }
        }
    }


    private void save(String tableName, Put put) {

        synchronized (LOCK_DATA) {
            if (null == put) {
                return;
            }
            List<Put> puts = vector.remove(tableName);
            if (null == puts) {
                puts = Lists.newArrayList();
            }
            puts.add(put);
            vector.put(tableName, puts);
        }
    }


    public void start() {
        for (int i = 0; i < 5; i++) {
            LogWriterThread thread = new LogWriterThread();
            thread.setName(StringUtils.join(this.getClass().getSimpleName(), "_", i));
            thread.start();
        }

    }

    class LogWriterThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (vector.size() > 0) {
                    try {
                        flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }
    }

}
