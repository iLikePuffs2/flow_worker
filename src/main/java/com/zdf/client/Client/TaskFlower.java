package com.zdf.client.Client;

import com.zdf.client.data.AsyncTaskRequest;
import com.zdf.client.data.AsyncTaskReturn;
import com.zdf.client.data.AsyncTaskSetRequest;
import com.zdf.client.data.ScheduleConfig;
import com.zdf.client.enums.TaskStatus;

import java.util.List;

// 这是一个任务管理的接口，对 FlowServer 的结果进行一些处理和封装
public interface TaskFlower {
    public String createTask(AsyncTaskRequest asyncTaskRequest);
    public void setTask(AsyncTaskSetRequest asyncTaskSetRequest);
    public AsyncTaskReturn getTask(Long taskId);
    // 去 任务信息表 查所有数据(查询条件是:taskType和status)
    public List<AsyncTaskReturn> getTaskList(Class<?> clazz, int status, int limit);
    // 查出任务配置表里这个任务的所有数据，装入数据结构
    public List<ScheduleConfig> getTaskTypeCfgList();
    public List<AsyncTaskReturn> getUserTaskList(List<TaskStatus> taskStatuses);
    public void createTaskCFG(ScheduleConfig scheduleConfig);


}
