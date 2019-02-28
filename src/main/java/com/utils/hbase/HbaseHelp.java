package com.utils.hbase;

import com.handler.HMsgHandler;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Description: 代码更jdbc写法一样low
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/2/27 10:26
 */
public class HbaseHelp {

    private final static Logger logger = LoggerFactory.getLogger(HbaseHelp.class);
    private Configuration conf = null;
    private HConnection connection = null;

    public void init() throws IOException {
        //读取配置文件
        conf = HBaseConfiguration.create();
        //创建连接
        //todo 优化放入连接池里面
        connection = HConnectionManager.createConnection(conf);

    }


    private synchronized HConnection getConnection() throws IOException {

        if (connection == null) {
            connection = HConnectionManager.createConnection(conf);
        }
        return connection;
    }

    private synchronized void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection = null;
    }


    public void put(String tableName, Put put) throws IOException {
        if (put == null) {
            return;
        }
        HTableInterface table = null;
        try {
            table = getConnection().getTable(tableName);
            table.put(put);
            return;
        } catch (Exception e) {
            closeConnection();
        } finally {
            if(table != null){
                table.close();
            }
        }

        try {
            //第二次连接
            table = getConnection().getTable(tableName);
            table.put(put);
            return;
        } catch (Exception e) {
            closeConnection();
        } finally {
            if(table != null){
                table.close();
            }
        }
    }

    public  void put(String tableName, List<Put> puts) throws IOException {

        if (puts == null || puts.size()<0) {
            return;
        }
        HTableInterface table = null;
        try {
            table = getConnection().getTable(tableName);
            table.put(puts);
            return;
        } catch (Exception e) {
            closeConnection();
        } finally {
            if(table != null){
                table.close();
            }
        }
    }
}
