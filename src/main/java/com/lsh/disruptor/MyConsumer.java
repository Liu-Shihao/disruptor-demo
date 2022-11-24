package com.lsh.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @Author: LiuShihao
 * @Date: 2022/11/23 14:39
 * @Desc: 消费者类：接收事件，实现EventHandler接口
 */
public class MyConsumer implements EventHandler<LogEvent> {
    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("consumer:"+event);
        Thread.sleep(3000);
    }
}
