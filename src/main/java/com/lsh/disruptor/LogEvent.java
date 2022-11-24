package com.lsh.disruptor;

import com.lmax.disruptor.EventFactory;

import java.time.Instant;

/**
 * @Author: LiuShihao
 * @Date: 2022/11/23 14:39
 * @Desc: 定义事件类：生产者和消费者之间进行交换的数据
 */
public class LogEvent {

    //事件类工厂：引用new方法
    public static final EventFactory<LogEvent> FACTORY = LogEvent::new;
    private String data;

    private Instant timestamp;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "data='" + data + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
