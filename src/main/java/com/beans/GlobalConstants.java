package com.beans;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Description: 全部成员变量
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/1/28 10:26
 */
public class GlobalConstants {

    /********************redis存储类型*******************************/
    public final static String redis_hash_type = "0";

    public final static String redis_set_type = "1";

    public final static String redis_sortset_type = "2";

    public final static String redis_string_type = "3";


    public final static byte[] DEFAULT_FAMILY_NAME = Bytes.toBytes("f1");
}
