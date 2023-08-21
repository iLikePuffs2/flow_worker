package com.zdf.client.rpc;

import com.zdf.client.data.*;

// 用于处理任务调度相关的网络请求(调用 server 的创建任务、获取任务列表等接口)
public interface FlowServer {
    // 去 任务信息表 查所有数据(查询条件是:taskType和status)
    ReturnStatus getTaskList(String taskType, int status, int limit);
    ReturnStatus createTask(AsyncTaskRequest asyncTaskRequest);
    // 对应 AsyncTaskController 的 setTask 方法
    ReturnStatus setTask(AsyncTaskSetRequest asyncTaskSetRequest);
    ReturnStatus getTask(Long taskId);
    // 查出任务配置表里这个任务的所有数据
    ReturnStatus getTaskTypeCfgList();
    ReturnStatus getUserTaskList(String user_id, int statusList);
    ReturnStatus createTaskCFG(ScheduleConfig scheduleConfig);

}
