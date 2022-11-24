package com.lsh.disruptor;


import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * @Author: LiuShihao
 * @Date: 2022/11/23 14:38
 * @Desc:
 */
public class Main {
    public static void main(String[] args) {
        MyConsumer myConsumer = new MyConsumer();

        Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(
                LogEvent.FACTORY,
                2,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI,
                new BlockingWaitStrategy()
        );
        disruptor.handleEventsWith(myConsumer);
        disruptor.start();

        MyProducer myProducer = new MyProducer(disruptor.getRingBuffer());

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                LogEvent logEvent = LogEvent.FACTORY.newInstance();
                logEvent.setData(Thread.currentThread().getName());
                myProducer.publish(logEvent);
            }).start();
        }
    }
}

