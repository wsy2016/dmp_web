package com.utils.hbase;

import com.handler.HMsgHandler;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static com.beans.GlobalConstants.DEFAULT_FAMILY_NAME;

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
        //conf.addResource("hbase-default.xml");
        //conf.addResource("hbase-site.xml");
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
            if (table != null) {
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
            if (table != null) {
                table.close();
            }
        }
    }

    public void put(String tableName, List<Put> puts) throws IOException {

        if (puts == null || puts.size() < 0) {
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
            if (table != null) {
                table.close();
            }
        }
        try {
            table = getConnection().getTable(tableName);
            table.put(puts);
            return;
        } catch (Exception e) {
            closeConnection();
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }


    public void readHbase(String tableName, String rowkey, List<String> colums) throws IOException {
        Result result = getResult(tableName, rowkey, colums);
        if (null != result) {
            for (Cell cell : result.rawCells()) {
                String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println("列名:>>>>"+qualifier);
                System.out.println("value:>>>>"+value);

            }
        }
    }


    public Result getResult(String tableName, String rowkey, List<String> colums) throws IOException {

        Get get = new Get(Bytes.toBytes(rowkey));
        for (String colum : colums) {
            get.addColumn(DEFAULT_FAMILY_NAME, Bytes.toBytes(colum));
        }
        Result result = null;
        HTableInterface tableInterface = null;
        try {
            tableInterface = getConnection().getTable(tableName);
            result = tableInterface.get(get);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tableInterface.close();
        }
        return result;
    }
}
