package com.carrey.carrey.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Carrey
 * @className DelayItemVo
 * @description DelayItemVo
 * @date 2021/4/9 1:49 下午
 */
public class DelayItem<T> implements Delayed {

    /**
     * 到期时间 单位：ms
     */
    private long activeTime;

    /**
     * 实体
     */
    private T entity;

    public DelayItem(long activeTime, T entity) {
        // 将传入的时间转换为超时的时刻
        this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, TimeUnit.MILLISECONDS)
                + System.nanoTime();

        this.entity = entity;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 剩余时间= 到期时间-当前系统时间，系统一般是纳秒级的，所以这里做一次转换
        return unit.convert(activeTime-System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * 按照剩余时间进行排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        // 剩余时间-当前传入的时间= 实际剩余时间（单位纳秒）
        long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        // 根据剩余时间判断等于0 返回1 不等于0
        // 有可能大于0 有可能小于0  大于0返回1  小于返回-1
        return (d == 0) ? 0 : ((d > 0) ? 1 : -1);
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getEntity() {
        return entity;
    }
}
