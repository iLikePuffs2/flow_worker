package com.zdf.client.core;

import com.zdf.client.data.AsyncTaskBase;
import com.zdf.client.data.ScheduleConfig;

import java.util.List;
// 观察者的接口，声明了一些方法，这些方法对应于任务执行的各个阶段
public interface ObserverFunction {
    void onBoot();
    void onObtain(List<AsyncTaskBase> asyncTaskBaseList);
    void onExecute(AsyncTaskBase asyncTaskReturn);
    void onFinish(AsyncTaskBase asyncTaskReturn);
    void onStop(AsyncTaskBase asyncTaskReturn);
    void onError(AsyncTaskBase asyncTaskReturn, ScheduleConfig scheduleConfig, List<AsyncTaskBase> asyncTaskBaseList, Exception e);

}
