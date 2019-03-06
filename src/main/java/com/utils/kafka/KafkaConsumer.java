package com.utils.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/3/1 19:00
 */
//@Component
public class KafkaConsumer {

    /**
     * 监听test主题,有消息就读取
     * @param message
     */
   // @KafkaListener(topics = {"test"})
    public void consumer(String message){
        System.out.println("test topic message : {}"+ message);
    }
}
