package com.lsh.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.time.Instant;

/**
 * @Author: LiuShihao
 * @Date: 2022/11/23 14:39
 * @Desc: 生产者类：用于发布事件。
 */
public class MyProducer {

    //RingBuffer
    private final RingBuffer<LogEvent> ringBuffer;

    //有参构造
    public MyProducer(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 发布事件
     * @param eventObject
     */
    public void publish(LogEvent eventObject) {
        boolean isPublished = ringBuffer.tryPublishEvent((event, sequence) -> {
            event.setTimestamp(Instant.now());
            event.setData(eventObject.getData());
        });

        if (!isPublished) {
            System.err.println(Thread.currentThread().getName()+" - "+Thread.currentThread().getId() + " producer Failed to publish!");
        }
    }
}
