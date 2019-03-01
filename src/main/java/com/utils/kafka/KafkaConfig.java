package com.utils.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.vibur.objectpool.ConcurrentLinkedPool;
import org.vibur.objectpool.PoolObjectFactory;

import java.util.Properties;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/1 17:40
 */
@Configuration
@PropertySource("classpath:conf/kafka.properties")
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Value("${spring.kafka.consumer.group-id}")
    private String group_id;

//    @Value("${spring.kafka.bootstrap-servers}")
//    private String enable_auto_commit;
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String auto_commit_interval_ms;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String key_deserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String value_deserializer;

    private int initToalSize = 8;
    private int maxSize = 50;


    @Bean(value = "concurrentLinkedPool")
    public ConcurrentLinkedPool<KafkaProducer> initLinkedPool() {
        KafkaProducerFactory kafkaProducerFactory = new KafkaProducerFactory();
        return new ConcurrentLinkedPool(kafkaProducerFactory, initToalSize, maxSize, false);
    }

    public class KafkaProducerFactory implements PoolObjectFactory<KafkaProducer> {

        public KafkaProducerFactory() {

            Properties props = new Properties();
            props.put("bootstrap.servers", bootstrap_servers);

            props.put("group.id", group_id);

//            props.put("enable.auto.commit", enable_auto_commit);
//
//            props.put("auto.commit.interval.ms", auto_commit_interval_ms);

            props.put("key.deserializer", key_deserializer);

            props.put("value.deserializer", value_deserializer);
        }

        @Override
        public KafkaProducer create() {
            return null;
        }

        @Override
        public boolean readyToTake(KafkaProducer kafkaProducer) {
            return false;
        }

        @Override
        public boolean readyToRestore(KafkaProducer kafkaProducer) {
            return false;
        }

        @Override
        public void destroy(KafkaProducer kafkaProducer) {

        }
    }
}
