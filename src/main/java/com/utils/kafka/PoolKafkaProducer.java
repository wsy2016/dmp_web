package com.utils.kafka;

import jdk.nashorn.internal.objects.annotations.Property;
import kafka.Kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vibur.objectpool.ConcurrentLinkedPool;

import java.util.ConcurrentModificationException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/1 15:38
 */
@Service
public class PoolKafkaProducer {


    @Autowired
    private ConcurrentLinkedPool<KafkaProducer> pool;

    public void sendSync(ProducerRecord record) throws InterruptedException, ExecutionException {
        KafkaProducer producer = null;
        try {
            producer = pool.take();
            producer.send(record).get();
        }finally{
            pool.restore(producer);

        }
    }


}
